package in.fusionbit.shreejeemis.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by rutvikmehta on 29/12/17.
 */

public class SharedPreferences {

    public static void saveUsernamePassword(final Context context,
                                            final String username,
                                            final String password) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(Name.USERNAME, username)
                .putString(Name.PASSWORD, password)
                .apply();
    }

    public static String[] getUsernamePassword(final Context context) {
        final String username = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Name.USERNAME, null);

        final String password = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Name.PASSWORD, null);

        final String[] credentials = {username, password};

        if (credentials[0] == null || credentials[1] == null) {
            return null;
        }
        return credentials;
    }

    class Name {
        static final String USERNAME = "27087b329deeade828edd652d45461b2";
        static final String PASSWORD = "319f4d26e3c536b5dd871bb2c52e3178";
    }

}
