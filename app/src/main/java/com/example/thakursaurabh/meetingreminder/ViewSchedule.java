package com.example.thakursaurabh.meetingreminder;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewSchedule extends AppCompatActivity {

    private ListView listView;
    private MeetingAdapter meetingAdapter;

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

        listView = findViewById(R.id.list);
        meetingAdapter = new MeetingAdapter(this,meetings);
        listView.setAdapter(meetingAdapter);
    }
}
