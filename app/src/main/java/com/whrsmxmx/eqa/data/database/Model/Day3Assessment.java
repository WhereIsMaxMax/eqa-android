package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */

@DatabaseTable
public class Day3Assessment{

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

    Day3Assessment(){}

    public Day3Assessment(String decision, String blastomeres, int percent, ArrayList<String> anomalies, String note){
        this.decision = decision;
        this.blastomeres = blastomeres;
        this.percent = percent;
        this.anomalies = anomalies;
        this.note = note;
    }

    public void setInfo(String decision, String blastomeres, int percent, ArrayList<String> anomalies, String note){
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

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setBlastomeres(String blastomeres) {
        this.blastomeres = blastomeres;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setAnomalies(ArrayList<String> anomalies) {
        this.anomalies = anomalies;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
