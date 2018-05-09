package com.example.karthik.myorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    TextView dateText;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        simpleCalendarView.setDate(Calendar.getInstance().getTimeInMillis(),false,true);
        dateText = (TextView) findViewById(R.id.dateText);

        sdf = new SimpleDateFormat("MM/dd/yyyy");
        dateText.setText("Date: " + sdf.format(new Date()));

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Calendar gcal = GregorianCalendar.getInstance();
                gcal.set(year, month, dayOfMonth);
                dateText.setText("Date: " + sdf.format(gcal.getTime()));

            }
        });

    }
}
