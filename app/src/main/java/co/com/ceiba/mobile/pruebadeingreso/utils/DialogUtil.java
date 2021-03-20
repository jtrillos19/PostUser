package co.com.ceiba.mobile.pruebadeingreso.utils;

import android.app.AlertDialog;
import android.content.Context;

import dmax.dialog.SpotsDialog;

public class DialogUtil {
    private DialogUtil() {
    }

    private static AlertDialog dialog;

    public static void showDialog(Context context) {
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(context).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
    }

    public static void dismissDailog() {
        dialog.dismiss();
    }

    public static AlertDialog getDialog() {
        return dialog;
    }
}
