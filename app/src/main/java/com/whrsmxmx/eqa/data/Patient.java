package com.whrsmxmx.eqa.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

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
    private int procedure;
    @DatabaseField
    private Date creationDate;
    @DatabaseField
    private int dropsNumber;

    public final static String NAME = "NAME";

    Patient(){}

    public Patient(String surname, int procedure, Date creationDate, int dropsNumber){
        this.mSurname = surname;
        this.procedure = procedure;
        this.creationDate = creationDate;
        this.dropsNumber = dropsNumber;
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

    public int getProcedure() {
        return procedure;
    }
}
