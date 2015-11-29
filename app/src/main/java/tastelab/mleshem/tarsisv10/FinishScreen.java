package tastelab.mleshem.tarsisv10;

import android.media.Image;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

/**
 * Created by ilya on 11/18/15.
 */
public class FinishScreen {

/* -------------------------------- fields *-------------------------------- */

    private MainActivity activity;
    private FrameLayout finishLayout;
    private ImageButton finishButton;
    private ImageButton reviewButton;
    private TextView finishText;
    private Experiment current;

/* ---------------------------- Constant Values ---------------------------- */

    private static String FINISH_TEXT = "";

/* ---------------------------- DEBUG Environment -------------------------- */

/* ---------------------------- Object Construction ------------------------ */

    public FinishScreen(MainActivity activity) {
        this.activity = activity;
        this.finishButton = (ImageButton) activity.findViewById(R.id.finishButton);
        this.reviewButton = (ImageButton) activity.findViewById(R.id.reviewButton);
        this.finishText = (TextView) activity.findViewById(R.id.finishText);
        this.finishLayout = (FrameLayout) activity.findViewById(R.id.finishLayout);
        this.current = activity.getCurrentExperiment();
        this.FINISH_TEXT = activity.getResources().getString(R.string.finish_text);
        this.FINISH_TEXT = FINISH_TEXT+"?";
        this.finishText.setText(FINISH_TEXT);
        setButtonListeners();
        Hide();
    }
/* ----------------------------- Public Methods ---------------------------- */

    public void Show(){
        finishLayout.setVisibility(View.VISIBLE);
        finishText.setVisibility(View.VISIBLE);
        finishButton.setVisibility(View.VISIBLE);
        reviewButton.setVisibility(View.VISIBLE);
    }

    public void Hide(){
        finishButton.setVisibility(View.GONE);
        reviewButton.setVisibility(View.GONE);
        finishText.setVisibility(View.GONE);
        finishLayout.setVisibility(View.GONE);
    }

/* ----------------------------- Object Methods ---------------------------- */

/* ---------------------------- Private Methods ---------------------------- */

    private void setButtonListeners(){
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide();
                current.Finish();
            }
        });
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide();
                current.Review();
            }
        });
    }

} // End of Class X -------------------------------------------------------- //


