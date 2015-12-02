package tastelab.mleshem.tarsisv10;

/**
 * Created by ilya on 11/25/15.
 */
public class Helper {
/* ------------------------------ Singleton -------------------------------- */

    private static Helper ourInstance = new Helper();

    public static Helper getInstance() {
        return ourInstance;
    }

/* -----------------------------static fields *----------------------------- */

/* ---------------------------- Constant Values ---------------------------- */

    public static final int MAX_ZEROS = 2;

    //MODE
    public static final int SALTY_MODE = 0;
    public static final String SALTY = "Salty";
    public static final int SWEET_MODE = 1;
    public static final String SWEET = "Sweet";
    public static final String COMMA = ",";

/* ---------------------------- DEBUG Environment -------------------------- */

/* -------------------------- Singleton Construction ----------------------- */

    private Helper() {
    }

/* -------------------------- Public static Methods ------------------------ */

    public static String printZeros(int num){
        int tmp = num;
        String zeros = "";
        int count = 0;
        while((tmp/=10)!=0){
            count++;
        }
        for(int i=0;i<MAX_ZEROS-count;i++){
            zeros = zeros +"0";
        }
        return zeros;
    }

/* ---------------------------- Private Methods ---------------------------- */

} // End of Class Helper --------------------------------------------------- //


