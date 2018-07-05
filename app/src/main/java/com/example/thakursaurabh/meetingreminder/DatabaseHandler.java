package com.example.thakursaurabh.meetingreminder;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "meetings";
    private static final String TABLE_MEETINGS = "meetings";
    private static final String KEY_ID = "id";
    private static final String KEY_TOPIC = "topic";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static DatabaseHandler db;

    public static DatabaseHandler getInstance(Context context) {
        if(db == null) {
            db = new DatabaseHandler(context);
        }
        return  db;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MEETINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TOPIC + " TEXT,"
                + KEY_DURATION + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETINGS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new meeting
    void addMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TOPIC, meeting.getTopic());
        values.put(KEY_DURATION, meeting.getDuration());
        values.put(KEY_TIME, meeting.getTime());
        values.put(KEY_DATE, meeting.getDate());

        // Inserting Row
        db.insert(TABLE_MEETINGS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all meeting in a list view
    public ArrayList<Meeting> getAllMeetings() {
        ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEETINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Meeting meeting = new Meeting();
                meeting.setTopic(cursor.getString(1));
                meeting.setDuration(cursor.getString(2));
                meeting.setDate(cursor.getString(3));
                meeting.setTime(cursor.getString(4));
                // Adding contact to list
                meetingList.add(meeting);
            } while (cursor.moveToNext());
        }

        // return meeting list
        return meetingList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEETINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}