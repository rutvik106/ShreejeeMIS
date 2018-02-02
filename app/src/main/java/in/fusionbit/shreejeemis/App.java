package in.fusionbit.shreejeemis;

import android.app.Application;

import in.fusionbit.shreejeemis.apimodels.UserModel;

/**
 * Created by rutvikmehta on 27/01/18.
 */

public class App extends Application {

    public static final String APP_TAG = "SMIS ";

    private static UserModel currentUser;

    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserModel currentUser) {
        App.currentUser = currentUser;
    }
}
