package com.whrsmxmx.eqa.data.database.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */

@DatabaseTable
public class Day0Assessment {
    @DatabaseField (foreign = true, foreignAutoRefresh = true)
    private Drop drop;
    @DatabaseField
    private String maturity;
    @DatabaseField
    private ArrayList<String> zonaPellucida;
    @DatabaseField
    private ArrayList<String> pvs;
    @DatabaseField
    private ArrayList<String> membrane;
    @DatabaseField
    private ArrayList<String> cytoplasm;
    @DatabaseField
    private String dirBody;

    Day0Assessment(){}

    Day0Assessment(Drop drop,
                   String maturity,
                   ArrayList<String> zonaPellucida,
                   ArrayList<String> pvs,
                   ArrayList<String> membrane,
                   ArrayList<String> cytoplasm,
                   String dirBody){
        this.drop = drop;
        this.maturity = maturity;
        this.zonaPellucida = zonaPellucida;
        this.pvs = pvs;
        this.membrane = membrane;
        this.cytoplasm = cytoplasm;
        this.dirBody = dirBody;
    }

    public String getMaturity() {
        return maturity;
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
}
