package tastelab.mleshem.tarsisv10;

import android.util.Log;

/**
 * Created by ilya on 11/22/15.
 * The class stores the experiment data given by the experimentor in the
 * login Activity. The class also responsible to print the results
 * int the right format
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

    //MODE
    public static final int SALTY_MODE = 0;
    public static final String SALTY = "Salty";
    public static final int SWEET_MODE = 1;
    public static final String SWEET = "Sweet";
    public static final String COMMA = ",";

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
        return "tarsis_" + subjectName + ".csv";
    }

    public String PrintEmailSubject(){
        return "Tarsis : " + experimenterName + " , " + dateAndTime + " : " + subjectName;
    }

    public String PrintEmailText(){
        return "Tarsis Experiment results:\n" +
                "Experimenter: " + experimenterName + "\n" +
                "Subject: " + subjectName + "\n" +
                "Date and Time: " + dateAndTime + "\n" +
                "Mode: " + printMode() + "\n" +
                "See Attached CSV file. \n \n" +
                "Best Regards,\n" + experimenterName + ", \n" +
                "Micah Leshem Taste Lab, University of Haifa.";
    }

    public String PrintOutput(){
        if(isResultPresent) {
            return  "Tarsis" + "\n" +
                    "Experimenter:" + COMMA + experimenterName + "\n" +
                    "Subject:" + COMMA + subjectName + "\n" +
                    "Time:" + COMMA + dateAndTime + "\n" +
                    "Mode:" + COMMA + printMode() + "\n \n" +
                    mode + COMMA + sequence.toOutput() + COMMA +
                    result.getOutput() + "\n" +
                    printSPSSline();
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

    /* ver 1.2 [psyFeatures]
     * if the mode is SW->SA, print the output in SA->SW order */
    private String printSPSSline(){
        if(mode == SWEET_MODE){
            return "SPSS:" + COMMA + sequence.toOutput() + COMMA + result.printOppositeMode();
        }
        return  "";
    }

    private String printMode(){
        String s = "";
        if(mode == SWEET_MODE){
            s = s + SWEET;
        }else{
            s = s + SALTY;
        }
        return s;
    }

} // End of Class ExperimentData ------------------------------------------- //