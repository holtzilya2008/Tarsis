package tastelab.mleshem.tarsisv10;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class LoginActivity extends Activity {
/* -------------------------------- fields *-------------------------------- */

    private LoginActivity currentActivity;
    public Controller controller = Controller.getInstance();

    private ImageButton startButton;

    private EditText editExptor;
    private String exptorText;
    private EditText editSubject;
    private String subjectText;

    private ToggleButton modeButton;
    private int mode;

    private Spinner sequenceSpinner;
    ArrayList<String> sequences;
    int seqIndex;

/* ---------------------------- Constant Values ---------------------------- */

    private static final int NOTHING_CHOSEN = -1;

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "LoginActivity";

/* ----------------------------- Activity Start ---------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        currentActivity = this;
        exptorText = "";
        subjectText = "";
        mode = ExperimentData.SALTY_MODE;
        seqIndex = NOTHING_CHOSEN;
        prepareSpinner();
        prepareViews();
        prepareButtons();
        editExptor.requestFocus();
    }

/* ----------------------------- Public Methods ---------------------------- */

/* --------------------------- Override Methods ---------------------------- */

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void prepareViews(){
        this.editExptor = (EditText)findViewById(R.id.editExptor);
        editExptor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkIfAllFilled();
            }
        });
        this.editExptor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkIfAllFilled();
                return false;
            }
        });
        this.editSubject = (EditText)findViewById(R.id.editSbject);
        this.editSubject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkIfAllFilled();
            }
        });
        this.editSubject.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkIfAllFilled();
                return false;
            }
        });
    }

    private void prepareButtons(){
        startButton = (ImageButton)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareExperiment();
                Intent intent = new Intent(currentActivity, MainActivity.class);
                startActivity(intent);
            }
        });
        startButton.setVisibility(View.GONE);
        this.modeButton = (ToggleButton)findViewById(R.id.toogleOrder);
        this.modeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkIfAllFilled();
            }
        });
        this.modeButton.setVisibility(View.VISIBLE);
        this.modeButton.bringToFront();
    }

    private void prepareSpinner(){
        seqIndex = NOTHING_CHOSEN;
        sequences = new ArrayList<>();
        sequences.add(Sequence.SEQ1_STR);
        sequences.add(Sequence.SEQ2_STR);
        sequences.add(Sequence.SEQ3_STR);
        sequenceSpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_view,sequences);
        sequenceSpinner.setAdapter(adapter);
        sequenceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seqIndex = position;
                checkIfAllFilled();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                seqIndex = NOTHING_CHOSEN;
            }
        });
        sequenceSpinner.setVisibility(View.VISIBLE);
        sequenceSpinner.bringToFront();
    }

    private boolean checkIfAllFilled(){
        Log.d(TAG,"Reached checkIfAllFilled");
        if( (seqIndex != NOTHING_CHOSEN)&&
           (!(editExptor.getText().toString().isEmpty()))&&
           (!(editSubject.getText().toString().isEmpty())) ){
           saveFeilds();
           startButton.setVisibility(View.VISIBLE);
           startButton.bringToFront();
           return true;
        }
        startButton.setVisibility(View.GONE);
        return false;
    }

    private void saveFeilds(){
        exptorText = editExptor.getText().toString();
        subjectText = editSubject.getText().toString();
        if(modeButton.isChecked()){
            mode = ExperimentData.SWEET_MODE;
        }else{
            mode = ExperimentData.SALTY_MODE;
        }
    }

    private void prepareExperiment(){
        if(!checkIfAllFilled()){
            Log.d(TAG, "BUG: reached prepareExperiment when not all filled");
            return;
        }
        saveFeilds();
        String dateAndTime = getDateAndTime();
        Sequence seq = new Sequence(sequences.get(seqIndex));
        controller.setExperimentData(exptorText,subjectText,dateAndTime,mode,seq);
    }

    private String getDateAndTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault());
        return dateFormat.format(cal.getTime());
    }

} // End of LoginActivity --------------------------------------------------- //

/*
public String getCurrentTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return timeFormat.format(cal.getTime());
    }



 */
