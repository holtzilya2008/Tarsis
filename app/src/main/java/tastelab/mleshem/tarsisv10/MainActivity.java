package tastelab.mleshem.tarsisv10;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {
/* -------------------------------- fields *-------------------------------- */

    private MainActivity currentActivity;
    Controller controller = Controller.getInstance();
    private ArrayList<View> views;

    private Button testButton;
    private Experiment current;


/* ---------------------------- Constant Values ---------------------------- */

    private static final String DEF_EMAIL = "holtzilya2008@gmail.com";

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "MainActivity";

/* ----------------------------- Activity Start ---------------------------- */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;
        prepareViews();
        StartExperiment();
    }

    private void prepareViews(){
        views = new ArrayList<View>();
        views.add(findViewById(R.id.nextButton));
        views.add(findViewById(R.id.backButton));
        views.add(findViewById(R.id.tastyBar));
        views.add(findViewById(R.id.tastyText));
        views.add(findViewById(R.id.strongBar));
        views.add(findViewById(R.id.strongText));
        views.add(findViewById(R.id.qproperties));
        views.add(findViewById(R.id.questionText));
        views.add(findViewById(R.id.finishButton));
        views.add(findViewById(R.id.finishLayout));
        views.add(findViewById(R.id.finishText));
        views.add(findViewById(R.id.reviewButton));
        hideAllViews();
    }

    public void hideAllViews(){
        for (View v: views){
            v.setVisibility(View.GONE);
        }
    }

    public Experiment getCurrentExperiment(){
        if (current == null) {
            Log.d(TAG,"BUG MainActivity: Experiment is null");
        }
        return current;
    }
/* ----------------------------- Public Methods ---------------------------- */

    public void StartExperiment(){
        current = new Experiment(this,controller.getExperimentData().getSequence());
        current.Start();
    }

    public void FinishExperiment(){
        if(controller.getExperimentData().isResultPresent()!=true){
            Log.d(TAG,"BUG : FinishExperiment called when result still not set");
            return;
        }
        logResults();
        emailResults();
        final CountDownTimer shutdown = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG,"Activity will close in" + millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                currentActivity.finish();
            }
        }.start();
    }



/* --------------------------- Qverride Methods ---------------------------- */

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void emailResults(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"+DEF_EMAIL));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, DEF_EMAIL);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, controller.getExperimentData().PrintFileName());
        emailIntent.putExtra(Intent.EXTRA_TEXT, controller.getExperimentData().PrintOutput());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "There is no email client installed.");
        }

    }

    private void logResults(){
        String text = controller.getExperimentData().PrintOutput();
        Log.d(TAG,"Output: "+text);
        File logfile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        logfile = new File(logfile,controller.getExperimentData().PrintFileName()+".txt");
        if (!logfile.exists())
        {
            try
            {
                logfile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                Log.d(TAG, "in first catch");
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logfile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            Log.d(TAG, "in second catch");
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(this, new String[]{logfile.toString()}, null, null);
    }

} // End of MainActivity --------------------------------------------------- //

