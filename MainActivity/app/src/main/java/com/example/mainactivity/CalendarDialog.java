package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mainactivity.databinding.ActivityCalendarBinding;

import org.jetbrains.annotations.NotNull;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarDialog extends AppCompatDialogFragment {

    private CalendarView calendarView;
    private String dateSelectedTxt, dateSelectedIndex;

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ActivityCalendarBinding binding = ActivityCalendarBinding.inflate(LayoutInflater.from(getContext()));
        builder.setView(binding.getRoot())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        calendarView = binding.calendarView;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateSelectedIndex = year + "-" + (month + 1) + "-" + dayOfMonth;
                dateSelectedTxt = parseDate(dateSelectedIndex);

                Bundle bundle = new Bundle();
                bundle.putString("dateIndex", dateSelectedIndex);
                bundle.putString("dateSelectedTxt", dateSelectedTxt);


                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                intent.putExtra("DATE_SELECTED_DATA", bundle);
                getActivity().startActivity(intent);
            }

        });
        return builder.create();
    }

    private String parseDate(String dateSelected) {
        String rawDate = dateSelected;
        SimpleDateFormat initDate = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = initDate.parse(rawDate, new ParsePosition(0));
        SimpleDateFormat dateTxt = new SimpleDateFormat("EEE, MMM d yyyy");
        return dateTxt.format(formattedDate);
    }
}