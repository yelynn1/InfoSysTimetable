package com.example.infosystimetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity {

    EditText courseName;
    EditText date;
    EditText time;
    EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

//        Intent intent = getIntent();
//        String message  = intent.getStringExtra(MainActivity.extra_message);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(message);
    }

    public void actionSave(View view) {
        courseName = (EditText) findViewById(R.id.courseName);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.classtime);
        location = (EditText) findViewById(R.id.location);

        SharedPreferences myPrefs = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor e = myPrefs.edit();
        e.putString("courseName", courseName.getText().toString()); // add or overwrite someValue
        e.putString("date",date.getText().toString());
        e.putString("classtime",time.getText().toString());
        e.putString("location",location.getText().toString());
        e.commit(); // this saves to disk and notifies observers

    }
}
