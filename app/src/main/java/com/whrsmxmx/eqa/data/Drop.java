package com.whrsmxmx.eqa.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 14.12.2016.
 */

@DatabaseTable(tableName = "drops")
public class Drop implements Serializable{

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Patient mPatient;
    @DatabaseField(generatedId = true)
    private  int Id;
    @DatabaseField
    private  int mNumber;
    @DatabaseField
    private  Date mAssessmentDate;
    @DatabaseField
    private  boolean mIsDegenerate;
    @DatabaseField
    private  String mBlastomeres;
    @DatabaseField
    private  int mPercent;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    private  ArrayList<String> mAnomalies;
    @DatabaseField
    private  String mComment;

    Drop(){}

    public Drop(int number, Date assessmentDate, boolean isDegenerate,
                String blastomeres, int percent,
                ArrayList<String> anomalies, String comment){
        mNumber = number;
        mAssessmentDate = assessmentDate;
        mIsDegenerate = isDegenerate;
        mBlastomeres = blastomeres;
        mPercent = percent;
        mAnomalies = anomalies;
        mComment = comment;
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

    public Date getAssessmentDate() {
        return mAssessmentDate;
    }

    public boolean isDegenerate() {
        return mIsDegenerate;
    }

    public String getBlastomeres() {
        return mBlastomeres;
    }

    public int getPercent() {
        return mPercent;
    }

    public ArrayList<String> getAnomalies() {
        return mAnomalies;
    }

    public String getComment() {
        return mComment;
    }
}
