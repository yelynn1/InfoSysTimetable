package com.example.infosystimetable;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {


    public static String extra_message = "NTUTimetableIntent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTodayInfo();

        SharedPreferences myPrefs = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor e = myPrefs.edit();
        int count = myPrefs.getInt("courseID",0);
        TextView todayText = (TextView) findViewById(R.id.todayInfo);
        todayText.setText("");

        for(int i=1; i<=count; i++){
            String courseName = myPrefs.getString(String.valueOf(i),"No course added, please go to setting to add course detail");
            setTodayInfo(courseName);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.setting){
            sendMessage();
        }
        return true;
    }

    public void sendMessage(){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }


    private void setTodayInfo(String record){

        String[] splitedRecord = record.split("#");
        String showData = splitedRecord[0] + ". " + splitedRecord[1].toUpperCase() + "\nDate:  " + splitedRecord[2]
                + "\nTime: " + splitedRecord[3] + "\nVenue: " + splitedRecord[4] + "\n";


        TextView todayText = (TextView) findViewById(R.id.todayInfo);

        OffsetDateTime calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            calendar = OffsetDateTime.now();
            String day = String.valueOf(calendar.getDayOfWeek());

            if(day.toUpperCase().equals(splitedRecord[2].toUpperCase())){
                todayText.append(showData);
            }

//            switch (day) {
//                case "SUNDAY":
//                    todayText.setText(R.string.sundayText);
//                    break;
//                case "MONDAY":
//                    todayText.setText(R.string.mondayText);
//                    break;
//                case "TUESDAY":
//                    todayText.setText(R.string.tuesdayText);
//                    break;
//                case "WEDNESDAY":
//                    todayText.setText(R.string.wednesdayText);
//                    break;
//                case "THURSDAY":
//                    todayText.setText(R.string.thursdayText);
//                    break;
//                case "FRIDAY":
//                    todayText.setText(R.string.fridayText);
//                    break;
//                case "SATURDAY":
//                    todayText.setText(R.string.saturdayText);
//                    break;
//                }
        }
    }





}
