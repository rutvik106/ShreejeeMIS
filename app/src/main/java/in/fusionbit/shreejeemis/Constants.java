package in.fusionbit.shreejeemis;

/**
 * Created by rutvikmehta on 27/01/18.
 */

public class Constants {

    private static final String HOST = "mis.tapandtype.com";

    public static final String BASE_URL = "http://" + HOST + "/";

    public static final String API_BASE_URL = BASE_URL + "webservice/";
    public static final int TASK_COMPLETION = 0;
    public static final int EFFICIENCY_REPORT = 1;

    public class UserRights {
        public static final String CAN_GENERATE_REPORT_FOR_OTHER_USERS = "201";
        public static final String FULL = "7";
    }
}
