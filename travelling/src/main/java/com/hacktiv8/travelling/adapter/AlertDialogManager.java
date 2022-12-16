package com.hacktiv8.travelling.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.hacktiv8.travelling.R;

public class AlertDialogManager {

    public void showAlertDialog(Context context, String title, String message, Boolean status) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status != null)
            alertDialog.setIcon((status) ? R.drawable.ic_success : R.drawable.ic_fail);
        alertDialog.setButton("OK", (dialog, which) -> {
        });
        alertDialog.show();
    }
}