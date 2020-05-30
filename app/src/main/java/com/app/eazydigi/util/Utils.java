package com.app.eazydigi.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public static boolean isConnectedToInternet(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAuthorization(){
        return "Basic aGVyb2t1OkFCU0hpbmRpQDEyMw==";
    }

    public static int getModuleImage(Context context, String iconName) {

        iconName = iconName.replaceAll("\\s+", "_");
        return context.getResources().getIdentifier("ic_" + iconName.toLowerCase(), "drawable", context.getPackageName());
    }

    public static String convertToDateString(String sourceDateString, String inputFormat, String outputFormat) {

        try {

            SimpleDateFormat sourceSDF = new SimpleDateFormat(inputFormat);

            Date date = sourceSDF.parse(sourceDateString);

            SimpleDateFormat outputDate = new SimpleDateFormat(outputFormat);

            sourceDateString = outputDate.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceDateString;
    }

    public static String convertToDateString(long currentTime, String outputFormat) {

        String sourceDateString = null;

        try {

            Date date = new Date(currentTime);

            SimpleDateFormat outputDate = new SimpleDateFormat(outputFormat);

           sourceDateString = outputDate.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceDateString;
    }
    public static String loadJSONFromAsset(Context context,String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static void showAlertDialog(Context context,String Title,String Message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(Title);
        alertDialogBuilder.setMessage(Message);


        alertDialogBuilder.setPositiveButton("Ok", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showAlertDialogFinish(Context context,String Title,String Message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(Title);
        alertDialogBuilder.setMessage(Message);


        alertDialogBuilder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.cancel();
            ((Activity)context).finish();
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
