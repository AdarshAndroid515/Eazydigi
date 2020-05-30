package com.app.eazydigi.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPref {
    private static final String SHARED_PREF_NAME = "safepe_pref";
    private Context context;

    public final String ACCESS_TOKEN = "accessToken";
    public final String DEVICE_NAME = "DeviceName";
    public final String USER_ID = "userId";
    public final String USER_EMAIL = "userEmail";
    public final String USER_FIRST_NAME = "User_first_name";
    public final String USER_LAST_NAME = "User_last_name";
    public final String MOBILE = "mobile";

    public final String TODAY_COLLECTION = "TodayCollection";
    public final String TOTAL_EXPENSE = "TotalExpense";
    public final String TOTAL_BUS = "TotalBus";
    public final String TOTAL_VAN= "TotalVan";
    public final String TOTAL_OTHER_VEHICLE = "TotalOtherVehicle";
    public final String TOTAL_STUDENTS= "Total_students";
    public final String TOTAL_PARENTS= "Total_parents";
    public final String TOTAL_TEACHERS= "Total_teachers";
    public final String TOTAL_STAFF= "Total_staff";

    public final String TEACHER_TOTAL_STUDENT = "teacher_total_student";
    public final String TEACHER_TOTAL_ABSENT = "teacher_total_absent";
    public final String TEACHER_TOTAL_PRESENT = "teacher_total_present";
    public final String TEACHER_CLASS = "teacher_class";
    public final String TEACHER_SECTION = "teacher_sec";
    public final String IS_CLASS_TEACHER = "class_teacher";

    public final String STUDENT_FEES_PENDING = "student_fee_pending";
    public final String STUDENT_FEES_PAID = "student_fee_paid";
    public final String STUDENT_MY_PRESENT = "student_present";
    public final String STUDENT_MY_ABSENT = "student_absent";
    public final String STUDENT_ATTENDANCE_PER = "student_attendance_per";

    public SharedPref(Context context) {
        this.context = context;
    }

    private static SharedPreferences getUserSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public Long getLong(String key) {
        return getUserSharedPreferences(context).getLong(key, 0);
    }

    public boolean getBoolean(String key) {
        return getUserSharedPreferences(context).getBoolean(key, false);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }


    public String getString(String key) {
        return getUserSharedPreferences(context).getString(key, null);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return getUserSharedPreferences(context).getInt(key, 0);
    }

    public void setObject(String key, String value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
//        editor.putString(key, new Gson().toJson(value));
        editor.putString(key, value);
        editor.commit();
    }

    public Object getObject(String key, final Class<?> aClass) {
        return new Gson().fromJson(getString(key), aClass);
    }
}
