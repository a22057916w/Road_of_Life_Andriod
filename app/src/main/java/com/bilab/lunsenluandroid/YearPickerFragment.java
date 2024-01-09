package com.bilab.lunsenluandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

public class YearPickerFragment extends DialogFragment {

    private NumberPicker np_year;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_year_picker, null);

        np_year = view.findViewById(R.id.np_year_picker);
        np_year.setMinValue(1900);
        np_year.setMaxValue(2023);
        np_year.setValue(1980);

        return new AlertDialog.Builder(getActivity())
                .setTitle("年份")
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
                                TextView tv_year = getActivity().findViewById(R.id.tv_year);
                                tv_year.setText(String.format("%s", np_year.getValue()));
                                Person.getInstance().setYear(String.valueOf(np_year.getValue()));                            }
                        })
                .create();
    }
}