package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Max on 11.01.2017.
 */

@DatabaseTable
public class Day5Assessment {

    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;

    Day5Assessment(){}

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }
}
