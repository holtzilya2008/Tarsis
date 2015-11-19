package tastelab.mleshem.tarsisv10;

import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ilya on 11/18/15.
 * This class will represent a single question to the participant
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

/* ---------------------------- Constant Values ---------------------------- */

    public static final int STRONG = 0;
    public static final int TASTY = 1;

    private static final String SIDRA_WORD = String.valueOf(R.string.sidra_word);
    private static final String TARSIS_WORD = String.valueOf(R.string.tarsis_word);
    private static final String QUESTION_STRONG = String.valueOf(R.string.strong_question);
    private static final String QUESTION_TASTY = String.valueOf(R.string.tasty_question);

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


    }

/* ----------------------------- Public Methods ---------------------------- */

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void loadQuestionEnvironment(){
        /*
        private TextView questionText;
        private TextView questionProperties;
        private TextView answerText; // X/100
        private SeekBar seekBar;
        private int answer;
        private boolean isAnswered;
        */



    }

} // End of Class Question ------------------------------------------------- //
