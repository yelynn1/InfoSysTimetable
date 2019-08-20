package com.example.infosystimetable;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.OffsetDateTime;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTodayInfo();

    }


    private void setTodayInfo(){
        TextView todayText = (TextView) findViewById(R.id.todayInfo);
        OffsetDateTime calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            calendar = OffsetDateTime.now();
            String day = String.valueOf(calendar.getDayOfWeek());
            todayText.setText(day);
            switch (day) {
                case "SUNDAY":
                    todayText.setText(R.string.sundayText);
                    break;
                case "MONDAY":
                    todayText.setText(R.string.mondayText);
                    break;
                case "TUESDAY":
                    todayText.setText(R.string.tuesdayText);
                    break;
                case "WEDNESDAY":
                    todayText.setText(R.string.wednesdayText);
                    break;
                case "THURSDAY":
                    todayText.setText(R.string.thursdayText);
                    break;
                case "FRIDAY":
                    todayText.setText(R.string.fridayText);
                    break;
                case "SATURDAY":
                    todayText.setText(R.string.saturdayText);
                    break;
                }
        }
    }





}
