package com.example.thakursaurabh.meetingreminder;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ViewSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        ArrayList<Meeting> meetings = db.getAllMeetings();

        for (Meeting mtng : meetings) {
            String log = "Topic: " + mtng.getTopic() + " ,Duration: " + mtng.getDuration() + " ,Date: " +
                    mtng.getDate() + " ,Time: " + mtng.getTime();
            // Writing Contacts to log
            Log.d("TODOS: ", log);
        }
    }
}
