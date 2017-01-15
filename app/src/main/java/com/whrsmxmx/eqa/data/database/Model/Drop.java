package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Max on 14.12.2016.
 */

@DatabaseTable(tableName = "drops")
public class Drop implements Serializable{

    final static int DAYS = 6;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Patient mPatient;
    @DatabaseField(generatedId = true)
    private int Id;
    @DatabaseField
    private int mNumber;
    @DatabaseField
    private boolean mIsDegenerate;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day0Assessment mDay0Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day1Assessment mDay1Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day2Assessment mDay2Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day3Assessment mDay3Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day4Assessment mDay4Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day5Assessment mDay5Assessment;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Day6Assessment mDay6Assessment;

    Drop(){}

    public Drop(int number, boolean isDegenerate){
        mNumber = number;

        mIsDegenerate = isDegenerate;
    }

    public void setPatient(Patient patient) {
        this.mPatient = patient;
    }

    public Patient getPatient() {
        return mPatient;
    }

    public int getNumber() {
        return mNumber;
    }

    public boolean isDegenerate() {
        return mIsDegenerate;
    }

    public Day0Assessment getDay0Assessment() {
        return mDay0Assessment;
    }

    public void setDay0Assessment(Day0Assessment day0Assessment) {
        mDay0Assessment = day0Assessment;
    }

    public Day1Assessment getDay1Assessment() {
        return mDay1Assessment;
    }

    public void setDay1Assessment(Day1Assessment day1Assessment) {
        mDay1Assessment = day1Assessment;
    }

    public Day2Assessment getDay2Assessment() {
        return mDay2Assessment;
    }

    public void setDay2Assessment(Day2Assessment day2Assessment) {
        mDay2Assessment = day2Assessment;
    }

    public Day3Assessment getDay3Assessment() {
        return mDay3Assessment;
    }

    public void setDay3Assessment(Day3Assessment day3Assessment) {
        mDay3Assessment = day3Assessment;
    }

    public Day4Assessment getDay4Assessment() {
        return mDay4Assessment;
    }

    public void setDay4Assessment(Day4Assessment day4Assessment) {
        mDay4Assessment = day4Assessment;
    }

    public Day5Assessment getDay5Assessment() {
        return mDay5Assessment;
    }

    public void setDay5Assessment(Day5Assessment day5Assessment) {
        mDay5Assessment = day5Assessment;
    }

    public Day6Assessment getDay6Assessment() {
        return mDay6Assessment;
    }

    public void setDay6Assessment(Day6Assessment day6Assessment) {
        mDay6Assessment = day6Assessment;
    }
}
