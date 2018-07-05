package com.example.thakursaurabh.meetingreminder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MeetingAdapter extends ArrayAdapter<Meeting> {

    private Context mContext;
    private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    public MeetingAdapter(Context context,ArrayList<Meeting> list) {
        super(context, 0 , list);
        mContext = context;
        meetings = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.adapter_main,parent,false);

        Meeting meeting = meetings.get(position);

        TextView topic = (TextView) listItem.findViewById(R.id.tv1);
        topic.setText(meeting.getTopic());

        TextView date = (TextView) listItem.findViewById(R.id.tv2);
        date.setText(meeting.getDate());

        TextView time = (TextView) listItem.findViewById(R.id.tv3);
        time.setText(meeting.getTime());

        TextView duration = (TextView) listItem.findViewById(R.id.tv4);
        duration.setText(meeting.getDuration());

        return listItem;
    }
}