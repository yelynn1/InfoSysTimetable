package com.example.infosystimetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class DisplayMessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String courseName;
    String date;
    String time;
    String location;
    String lecturer;
    Spinner chooseCourse;
    SharedPreferences myPrefs;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        myPrefs = getSharedPreferences("myPref", MODE_PRIVATE);
        e = myPrefs.edit();

        chooseCourse = (Spinner) findViewById(R.id.courseID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.course_array, android.R.layout.simple_spinner_dropdown_item);
        chooseCourse.setAdapter(adapter);
        chooseCourse.setOnItemSelectedListener(this);

        ((TextView) findViewById(R.id.addedCourses)).setText("\nYour added courses: \n");
        int count = myPrefs.getInt("courseID",0);
        for(int i=1; i<=count; i++){
            String courseName = myPrefs.getString(String.valueOf(i),"No course added, please go to setting to add course detail");
            String[] splitedData = courseName.split("#");
            ((TextView) findViewById(R.id.addedCourses)).append(splitedData[0] + ". " + splitedData[1] + "\n");
        }


//        Intent intent = getIntent();
//        String message  = intent.getStringExtra(MainActivity.extra_message);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(message);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        try {
            String packageName = getPackageName();
            int resourceId = getResources().getIdentifier(item,"string",packageName);

            String[] detailOfItem =  getString(resourceId).split("#");

            ((EditText) findViewById(R.id.courseName)).setText(detailOfItem[0]);
            ((EditText) findViewById(R.id.date)).setText(detailOfItem[1]);
            ((EditText) findViewById(R.id.classtime)).setText(detailOfItem[2]);
            ((EditText) findViewById(R.id.location)).setText(detailOfItem[3]);
            ((EditText) findViewById(R.id.lecturer)).setText(detailOfItem[4]);
        }
        catch (Exception e){
            //throw exception
            ((EditText) findViewById(R.id.courseName)).setText("");
            ((EditText) findViewById(R.id.date)).setText("");
            ((EditText) findViewById(R.id.classtime)).setText("");
            ((EditText) findViewById(R.id.location)).setText("");
            ((EditText) findViewById(R.id.lecturer)).setText("");
        }

    }
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void actionSave(View view) {


        String selected_Spanner = (String) chooseCourse.getSelectedItem();

        courseName = ((EditText) findViewById(R.id.courseName)).getText().toString();
        date = ((EditText) findViewById(R.id.date)).getText().toString();
        time = ((EditText) findViewById(R.id.classtime)).getText().toString();
        location = ((EditText) findViewById(R.id.location)).getText().toString();
        lecturer = ((EditText) findViewById(R.id.lecturer)).getText().toString();

        int count = myPrefs.getInt("courseID",0);
        if(courseName != ""){
            count++;

            String record = String.valueOf(count) + "#" + courseName+ "#" + date + "#" + time + "#" + location + "#" + lecturer;
            e.putInt("courseID",count);
            e.putString(String.valueOf(count), record); // add or overwrite someValue
            e.commit(); // this saves to disk and notifies observers
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
