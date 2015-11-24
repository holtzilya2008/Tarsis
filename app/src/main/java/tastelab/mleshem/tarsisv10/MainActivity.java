package tastelab.mleshem.tarsisv10;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity {
/* -------------------------------- fields *-------------------------------- */

    private MainActivity currentActivity;
    private ArrayList<View> views;

    private Button testButton;
    private Experiment current;


/* ---------------------------- Constant Values ---------------------------- */

/* ---------------------------- DEBUG Environment -------------------------- */

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
        views.add(findViewById(R.id.finishQeustion));
        for (View v: views){
            v.setVisibility(View.GONE);
        }
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void StartExperiment(){
        current = new Experiment(this,new Sequence(1),0);
        current.Start();
    }

    public void FinishExperiment(){
        final CountDownTimer shutdown = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

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

} // End of MainActivity --------------------------------------------------- //
