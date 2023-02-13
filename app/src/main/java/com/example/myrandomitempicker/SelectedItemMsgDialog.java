package com.example.myrandomitempicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class SelectedItemMsgDialog extends DialogFragment {
    private String currentSelectedStr;
    private String dialogTitle;
    public SelectedItemMsgDialog(String selected_str, String dlg_caption)
    {
        this.currentSelectedStr = selected_str;
        this.dialogTitle = dlg_caption;
    }

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder dbl = new AlertDialog.Builder(getActivity());
        dbl.setTitle(this.dialogTitle)
            .setMessage(this.currentSelectedStr)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    return;
                }
            }
        );
        return dbl.create();
    }
}
