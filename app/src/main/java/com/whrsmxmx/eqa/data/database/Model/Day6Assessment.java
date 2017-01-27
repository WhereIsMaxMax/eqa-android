package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Max on 11.01.2017.
 */

@DatabaseTable
public class Day6Assessment {

    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;

    @DatabaseField
    private String decision;

    @DatabaseField
    private String devStage;

    @DatabaseField
    private String ICM;

    @DatabaseField
    private String TE;

    @DatabaseField
    private String note;

    Day6Assessment(){}

    public Day6Assessment(String decision,
                          String devStage,
                          String ICM,
                          String TE,
                          String note){
        this.decision = decision;
        this.devStage = devStage;
        this.ICM = ICM;
        this.TE = TE;
        this.note = note;
    }

    public void setInfo(String decision,
                        String devStage,
                        String ICM,
                        String TE,
                        String note){
        this.decision = decision;
        this.devStage = devStage;
        this.ICM = ICM;
        this.TE = TE;
        this.note = note;
    }

    public String[] toStringArray(){
        return new String[]{devStage, ICM, TE, note, decision};
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

    public String getICM() {
        return ICM;
    }

    public void setICM(String ICM) {
        this.ICM = ICM;
    }

    public String getTE() {
        return TE;
    }

    public void setTE(String TE) {
        this.TE = TE;
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
