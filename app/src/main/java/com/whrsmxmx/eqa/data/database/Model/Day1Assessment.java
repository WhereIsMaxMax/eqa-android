package com.whrsmxmx.eqa.data.database.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */

@DatabaseTable
public class Day1Assessment extends Assessment{
    @DatabaseField
    private String maturity;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> zonaPellucida;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> pvs;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> membrane;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> cytoplasm;
    @DatabaseField
    private String dirBody;

    Day1Assessment(){}

    Day1Assessment(String maturity,
                   ArrayList<String> zonaPellucida,
                   ArrayList<String> pvs,
                   ArrayList<String> membrane,
                   ArrayList<String> cytoplasm,
                   String dirBody){
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
