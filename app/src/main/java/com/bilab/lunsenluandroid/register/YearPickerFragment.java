package com.bilab.lunsenluandroid.register;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;

public class YearPickerFragment extends DialogFragment {

    private NumberPicker np_year, np_month;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_year_picker, null);

        np_year = view.findViewById(R.id.np_year_picker);
        np_year.setMinValue(1900);
        np_year.setMaxValue(2023);
        np_year.setValue(1980);

        np_month = view.findViewById(R.id.np_month_picker);
        np_month.setMinValue(1);
        np_month.setMaxValue(12);
        np_month.setValue(6);

        return new AlertDialog.Builder(getActivity())
                .setTitle("生日年月")
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
                                Button btn_year = getActivity().findViewById(R.id.btn_year);
                                btn_year.setHint(String.format("%04d/%02d", np_year.getValue(), np_month.getValue()));
                                Person.getInstance().setYear(String.valueOf(np_year.getValue()));                            }
                        })
                .create();
    }
}