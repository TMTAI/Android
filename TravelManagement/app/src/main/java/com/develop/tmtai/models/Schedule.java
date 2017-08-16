package com.develop.tmtai.models;

import org.json.JSONException;
import org.json.JSONObject;

import static com.develop.tmtai.util.Constant.ACTION;
import static com.develop.tmtai.util.Constant.DATE;
import static com.develop.tmtai.util.Constant.DESTINATION;
import static com.develop.tmtai.util.Constant.INSTRUCTOR;
import static com.develop.tmtai.util.Constant.LOCATION;
import static com.develop.tmtai.util.Constant.TIME_END;
import static com.develop.tmtai.util.Constant.TIME_START;

/**
 * Created by tmtai on 8/10/2017.
 */

public class Schedule {
    private String timeStart;
    private String date;
    private String action;
    private String location;
    private String destination;
    private String instructor;
    private String timeEnd;

    public Schedule() {
    }

    public Schedule(String timeStart, String date, String action, String location, String destination, String instructor, String timeEnd) {
        this.timeStart = timeStart;
        this.date = date;
        this.action = action;
        this.location = location;
        this.destination = destination;
        this.instructor = instructor;
        this.timeEnd = timeEnd;
    }

    public Schedule(JSONObject json) {
        try {
            this.timeStart = json.getString(TIME_START);
            this.date = json.getString(DATE);
            this.action = json.getString(ACTION);
            this.location = json.getString(LOCATION);
            this.destination = json.getString(DESTINATION);
            this.instructor = json.getString(INSTRUCTOR);
            this.timeEnd = json.getString(TIME_END);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }


}
