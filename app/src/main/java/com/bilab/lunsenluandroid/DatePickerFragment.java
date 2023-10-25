package com.bilab.lunsenluandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String date = "";



    @Override
    public DatePickerDialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        setDate(year, month, day);
        TextView birthday_tv = getActivity().findViewById(R.id.birthday_textview);
        birthday_tv.setText((getDate()));
    }

    public String getDate() {
        return date;
    }
    private void setDate(int year, int month, int day) {
        date = String.valueOf(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}