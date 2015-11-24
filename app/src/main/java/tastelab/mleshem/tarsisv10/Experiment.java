package tastelab.mleshem.tarsisv10;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ilya on 11/18/15.
 * This class will represent a single Tarsisim Experiment
 * will be constructed in the MainActivity after the experimenter will log in.
 */
public class Experiment {
/* -------------------------------- fields *-------------------------------- */

    // Experiment id:
    private Sequence sequence;
    private int mode;  //Salty->Sweet or Sweet->Salty

    // State Machine
    private ArrayList<Question> questions;
    private int current;
    private int state;

    // Environment
    private MainActivity activity;
    private SeekBar strongBar;
    private SeekBar tastyBar;
    private ImageButton backButton;
    private ImageButton nextButton;

/* ---------------------------- Constant Values ---------------------------- */

    private static final int QUESTIONS = Sequence.SIZE*4;
    private static final int FIRST = 0;
    private static final int LAST = QUESTIONS -1;
    private static final int FIRST_SIDRA = 1;
    private static final int LAST_SIDRA = 2;

    //State Machine
    private static final int INIT = 0;          /* Ready to start */
    private static final int NEW_QUESTION = 1;  /* When question is Active but
                                                   not answered yet         */
    private static final int OLD_QUESTION = 2;  /* When an old question
                                                   (that was already answered)
                                                   is now Active            */
    private static final int FINISHED = 3;      /* When all questions are
                                                   answered, and we are on the
                                                   FinishScreen             */
    private static final int DESTROYED = 404;   /* This object is ready to be
                                                   destroyed                */

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Experiment";

/* ---------------------------- Object Construction ------------------------ */

    public Experiment(MainActivity activity,
                      Sequence sequence,int mode){
        this.mode = mode;
        this.activity = activity;
        this.sequence = sequence;
        prepareEnvironment();
        prepareQuestions();
        this.state = INIT;
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void Start(){
        First();
    }

    public Result GetResults(){
        if(state == FINISHED){
            return new Result(questions);
        }
        Log.d(TAG,"BUG method GetResults can't be called in this state!\n" +
        "State =  "+state);
        return null;
    }

    public void Review(){
        if(state == FINISHED){
            Last();
        }
        Log.d(TAG,"BUG method Review shouldn't be called in this state!\n" +
                "State =  "+state);
    }

    public void Destroy(){
        this.state = DESTROYED;
        this.backButton.setVisibility(View.GONE);
        this.nextButton.setVisibility(View.GONE);
        this.current = -1;
        activity.FinishExperiment();
    }

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void prepareEnvironment(){
        SeekBar.OnSeekBarChangeListener progressListener =
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser){
                            updateCurrentQuestion(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                };

        strongBar = (SeekBar)activity.findViewById(R.id.strongBar);
        strongBar.setOnSeekBarChangeListener(progressListener);
        tastyBar = (SeekBar)activity.findViewById(R.id.tastyBar);
        tastyBar.setOnSeekBarChangeListener(progressListener);
        backButton = (ImageButton)activity.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prev();
            }
        });
        nextButton = (ImageButton)activity.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next();
            }
        });
    }

    private void updateCurrentQuestion(int progress){
        if(state == FINISHED || state == INIT || state == DESTROYED){
            Log.d(TAG,"BUG updateCurrentQuestion - Shoudn't be here at this state");
            return;
        }
        questions.get(current).SetAnswer(progress);
        updateCurrentState();
    }

    private void prepareQuestions(){
        this.questions = new ArrayList<Question>();
        int qid = FIRST;
        for (int sidra=FIRST_SIDRA;sidra<=LAST_SIDRA;sidra++) {
            for (int i = 0; i < Sequence.SIZE; i++) {
                Question qStrong = new Question(activity,  // MainActivity
                        qid,                               // Question ID
                        i+1,                               // TarsisFake
                        sequence.getTarsisim()[i],         // TarsisReal
                        sidra,                             // Sidra (order)
                        Question.STRONG);                  // QuestionAbout
                questions.add(qid, qStrong);
                qid++;
                Question qTasty = new Question(activity,   // MainActivity
                        qid,                               // Question ID
                        i+1,                               // TarsisFake
                        sequence.getTarsisim()[i],         // TarsisReal
                        sidra,                             // Sidra (order)
                        Question.TASTY);                   // QuestionAbout
                questions.add(qid, qTasty);
                qid++;
            }
        }
        if(qid != QUESTIONS || questions.size()!=QUESTIONS){
            Log.d(TAG,"BUG while preparing questions:" +
                    " qid = " + qid + " questions.size = " + questions.size());
        }
        current = FIRST;
    }


    private void First(){
        if(state == INIT){
            state = NEW_QUESTION;
        }
        if(current!=FIRST){
            questions.get(current).Close();
        }
        current = FIRST;
        questions.get(current).Show();
        updateCurrentState();
    }

    private void Last(){
        if(state == NEW_QUESTION){
            Log.d(TAG, "BUG: Last called when new question active");
            return;
        }
        if(current!=LAST){
            questions.get(current).Close();
        }
        current = LAST;
        questions.get(current).Show();
        updateCurrentState();
    }

    private void Next(){
        if(current == LAST){
            loadFinishScreen();
            return;
        }
        questions.get(current).Close();
        current++;
        questions.get(current).Show();
        updateCurrentState();
    }

    private void Prev(){
        if(current == FIRST){
            First();
            return;
        }
        questions.get(current).Close();
        current--;
        questions.get(current).Show();
        updateCurrentState();
    }

    private boolean isLegalOperation(){ //if operation is legal returns true
        return state != DESTROYED;
    }

    private void updateCurrentState(){
        if(current == FIRST){
            backButton.setVisibility(View.GONE);
        }else {
            backButton.setVisibility(View.VISIBLE);
        }
        if(questions.get(current).isAnswered()){
            nextButton.setVisibility(View.VISIBLE);
            state = OLD_QUESTION;
        }else{
            nextButton.setVisibility(View.GONE);
            state = NEW_QUESTION;
        }
    }

    private void loadFinishScreen(){
        questions.get(current).Close();
        if(!isAllAnswered()){
            Log.d(TAG,"BUG : Finish request when not all answered!");
            return;
        }
        if(!isAllClosed()){
            Log.d(TAG,"BUG : Finish Request when not all closed");
            return;
        }
        this.state = FINISHED;
        GetResults();       // TODO - Complete finish screen
        Destroy();
    }

    private boolean isAllAnswered(){
        for (Question q : questions){
            if(!q.isAnswered()){
                return false;
            }
        }
        return true;
    }

    private boolean isAllClosed(){
        for (Question q : questions){
            if(q.isActive()){
                return false;
            }
        }
        return true;
    }

} // End of Class Experiment ----------------------------------------------- //

