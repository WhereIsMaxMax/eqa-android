package com.whrsmxmx.eqa.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Max on 13.12.2016.
 */

@DatabaseTable
public class Patient {
//    tag for passing id's between activities
    final public static String PERSON_ID = "PERSON_ID";

    @DatabaseField(id = true, columnName = "surname")
    private String mSurname = "";
    @DatabaseField
    private String procedure;
    @DatabaseField
    private Date creationDate;
    @DatabaseField
    private int dropsNumber;
    @ForeignCollectionField(eager = true, columnName = "drops")
    private Collection<Drop> drops;

    public final static String NAME = "NAME";

    Patient(){}

    public Patient(String surname, String procedure, Date creationDate, int dropsNumber){
        this.mSurname = surname;
        this.procedure = procedure;
        this.creationDate = creationDate;
        this.dropsNumber = dropsNumber;
    }

    public Patient(String surname, String procedure, Date creationDate, int dropsNumber, Collection<Drop> drops){
        this.mSurname = surname;
        this.procedure = procedure;
        this.creationDate = creationDate;
        this.dropsNumber = dropsNumber;
        this.drops = drops;
    }

    public Collection<Drop> getDrops() {
        return drops;
    }

    public void setDrops(Collection<Drop>drops){
        this.drops = drops;
    }

    public String getName(){
        return mSurname;
    }

    public int getDropsNumber() {
        return dropsNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getProcedure() {
        return procedure;
    }
}
