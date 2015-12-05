package tastelab.mleshem.tarsisv10;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Comparator;

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

    //Question and answer environment
    private TextView questionText;
    private TextView questionProperties;
    private TextView very_d;        //descriptor eg. very tasty
    private TextView not_d;         //descriptor eg. not tasty
    private TextView answerText; // X/100
    private SeekBar seekBar;
    private int answer;
    private boolean isAnswered;
    private boolean isActive;

/* ---------------------------- Constant Values ---------------------------- */

    public static final int STRONG = 0;
    public static final int TASTY = 1;



    private static final int INIT_ANSWER = -1;
    private static final float ALPHA = (float)0.5;

    //Comparing between questions (for output sorting)
    private static final int GREATER = 1;
    private static final int EQUAL = 0;
    private static final int SMALLER = -1;

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Question";

/* ---------------------------- Object Construction ------------------------ */

    public Question(MainActivity activity,
                    int qid,
                    int tarsisFake,
                    int tarsisReal,
                    int order,
                    int about) {
        this.activity = activity;
        this.qid = qid;
        this.tarsisFake = tarsisFake;
        this.tarsisReal = tarsisReal;
        this.order = order;
        this.about = about;
        this.isActive = false;
        loadQuestionEnvironment();
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void Show(){
        updateEnvironment();
        this.questionProperties.setVisibility(View.VISIBLE);
        this.questionText.setVisibility(View.VISIBLE);
        this.very_d.setVisibility(View.VISIBLE);
        this.not_d.setVisibility(View.VISIBLE);
        this.answerText.setVisibility(View.VISIBLE);
        this.seekBar.setVisibility(View.VISIBLE);
        this.isActive = true;
        setHalfTransparent(!isAnswered);
    }

    public void Close(){
        this.questionProperties.setVisibility(View.GONE);
        this.questionText.setVisibility(View.GONE);
        this.very_d.setVisibility(View.GONE);
        this.not_d.setVisibility(View.GONE);
        this.answerText.setVisibility(View.GONE);
        this.seekBar.setVisibility(View.GONE);
        this.isActive = false;
    }

    public void SetAnswer(int ans){
        if(isActive) {
            this.answer = ans;
            this.isAnswered = true;
            setHalfTransparent(!isAnswered);
            showAnswer();
        }
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
        return ""+Helper.printZeros(answer)+answer;
    }

/* ----------------------------- Object Comparator ------------------------- */

    public static Comparator<Question> MyComparator = new Comparator<Question>() {
        @Override
        public int compare(Question lhs, Question rhs) {
            if(lhs.order < rhs.order){
                return SMALLER;
            }else if (lhs.order > rhs.order){
                return GREATER;
            }else{    // Their order is equal

                if(lhs.tarsisReal < rhs.tarsisReal){
                    return SMALLER;
                }else if (lhs.tarsisReal > rhs.tarsisReal){
                    return GREATER;
                }else{      //Their order and tarsisReal is equal

                    if(lhs.about < rhs.about){
                        return SMALLER;
                    }else{
                        return GREATER;
                    }

                }
            }
        }
    };

/* ---------------------------- Private Methods ---------------------------- */

    private void loadQuestionEnvironment(){
        this.questionText = (TextView)activity.findViewById(R.id.questionText);
        this.questionProperties = (TextView)activity.findViewById(R.id.qproperties);
        if(this.about == STRONG) {
            this.answerText = (TextView) activity.findViewById(R.id.strongText);
            this.seekBar = (SeekBar) activity.findViewById(R.id.strongBar);
            this.very_d = (TextView) activity.findViewById(R.id.veryStrongText);
            this.not_d = (TextView) activity.findViewById(R.id.notStrongText);
        }else if(this.about == TASTY){
            this.answerText = (TextView)activity.findViewById(R.id.tastyText);
            this.seekBar = (SeekBar)activity.findViewById(R.id.tastyBar);
            this.very_d = (TextView)activity.findViewById(R.id.veryTastyText);
            this.not_d = (TextView)activity.findViewById(R.id.notTastyText);
        }
        this.answer = INIT_ANSWER;
        this.isAnswered = false;
    }

    private void updateEnvironment(){
        if(about == STRONG) {
            this.questionText.setText(Experiment.QUESTION_STRONG + "?");
        }else if(about == TASTY){
            this.questionText.setText(Experiment.QUESTION_TASTY + "?");
        }
        this.questionProperties.setText(Experiment.SIDRA_WORD + " " + this.order + "   " +
                Experiment.TARSIS_WORD + " " + this.tarsisFake);
        if(isAnswered) {
            showAnswer();
        }else{
            seekBar.setProgress(Experiment.DEFAULT_PROGRESS);
            String defaultAnswer = ""+Experiment.DEFAULT_PROGRESS + "/" + Experiment.MAX_PROGRESS;
            answerText.setText(defaultAnswer);
            Log.d(TAG,"Question" + qid + " :Is not been answered yet, " +
                    "setting the default values");
        }
    }

    private void showAnswer(){
        seekBar.setProgress(answer);
        String ans = "" + answer+"/"+Experiment.MAX_PROGRESS;
        answerText.setText(ans);
    }

    private void setHalfTransparent(boolean x){
        if(!isActive){
            Log.d(TAG,"BUG setHalfTransparent called when not Active!");
            return;
        }
        if (x){
            seekBar.setAlpha(ALPHA);
        } else{
            seekBar.setAlpha((float)1);
        }
    }

} // End of Class Question ------------------------------------------------- //
