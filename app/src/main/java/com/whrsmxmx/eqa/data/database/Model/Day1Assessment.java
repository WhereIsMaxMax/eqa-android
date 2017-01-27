package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import static com.whrsmxmx.eqa.utils.StringsTricks.stringCSVFromArrayList;

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
    private String decision;
    @DatabaseField
    private String note;
    @DatabaseField
    private String dirBody;

    Day1Assessment(){}

    public Day1Assessment(String decision,
                   String maturity,
                   String npbs,
                   ArrayList<String> zonaPellucida,
                   ArrayList<String> pvs,
                   ArrayList<String> membrane,
                   ArrayList<String> cytoplasm,
                   String dirBody,
                   String note){
        this.decision = decision;
        this.maturity = maturity;
        this.npbs = npbs;
        this.zonaPellucida = zonaPellucida;
        this.pvs = pvs;
        this.membrane = membrane;
        this.cytoplasm = cytoplasm;
        this.dirBody = dirBody;
        this.note = note;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public void setNpbs(String npbs) {
        this.npbs = npbs;
    }

    public void setZonaPellucida(ArrayList<String> zonaPellucida) {
        this.zonaPellucida = zonaPellucida;
    }

    public void setPvs(ArrayList<String> pvs) {
        this.pvs = pvs;
    }

    public void setMembrane(ArrayList<String> membrane) {
        this.membrane = membrane;
    }

    public void setCytoplasm(ArrayList<String> cytoplasm) {
        this.cytoplasm = cytoplasm;
    }

    public void setDirBody(String dirBody) {
        this.dirBody = dirBody;
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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setInfo(String decision, String maturity, String npbs,
                        ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                        ArrayList<String> membrane, ArrayList<String> cytoplasm, String dirBody,
                        String note) {

        this.decision = decision;
        this.maturity = maturity;
        this.npbs = npbs;
        this.zonaPellucida = zonaPellucida;
        this.pvs = pvs;
        this.membrane = membrane;
        this.cytoplasm = cytoplasm;
        this.dirBody = dirBody;
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public String[] toStringArray(){
        return new String[]{decision, maturity, npbs, stringCSVFromArrayList(zonaPellucida), stringCSVFromArrayList(pvs),
                stringCSVFromArrayList(membrane), stringCSVFromArrayList(cytoplasm), dirBody, note};
    }
}
