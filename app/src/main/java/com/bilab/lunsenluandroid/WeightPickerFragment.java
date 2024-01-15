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

public class WeightPickerFragment extends DialogFragment {
    private NumberPicker weight_picker, kg_picker;
    public WeightPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = (View)inflater.inflate(R.layout.fragment_height_picker, null);

        // Initialize the pickers
        weight_picker = (NumberPicker) view.findViewById(R.id.value_picker);
        weight_picker.setMinValue(20);
        weight_picker.setMaxValue(120);
        weight_picker.setValue(50);

        kg_picker = (NumberPicker) view.findViewById(R.id.unit_picker);
        String[] unit = new String[]{"kg"};
        kg_picker.setDisplayedValues(unit);

        Dialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("體重")
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
                                Button btn_weight = getActivity().findViewById(R.id.btn_weight);
                                btn_weight.setHint(String.format("%skg", weight_picker.getValue()));
                                Person.getInstance().setWeight(String.valueOf(weight_picker.getValue()));
                            }
                        })
                .create();
        return alertDialog;
    }
}