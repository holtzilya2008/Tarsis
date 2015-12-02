package tastelab.mleshem.tarsisv10;

import android.util.Log;

/**
 * Created by ilya on 11/22/15.
 */
public class ExperimentData {
/* -------------------------------- fields *-------------------------------- */

    private String experimenterName;
    private String subjectName;
    private String dateAndTime;
    private int mode;  //Salty->Sweet or Sweet->Salty
    private Sequence sequence;
    private Result result;
    private boolean isResultPresent;

/* ---------------------------- Constant Values ---------------------------- */

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "ExperimentData";

/* ---------------------------- Object Construction ------------------------ */

    public ExperimentData(String experimenterName,
                          String subjectName,
                          String dateAndTime,
                          int mode,
                          Sequence sequence) {
        this.experimenterName = experimenterName;
        this.subjectName = subjectName;
        this.dateAndTime = dateAndTime;
        this.mode = mode;
        this.sequence = sequence;
        this.result = null;
        this.isResultPresent = false;
    }

/* ----------------------------- Public Methods ---------------------------- */

    public final Sequence getSequence(){
        return sequence;
    }

    public String PrintFileName(){
        return "" + experimenterName + dateAndTime + subjectName;
    }

    public String PrintOutput(){
        if(isResultPresent) {
            return "" + printMode() + Helper.COMMA +
                    sequence.toOutput() + Helper.COMMA +
                    result.getOutput();
        }
        Log.d(TAG,"BUG : Result is not present");
        return "";
    }

    public void SetResult(Result newResult){
        if(newResult == null){
            Log.d(TAG,"BUG setResult : null pointer received");
            return;
        }
        this.result = newResult;
        this.isResultPresent = true;
    }

    public boolean isResultPresent() {
        return isResultPresent;
    }

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private String printMode(){
        String s = "";
        if(mode == Helper.SWEET_MODE){
            s = s + Helper.SWEET;
        }else{
            s = s + Helper.SALTY;
        }
        return s;
    }

} // End of Class ExperimentData ------------------------------------------- //