package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */
@DatabaseTable
public class Day2Assessment{

    @DatabaseField(generatedId = true)
    private int Id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;
    @DatabaseField
    private String decision;
    @DatabaseField
    private String blastomeres;
    @DatabaseField
    private int percent;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    private ArrayList<String> anomalies;
    @DatabaseField
    private String note;

    Day2Assessment(){}

    public Day2Assessment(String decision,
                          String blastomeres,
                          int percent,
                          ArrayList<String> anomalies,
                          String note){
        this.decision = decision;
        this.blastomeres = blastomeres;
        this.percent = percent;
        this.anomalies = anomalies;
        this.note = note;
    }

    public String getBlastomeres() {
        return blastomeres;
    }

    public int getFragmentation() {
        return percent;
    }

    public ArrayList<String> getAnomalies() {
        return anomalies;
    }

    public String getNote() {
        return note;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }

    public void setInfo(String decision,
                        String blastomeres,
                        int percent,
                        ArrayList<String> anomalies,
                        String note){
        this.decision = decision;
        this.blastomeres = blastomeres;
        this.percent = percent;
        this.anomalies = anomalies;
        this.note = note;
    }
}
