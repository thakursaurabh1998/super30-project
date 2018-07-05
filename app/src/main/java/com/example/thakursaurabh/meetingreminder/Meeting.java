package com.example.thakursaurabh.meetingreminder;

public class Meeting {

    String topic;
    String duration;
    String date;
    String time;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Meeting(){}

    public Meeting(String topic, String duration, String date, String time) {
        this.topic = topic;
        this.duration = duration;
        this.date = date;
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}