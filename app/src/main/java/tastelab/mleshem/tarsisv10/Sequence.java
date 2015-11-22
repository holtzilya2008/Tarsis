package tastelab.mleshem.tarsisv10;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by ilya on 11/18/15.
 */
public class Sequence {
/* -------------------------------- fields *-------------------------------- */

    private int id;
    private int tarsisim[];

/* ---------------------------- Constant Values ---------------------------- */

    public static final int SIZE = 6;

    //Sequences
    private static final int SEQ1[] = {1,5,3,6,4,2};
    private static final int SEQ2[] = {2,4,6,3,5,1};
    private static final int SEQ3[] = {3,5,2,4,1,6};

/* ---------------------------- DEBUG Environment -------------------------- */

    private static final String TAG = "Sequence";

/* ---------------------------- Object Construction ------------------------ */

    public Sequence(int id) {
        this.id = id;
        constructSequence();
    }

/* ----------------------------- Public Methods ---------------------------- */

    public int getId() {
        return id;
    }

    public int[] getTarsisim() {
        return tarsisim;
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
        return "Sequence{" +
                "id=" + id +
                ", tarsisim=" + Arrays.toString(tarsisim) +
                '}';
    }

/* ---------------------------- Private Methods ---------------------------- */

    private void constructSequence(){
        switch(id) {
            case 1: this.tarsisim = SEQ1; return;
            case 2: this.tarsisim = SEQ2; return;
            case 3: this.tarsisim = SEQ3; return;
            default:
                Log.d(TAG,"BUG: Unknown Sequence!");
        }
    }

} // End of Class Sequence -------------------------------------------------- //

