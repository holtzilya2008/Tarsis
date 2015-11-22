package tastelab.mleshem.tarsisv10;

/**
 * Created by ilya on 11/22/15.
 */
public class Controller {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }
}
