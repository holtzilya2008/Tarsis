package tastelab.mleshem.tarsisv10;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ilya on 11/18/15.
 * This class will represent a single question to the participant
 * This class will be responsible only for representation and storing the data
 * all the Touch and Click Listeners is set once in the experiment class
 */
public class Question {
/* -------------------------------- fields *-------------------------------- */

    //Question ID
    private MainActivity activity;
    private int qid;
    private int tarsisFake;
    private int tarsisReal;
    private int order;  //in original Sidra 1, Sidra2

    //Question Type
    private int about; //Strong or Tasty
    private int tarsisType; // Salty or Sweet

    //Question and answer environment
    private TextView questionText;
    private TextView questionProperties;
    private TextView answerText; // X/100
    private SeekBar seekBar;
    private int answer;
    private boolean isAnswered;
    private boolean isActive;

/* ---------------------------- Constant Values ---------------------------- */

    public static final int STRONG = 0;
    public static final int TASTY = 1;

    private static final String SIDRA_WORD = String.valueOf(R.string.sidra_word);
    private static final String TARSIS_WORD = String.valueOf(R.string.tarsis_word);
    private static final String QUESTION_STRONG = String.valueOf(R.string.strong_question);
    private static final String QUESTION_TASTY = String.valueOf(R.string.tasty_question);

    private static final int INIT_ANSWER = -1;
    private static final int DEFAULT_PROGRESS = R.integer.default_progress;
    private static final int MAX_PROGRESS = R.integer.max_progress;

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Question";

/* ---------------------------- Object Construction ------------------------ */

    public Question(MainActivity activity,
                    int qid,
                    int tarsisFake,
                    int tarsisReal,
                    int order,
                    int about,
                    int tarsisType) {
        this.activity = activity;
        this.qid = qid;
        this.tarsisFake = tarsisFake;
        this.tarsisReal = tarsisReal;
        this.order = order;
        this.about = about;
        this.tarsisType = tarsisType;
        this.isActive = false;

        loadQuestionEnvironment();
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void Show(){
        updateEnvironment();
        this.questionProperties.setVisibility(View.VISIBLE);
        this.questionText.setVisibility(View.VISIBLE);
        this.answerText.setVisibility(View.VISIBLE);
        this.seekBar.setVisibility(View.VISIBLE);
        this.isActive = true;
    }

    public void Close(){
        this.questionProperties.setVisibility(View.GONE);
        this.questionText.setVisibility(View.GONE);
        this.answerText.setVisibility(View.GONE);
        this.seekBar.setVisibility(View.GONE);
        this.isActive = false;
    }

    public void SetAnswer(int ans){
        this.answer = ans;
        this.isAnswered = true;
    }

    public boolean isAnswered(){
        return isAnswered;
    }

    public boolean isActive(){
        return isActive;
    }

/* ----------------------------- Object Methods ---------------------------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return qid == question.qid;

    }

    @Override
    public int hashCode() {
        return qid;
    }

    @Override
    public String toString() {
        return ""+answer; //TODO make a number of zeros generator
    }

    /* ---------------------------- Private Methods ---------------------------- */

    private void loadQuestionEnvironment(){
        this.questionText = (TextView)activity.findViewById(R.id.questionText);
        this.questionProperties = (TextView)activity.findViewById(R.id.qproperties);
        if(this.about == STRONG) {
            this.answerText = (TextView) activity.findViewById(R.id.strongText);
            this.seekBar = (SeekBar) activity.findViewById(R.id.strongBar);
        }else if(this.about == TASTY){
            this.answerText = (TextView)activity.findViewById(R.id.tastyText);
            this.seekBar = (SeekBar)activity.findViewById(R.id.tastyBar);
        }
        this.answer = INIT_ANSWER;
        this.isAnswered = false;
    }

    private void updateEnvironment(){
        if(about == STRONG) {
            this.questionText.setText(QUESTION_STRONG + "?");
        }else if(about == TASTY){
            this.questionText.setText(QUESTION_TASTY + "?");
        }
        this.questionProperties.setText(SIDRA_WORD + " " + this.order "   " +
                                        TARSIS_WORD + " " + this.tarsisFake);
        if(isAnswered) {
            seekBar.setProgress(answer);
            answerText.setText(answer+"/"+MAX_PROGRESS);
        }else{
            seekBar.setProgress(DEFAULT_PROGRESS);
            answerText.setText(DEFAULT_PROGRESS + "/" + MAX_PROGRESS);
            Log.d(TAG,"Question" + qid + " :Is not been answered yet, " +
                    "setting the default values");
        }
    }

} // End of Class Question ------------------------------------------------- //
