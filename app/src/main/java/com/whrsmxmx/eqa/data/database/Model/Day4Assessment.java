package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Max on 09.01.2017.
 */
@DatabaseTable
public class Day4Assessment{

    @DatabaseField(generatedId = true)
    private int Id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;
    @DatabaseField
    private String decision;
    @DatabaseField
    private String devStage;
    @DatabaseField
    private String note;

    Day4Assessment(){}

    public Day4Assessment(String decision, String devStage, String note) {
        this.decision = decision;
        this.devStage = devStage;
        this.note = note;
    }

    public void setInfo(String decision, String devStage, String note){
        this.decision = decision;
        this.devStage = devStage;
        this.note = note;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDevStage() {
        return devStage;
    }

    public void setDevStage(String devStage) {
        this.devStage = devStage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }
}
