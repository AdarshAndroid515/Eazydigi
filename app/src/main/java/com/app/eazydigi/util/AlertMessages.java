package com.app.eazydigi.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;

import androidx.appcompat.app.AlertDialog;

import com.app.eazydigi.R;

public class AlertMessages {
    static Context context;

    static AlertDialogCallback alertDialogCallback;

    public AlertMessages(Context c) {

        context = c;

    }

    public interface AlertDialogCallback {

        public void clickedButtonText(String s);

    }

    public static void showErrorInConnection(Activity context) {

        showMessage(context, null, context.getString(R.string.alert_check_connection), context.getString(R.string.ok), null, null, new AlertDialogCallback() {
            @Override
            public void clickedButtonText(String s) {
            }
        });

    }

    public static void showErrorInConnection(Activity context, final AlertDialogCallback alertDialogCallback) {

        showMessage(context, null, context.getString(R.string.alert_check_connection), context.getString(R.string.retry), null, null, new AlertDialogCallback() {
            @Override
            public void clickedButtonText(String s) {
                alertDialogCallback.clickedButtonText(s);
            }
        });

    }

    public static void showMessageToOffMobileData(Activity context, String title, String message, String pos, String neg, final AlertDialogCallback alertDialogCallback) {

        showMessage(context, title, message, pos, neg, null, new AlertDialogCallback() {
            @Override
            public void clickedButtonText(String s) {
                alertDialogCallback.clickedButtonText(s);
            }
        });

    }

    public static void showCustomMessage(Activity context, String title, String message) {

        showMessage(context, title, message, context.getString(R.string.ok), null, null, new AlertDialogCallback() {
            @Override
            public void clickedButtonText(String s) {
            }
        });

    }

    static AlertDialog dialog = null;

    public static void showMessage(final Context context, String title, String message, final String positiveBtn, final String negativeBtn, final String neutralBtn, final AlertDialogCallback alertDialogCallback) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        if (title != null && title.length() > 0) {
            alertDialog.setTitle(title);
        }

        alertDialog.setMessage(Html.fromHtml(message));

        alertDialog.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                alertDialogCallback.clickedButtonText(positiveBtn);

            }
        });

        if (negativeBtn != null) {
            alertDialog.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    alertDialogCallback.clickedButtonText(negativeBtn);

                }
            });
        }

        if (neutralBtn != null) {
            alertDialog.setNeutralButton(neutralBtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                    alertDialogCallback.clickedButtonText(neutralBtn);
                }
            });
        }
        alertDialog.setCancelable(false);

        final AlertDialog dialog = alertDialog.show();

    }
}