package tastelab.mleshem.tarsisv10;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by ilya on 11/18/15.
 */
public class Sequence {
/* -------------------------------- fields *-------------------------------- */

    private int id;
    private String string;
    private int tarsisim[];

/* ---------------------------- Constant Values ---------------------------- */

    public static final int SIZE = 6;
    private static final int BAD_ID = 404;

    //Sequences
    public static final int SEQ1_ID = 1;
    private static final int SEQ1[] = {1,5,3,6,4,2};
    public static final String SEQ1_STR = "1->5->3->6->4->2";

    public static final int SEQ2_ID = 2;
    private static final int SEQ2[] = {2,4,6,3,5,1};
    public static final String SEQ2_STR = "2->4->6->3->5->1";

    public static final int SEQ3_ID = 3;
    private static final int SEQ3[] = {3,5,2,4,1,6};
    public static final String SEQ3_STR = "3->5->2->4->1->6";



/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Sequence";

/* ---------------------------- Object Construction ------------------------ */

    public Sequence(int id) {
        this.id = id;
        constructSequence();
    }

    public Sequence(String str){
        switch (str){
            case SEQ1_STR: this.id = SEQ1_ID; break;
            case SEQ2_STR: this.id = SEQ2_ID; break;
            case SEQ3_STR: this.id = SEQ3_ID; break;
            default: this.id = BAD_ID; break;
        }
        constructSequence();
    }

/* ----------------------------- Public Methods ---------------------------- */

    public int getId() {
        return id;
    }

    public int[] getTarsisim() {
        return tarsisim;
    }

    public String toOutput(){
        return "" + Helper.printZeros(id)+id;
    }

/* ----------------------------- Object Methods ---------------------------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sequence sequence = (Sequence) o;

        if (id != sequence.id) return false;
        return Arrays.equals(tarsisim, sequence.tarsisim);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result +
                (tarsisim != null ? Arrays.hashCode(tarsisim) : 0);
        return result;
    }

    @Override
    public String toString() {
        return string;
    }

/* ---------------------------- Private Methods ---------------------------- */

    private void constructSequence(){
        switch(id) {
            case SEQ1_ID:
                this.tarsisim = SEQ1;
                this.string = SEQ1_STR;
                return;
            case SEQ2_ID:
                this.tarsisim = SEQ2;
                this.string = SEQ2_STR;
                return;
            case SEQ3_ID:
                this.tarsisim = SEQ3;
                this.string = SEQ3_STR;
                return;
            default:
                Log.d(TAG,"BUG: Unknown Sequence!");
                this.tarsisim = SEQ1;
                this.string = SEQ1_STR;
        }
    }


} // End of Class Sequence -------------------------------------------------- //

