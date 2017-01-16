package com.whrsmxmx.eqa.data.database.model;

import android.support.annotation.ArrayRes;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */

@DatabaseTable
public class Day1Assessment{

    @DatabaseField(generatedId = true)
    private int Id;
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;
    @DatabaseField
    private String maturity;
    @DatabaseField
    private String npbs;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> zonaPellucida;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> pvs;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> membrane;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> cytoplasm;
    @DatabaseField
    private boolean isDegenerate;
    @DatabaseField
    private String notes;
    @DatabaseField
    private String dirBody;

    Day1Assessment(){}

    public Day1Assessment(boolean isDegenerate,
                   String maturity,
                   String npbs,
                   ArrayList<String> zonaPellucida,
                   ArrayList<String> pvs,
                   ArrayList<String> membrane,
                   ArrayList<String> cytoplasm,
                   String dirBody,
                   String notes){
        this.isDegenerate = isDegenerate;
        this.maturity = maturity;
        this.npbs = npbs;
        this.zonaPellucida = zonaPellucida;
        this.pvs = pvs;
        this.membrane = membrane;
        this.cytoplasm = cytoplasm;
        this.dirBody = dirBody;
        this.notes = notes;
    }

    public String getMaturity() {
        return maturity;
    }

    public String getNpbs() {
        return npbs;
    }

    public ArrayList<String> getZonaPellucida() {
        return zonaPellucida;
    }

    public ArrayList<String> getPvs() {
        return pvs;
    }

    public ArrayList<String> getMembrane() {
        return membrane;
    }

    public ArrayList<String> getCytoplasm() {
        return cytoplasm;
    }

    public String getDirBody() {
        return dirBody;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public Drop getDrop() {
        return drop;
    }

    public void setInfo(boolean isDegenerate, String maturity, String npbs,
                        ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                        ArrayList<String> membrane, ArrayList<String> cytoplasm, String dirBody,
                        String note) {

        this.isDegenerate = isDegenerate;
        this.maturity = maturity;
        this.npbs = npbs;
        this.zonaPellucida = zonaPellucida;
        this.pvs = pvs;
        this.membrane = membrane;
        this.cytoplasm = cytoplasm;
        this.dirBody = dirBody;
        this.notes = note;
    }
}
