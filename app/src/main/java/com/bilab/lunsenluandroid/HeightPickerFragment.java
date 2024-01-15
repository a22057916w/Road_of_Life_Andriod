package com.bilab.lunsenluandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;


public class HeightPickerFragment extends DialogFragment {
    private NumberPicker height_picker, cm_picker;

    public HeightPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = (View)inflater.inflate(R.layout.fragment_height_picker, null);

        // Initialize the pickers
        height_picker = (NumberPicker) view.findViewById(R.id.value_picker);
        height_picker.setMinValue(130);
        height_picker.setMaxValue(220);
        height_picker.setValue(155);

        cm_picker = (NumberPicker) view.findViewById(R.id.unit_picker);
        String[] unit = new String[]{"cm"};
        cm_picker.setDisplayedValues(unit);

        Dialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("身高")
                .setView(view)
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                .setPositiveButton("確認",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Button btn_height = getActivity().findViewById(R.id.btn_height);
                                btn_height.setHint(String.format("%scm", height_picker.getValue()));
                                Person.getInstance().setHeight(String.valueOf(height_picker.getValue()));
                            }
                        })
                .create();
        return alertDialog;
    }

    public int getHeight() {
        return height_picker.getValue();
    }
}