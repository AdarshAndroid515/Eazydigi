package com.app.eazydigi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.eazydigi.model.old_model.LoginResponseInfo;
import com.google.gson.Gson;

public class PreferenceUtils {

    public static final String TAG = "PREFERENCES";

    public static final String APP_DEFAULTS = "EAZYDIGI";

    public static SharedPreferences getPreferences(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(APP_DEFAULTS, Context.MODE_PRIVATE);
        return preferences;
    }

    public static void saveStringToUserDefaults(Context context, String key, String value) {

        Log.e(TAG, "SAVING : " + key + " : " + value);
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringFromUserDefaults(Context context, String key) {

        SharedPreferences preferences = context.getSharedPreferences(
                APP_DEFAULTS, Context.MODE_PRIVATE);
        return preferences.getString(key, null);

    }

    public static void saveIntToUserDefaults(Context context, String key, int value) {

        Log.e(TAG, "SAVING : " + key + " : " + value);
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntFromUserDefaults(Context context, String key) {

        SharedPreferences preferences = getPreferences(context);
        return preferences.getInt(key, 0);

    }

    public static void clearUserDefaults(Context context) {
        SharedPreferences preferences = getPreferences(context);
        preferences.edit().clear().apply();

    }

    public static void saveUserIdToDefaults(Context context, int value) {
        saveIntToUserDefaults(context, Constants.PrefKeys.KEY_USERID, value);
    }

    public static int getUserIdFromDefaults(Context context) {
        return getIntFromUserDefaults(context, Constants.PrefKeys.KEY_USERID);
    }

    public static void saveSchoolIdToDefaults(Context context, int value) {
        saveIntToUserDefaults(context, Constants.PrefKeys.KEY_SCHOOLID, value);
    }

    public static int getSchoolIdFromDefaults(Context context) {
        return getIntFromUserDefaults(context, Constants.PrefKeys.KEY_SCHOOLID);
    }

    public static void saveeUserInfoToDefaults(Context context, LoginResponseInfo loginResponseInfo) {

        saveStringToUserDefaults(context, Constants.PrefKeys.KEY_USERINFO, new Gson().toJson(loginResponseInfo));
        saveSchoolIdToDefaults(context, loginResponseInfo.getSchoolId());
        saveUserIdToDefaults(context, loginResponseInfo.getUserId());

    }

    public static LoginResponseInfo getUserInfoFromDefaults(Context context) {

        String loginResponseString = PreferenceUtils.getStringFromUserDefaults(context, Constants.PrefKeys.KEY_USERINFO);
        if (loginResponseString != null) {
            LoginResponseInfo loginResponseInfo = new Gson().fromJson(loginResponseString, LoginResponseInfo.class);
            return loginResponseInfo;
        } else {
            return null;
        }

    }

    public static void saveUerRoleIdToDefaults(Context context, int value) {
        saveIntToUserDefaults(context, Constants.PrefKeys.KEY_USER_ROLEID, value);
    }

    public static int getUserRoleIdFromDefaults(Context context) {
        return getIntFromUserDefaults(context, Constants.PrefKeys.KEY_USER_ROLEID);
    }
}
