package com.whrsmxmx.eqa.data.database.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Max on 09.01.2017.
 */

@DatabaseTable
public class Day3Assessment {
    @DatabaseField
    private  String mBlastomeres;
    @DatabaseField
    private  int mPercent;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    private ArrayList<String> mAnomalies;
    @DatabaseField
    private  String mComment;
}
