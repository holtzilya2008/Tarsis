package tastelab.mleshem.tarsisv10;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity {
/* -------------------------------- fields *-------------------------------- */

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
        prepareViews();
        current = new Experiment(this,new Sequence(1),0);
        setTest();
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

    private void setTest(){
        testButton = (Button)findViewById(R.id.testButton);
        testButton.setVisibility(View.VISIBLE);
        testButton.setClickable(true);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testButton.setVisibility(View.GONE);
                testButton.setClickable(false);
                current.Start();
            }
        });
    }

/* ----------------------------- Public Methods ---------------------------- */

    public void restartTest(){
        prepareViews();
        testButton = (Button)findViewById(R.id.testButton);
        testButton.setVisibility(View.VISIBLE);
        testButton.setClickable(true);
    }

/* --------------------------- Qverride Methods ---------------------------- */

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

} // End of MainActivity --------------------------------------------------- //
