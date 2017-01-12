package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Max on 11.01.2017.
 */

public abstract class Assessment {

    @DatabaseField(generatedId = true)
    private int Id;

    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }
}
