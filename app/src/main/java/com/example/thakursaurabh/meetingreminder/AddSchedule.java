package com.example.thakursaurabh.meetingreminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
                int flag = 0;

                if(TextUtils.isEmpty(topic.getText().toString())){
                    topic.setError("Topic Empty");
                    flag=1;
                }
                if(TextUtils.isEmpty(duration.getText().toString())){
                    duration.setError("Duration empty");
                    flag=1;
                }
                if(flag==1) {
                    return;
                }
                Log.d("Insert: ", "Inserting .."+ topic.getText().toString()+duration.getText().toString());
                db.addMeeting(new Meeting(topic.getText().toString(), duration.getText().toString(),day+"/"+month+"/"+year, hourOfDay+":"+minute));
                Intent viewSchedule = new Intent(AddSchedule.this,ViewSchedule.class);
                startActivity(viewSchedule);
                finish();
            }
        });
        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 0;

                if(TextUtils.isEmpty(topic.getText().toString())){
                    topic.setError("Topic Empty");
                    flag=1;
                }
                if(TextUtils.isEmpty(duration.getText().toString())){
                    duration.setError("Duration empty");
                    flag=1;
                }
                if(flag==1) {
                    return;
                }

                Log.i("DATE",year+" "+month+" "+day);
                Calendar calNow = Calendar.getInstance();
                Calendar calSet = (Calendar) calNow.clone();

                calSet.clear();
                calSet.set(year,month-1,day,hourOfDay,minute);

                int year = calSet.get(Calendar.YEAR);
                int month = calSet.get(Calendar.MONTH);      // 0 to 11
                int day = calSet.get(Calendar.DAY_OF_MONTH);
                int hour = calSet.get(Calendar.HOUR_OF_DAY);
                int minute = calSet.get(Calendar.MINUTE);
                int second = calSet.get(Calendar.SECOND);

                Log.d("TIMENOW: ",  year +" "+ month +" "+ day+" "+ hour +" "+ minute +" "+ second);

                setAlarm(calSet);
            }
        });

    }

    private void setAlarm(Calendar targetCal){

        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

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