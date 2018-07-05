package com.example.thakursaurabh.meetingreminder;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddSchedule extends AppCompatActivity {
    static int day,month,year,hourOfDay,minute;
    DatabaseHandler db = DatabaseHandler.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        final EditText topic = findViewById(R.id.topic);
        final EditText duration = findViewById(R.id.duration);
        Button reminderBtn = findViewById(R.id.setReminder);
        Button addBtn = findViewById(R.id.addReminder);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Insert: ", "Inserting .."+ topic.getText().toString()+duration.getText().toString());
//                db.addMeeting(new Meeting(topic.getText().toString(), duration.getText().toString(),day+"/"+month+"/"+year, hourOfDay+":"+minute));
                Log.d("Reading: ", "Reading all contacts.."+day+"/"+month+"/"+year);
                Log.d("Reading: ", "Reading all contacts.."+hourOfDay+":"+minute);
            }
        });
        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inserting Contacts
                Log.i("DATE",year+" "+month+" "+day);
                createAlarm("TESTING",hourOfDay,minute);
            }
        });
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_DAYS,6)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}