package in.fusionbit.shreejeemis;

/**
 * Created by rutvikmehta on 27/01/18.
 */

public class Constants {

    //private static final String HOST = "mis.tapandtype.com";
    private static final String HOST = "192.168.43.161";

    public static final String BASE_URL = "http://" + HOST + "/shreejee_mis/";

    public static final String API_BASE_URL = BASE_URL + "webservice/";
    public static final int TASK_COMPLETION = 0;
    public static final int EFFICIENCY_REPORT = 1;

    public static final int FORM_TYPE_REPORT = 2;

    public class UserRights {
        public static final String CAN_GENERATE_REPORT_FOR_OTHER_USERS = "201";
        public static final String FULL = "7";
    }
}
