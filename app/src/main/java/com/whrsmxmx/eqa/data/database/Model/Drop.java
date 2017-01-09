package com.whrsmxmx.eqa.data.database.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

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
    private  boolean mIsDegenerate;


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
}
