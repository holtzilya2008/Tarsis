package tastelab.mleshem.tarsisv10;

import android.util.Log;

/**
 * Created by ilya on 11/22/15.
 */
public class Controller {
/* ------------------------------ Singleton *------------------------------- */

    private static Controller ourInstance = new Controller();
    public static Controller getInstance() {
        return ourInstance;
    }

/* -------------------------------- fields *-------------------------------- */

    //Activities
    public LoginActivity loginActivity;
    public MainActivity mainActivity;
    private boolean isActivityPresent[] = {false,false};

    //Experiment
    private ExperimentData experimentData;
    private boolean isExperimentDataSet = false;

/* ---------------------------- Constant Values ---------------------------- */

    public static final int ACTIVITIES = 2;
    public static final int LOGIN_ACTIVITY_ID = 0;
    public static final int MAIN_ACTIVITY_ID = 1;

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Controller";

/* ----------------------- Singleton Construction -------------------------- */

    private Controller() {
        this.mainActivity = null;
        this.loginActivity = null;
        this.experimentData = null;
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void setExperimentData(String experimentor,
                                  String subject,
                                  String dateAndTime,
                                  int mode,
                                  Sequence sequence){
        this.experimentData = new ExperimentData(experimentor,subject,dateAndTime,mode,sequence);
        this.isExperimentDataSet = true;
    }

    public ExperimentData getExperimentData(){
        if(isExperimentDataSet){
            return experimentData;
        }
        Log.d(TAG,"BUG trying to get Experiment Data when is not set!");
        return null;
    }

    public void setLoginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        if (loginActivity == null){
            this.isActivityPresent[LOGIN_ACTIVITY_ID] = false;
            return;
        }
        this.isActivityPresent[LOGIN_ACTIVITY_ID] = true;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        if(mainActivity == null){
            this.isActivityPresent[MAIN_ACTIVITY_ID] = false;
            return;
        }
        isActivityPresent[MAIN_ACTIVITY_ID] = true;
    }

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

} // End of Class Controller ----------------------------------------------- //










