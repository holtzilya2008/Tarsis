package tastelab.mleshem.tarsisv10;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ilya on 11/22/15.
 */
public class Result {
/* -------------------------------- fields *-------------------------------- */

    private ArrayList<Question> answers;
    private String output;

/* ---------------------------- Constant Values ---------------------------- */

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Result";

/* ---------------------------- Object Construction ------------------------ */

    public Result(ArrayList<Question> answered){
        this.answers = new ArrayList<Question>();
        copyList(answered);
        Collections.sort(this.answers,Question.MyComparator);
        makeOutput();
        Log.d(TAG,"Output : " + output);
    }

/* ----------------------------- Public Methods ---------------------------- */

    public String getOutput() {
        return output;
    }

    /* [psyFeatures]
     * needed in case the mode is Sweet->Salty and the output will be
     * posted into the SPSS system without manual reordering */
    public String printOppositeMode(){
        Log.d(TAG,"Reached printOppositeMode");
        String s = "";
        Log.d(TAG,"Printing the second sequence");
        for(int i = answers.size()/2;i<answers.size();i++ ){
            s = s + answers.get(i).toString() + ExperimentData.COMMA;
        }
        Log.d(TAG,"Printing the first sequence");
        for(int i = 0; i< answers.size()/2; i++ ){
            s = s + answers.get(i).toString() + ExperimentData.COMMA;
        }
        return s;
    }

/* ---------------------------- Private Methods ---------------------------- */

    private void copyList(ArrayList<Question> answered){
        if (answered == null) {
            Log.d(TAG,"Null Pointer Received");
            return;
        }
        for (Question q : answered){
            this.answers.add(q);
        }
    }

    private void makeOutput(){
        output = "";
        for(Question q : answers){
            output = output + q + ExperimentData.COMMA;
        }
    }

} // End of Class Result --------------------------------------------------- //
