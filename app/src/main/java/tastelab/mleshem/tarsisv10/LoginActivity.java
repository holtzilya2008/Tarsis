package tastelab.mleshem.tarsisv10;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class LoginActivity extends Activity {
/* -------------------------------- fields *-------------------------------- */

    private LoginActivity currentActivity;
    private ImageButton startButton;


/* ---------------------------- Constant Values ---------------------------- */

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "LoginActivity";

/* ----------------------------- Activity Start ---------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        currentActivity = this;
        prepareViews();
        prepareButtons();
    }

/* ----------------------------- Public Methods ---------------------------- */

/* --------------------------- Qverride Methods ---------------------------- */

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void prepareViews(){

    }

    private void prepareButtons(){
        startButton = (ImageButton)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentActivity,MainActivity.class);
                startActivity(intent);
            }
        });
    }

} // End of LoginActivity --------------------------------------------------- //


