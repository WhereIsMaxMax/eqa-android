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
    @ForeignCollectionField(eager = true)
    private Collection<Assessment> mAssessments;

    Drop(){}

    public Drop(int number, boolean isDegenerate){
        mNumber = number;

        mIsDegenerate = isDegenerate;

        mAssessments = new ArrayList<>();

        mAssessments.add(new Day0Assessment(false,
                "",
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                "",
                ""));
//        }
        mAssessments.add(new Day1Assessment("",
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                ""));
        mAssessments.add(new Day2Assessment(false,
                "",
                0,
                new ArrayList<String>(),
                ""));
        mAssessments.add(new Day3Assessment("",
                0,
                new ArrayList<String>(),
                ""));
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

    public Collection<Assessment> getAssessments() {
        return mAssessments;
    }

    public void setAssessments(Collection<Assessment> assessments) {
        mAssessments = assessments;
    }
}
