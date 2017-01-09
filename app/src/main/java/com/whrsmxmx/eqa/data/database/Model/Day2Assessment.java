package com.whrsmxmx.eqa.data.database.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */
@DatabaseTable
public class Day2Assessment {
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    Drop drop;
    @DatabaseField
    private String blastomeres;
    @DatabaseField
    private int percent;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    private ArrayList<String> anomalies;
    @DatabaseField
    private String note;

    Day2Assessment(){}

    Day2Assessment(String blastomeres, int percent, ArrayList<String> anomalies, String note){
        this.blastomeres = blastomeres;
        this.percent = percent;
        this.anomalies = anomalies;
        this.note = note;
    }

}
