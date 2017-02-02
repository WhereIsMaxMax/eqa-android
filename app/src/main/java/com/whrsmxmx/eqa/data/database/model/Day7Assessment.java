package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Max on 24.01.2017.
 */

@DatabaseTable
public class Day7Assessment {
    @DatabaseField(generatedId = true)
    private int Id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;
    @DatabaseField
    private String decision;
    @DatabaseField
    private String note;

    Day7Assessment(){}

    public Day7Assessment(String decision, String note) {
        this.decision = decision;
        this.note = note;
    }

    public void setInfo(String decision, String note){
        this.decision = decision;
        this.note = note;
    }

    public String[] toStringArray(){
        return new String[]{note, decision};
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
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
