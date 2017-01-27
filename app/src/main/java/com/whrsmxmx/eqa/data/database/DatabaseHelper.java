package com.whrsmxmx.eqa.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.model.Day0Assessment;
import com.whrsmxmx.eqa.data.database.model.Day1Assessment;
import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
import com.whrsmxmx.eqa.data.database.model.Day3Assessment;
import com.whrsmxmx.eqa.data.database.model.Day4Assessment;
import com.whrsmxmx.eqa.data.database.model.Day5Assessment;
import com.whrsmxmx.eqa.data.database.model.Day6Assessment;
import com.whrsmxmx.eqa.data.database.model.Day7Assessment;
import com.whrsmxmx.eqa.data.database.model.Drop;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.sql.SQLException;

/**
 * Created by Max on 14.12.2016.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "Patients.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<Patient, String> patentsDao = null;
    private Dao<Drop, String> dropDao = null;
    private Dao<Day0Assessment, String> day0AssessmentDao = null;
    private Dao<Day1Assessment, String> day1AssessmentDao = null;
    private Dao<Day2Assessment, String> day2AssessmentDao = null;
    private Dao<Day3Assessment, String> day3AssessmentDao = null;
    private Dao<Day4Assessment, String> day4AssessmentDao = null;
    private Dao<Day5Assessment, String> day5AssessmentDao = null;
    private Dao<Day6Assessment, String> day6AssessmentDao = null;
    private Dao<Day7Assessment, String> day7AssessmentDao = null;

    private RuntimeExceptionDao<Patient, String> patientsRuntimeDao = null;
    private RuntimeExceptionDao<Drop, String> dropRuntimeDao = null;
    private RuntimeExceptionDao<Day0Assessment, String> day0AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day1Assessment, String> day1AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day2Assessment, String> day2AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day3Assessment, String> day3AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day4Assessment, String> day4AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day5Assessment, String> day5AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day6Assessment, String> day6AssessmentRuntimeDao = null;
    private RuntimeExceptionDao<Day7Assessment, String> day7AssessmentRuntimeDao = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
//        super call for generate ormlite config run
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "OnCreate");
            TableUtils.createTable(connectionSource, Patient.class);
            TableUtils.createTable(connectionSource, Drop.class);
            TableUtils.createTable(connectionSource, Day0Assessment.class);
            TableUtils.createTable(connectionSource, Day1Assessment.class);
            TableUtils.createTable(connectionSource, Day2Assessment.class);
            TableUtils.createTable(connectionSource, Day3Assessment.class);
            TableUtils.createTable(connectionSource, Day4Assessment.class);
            TableUtils.createTable(connectionSource, Day5Assessment.class);
            TableUtils.createTable(connectionSource, Day6Assessment.class);
            TableUtils.createTable(connectionSource, Day7Assessment.class);
        } catch (SQLException e){
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Patient.class, true);
            TableUtils.dropTable(connectionSource, Drop.class, true);
            TableUtils.dropTable(connectionSource, Day0Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day1Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day2Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day3Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day4Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day5Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day6Assessment.class, true);
            TableUtils.dropTable(connectionSource, Day7Assessment.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Patient, String> getDao() throws SQLException {
        if (patentsDao == null) {
            patentsDao = getDao(Patient.class);
        }
        return patentsDao;
    }
    public Dao<Drop, String> getDropDao() throws SQLException {
        if (dropDao == null) {
            dropDao = getDao(Drop.class);
        }
        return dropDao;
    }

    public Dao<Day0Assessment, String> getDay0AssessmentDao() throws SQLException {
        if (day0AssessmentDao == null) {
            day0AssessmentDao = getDao(Day0Assessment.class);
        }
        return day0AssessmentDao;
    }

    public Dao<Day1Assessment, String> getDay1AssessmentDao() throws SQLException {
        if (day1AssessmentDao == null) {
            day1AssessmentDao = getDao(Day1Assessment.class);
        }
        return day1AssessmentDao;
    }

    public Dao<Day2Assessment, String> getDay2AssessmentDao() throws SQLException {
        if (day2AssessmentDao == null) {
            day2AssessmentDao = getDao(Day2Assessment.class);
        }
        return day2AssessmentDao;
    }

    public Dao<Day3Assessment, String> getDay3AssessmentDao() throws SQLException {
        if (day3AssessmentDao == null) {
            day3AssessmentDao = getDao(Day3Assessment.class);
        }
        return day3AssessmentDao;
    }

    public Dao<Day4Assessment, String> getDay4AssessmentDao() throws SQLException {
        if (day4AssessmentDao == null) {
            day4AssessmentDao = getDao(Day4Assessment.class);
        }
        return day4AssessmentDao;
    }

    public Dao<Day5Assessment, String> getDay5AssessmentDao() throws SQLException {
        if (day5AssessmentDao == null) {
            day5AssessmentDao = getDao(Day5Assessment.class);
        }
        return day5AssessmentDao;
    }

    public Dao<Day6Assessment, String> getDay6AssessmentDao() throws SQLException {
        if (day6AssessmentDao == null) {
            day6AssessmentDao = getDao(Day6Assessment.class);
        }
        return day6AssessmentDao;
    }

    public Dao<Day7Assessment, String> getDay7AssessmentDao() throws SQLException {
        if (day7AssessmentDao == null) {
            day7AssessmentDao = getDao(Day7Assessment.class);
        }
        return day7AssessmentDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Patient, String> getSimpleDataDao() {
        if (patientsRuntimeDao == null) {
            patientsRuntimeDao = getRuntimeExceptionDao(Patient.class);
        }
        return patientsRuntimeDao;
    }

    public RuntimeExceptionDao<Drop, String> getDropDataDao() {
        if (dropRuntimeDao == null) {
            dropRuntimeDao = getRuntimeExceptionDao(Drop.class);
        }
        return dropRuntimeDao;
    }

    public RuntimeExceptionDao<Day0Assessment, String> getDay0AssessmentDataDao() {
        if (day0AssessmentRuntimeDao == null) {
            day0AssessmentRuntimeDao = getRuntimeExceptionDao(Day0Assessment.class);
        }
        return day0AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day1Assessment, String> getDay1AssessmentDataDao() {
        if (day1AssessmentRuntimeDao == null) {
            day1AssessmentRuntimeDao = getRuntimeExceptionDao(Day1Assessment.class);
        }
        return day1AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day2Assessment, String> getDay2AssessmentDataDao() {
        if (day2AssessmentRuntimeDao == null) {
            day2AssessmentRuntimeDao = getRuntimeExceptionDao(Day2Assessment.class);
        }
        return day2AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day3Assessment, String> getDay3AssessmentDataDao() {
        if (day3AssessmentRuntimeDao == null) {
            day3AssessmentRuntimeDao = getRuntimeExceptionDao(Day3Assessment.class);
        }
        return day3AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day4Assessment, String> getDay4AssessmentDataDao() {
        if (day4AssessmentRuntimeDao == null) {
            day4AssessmentRuntimeDao = getRuntimeExceptionDao(Day4Assessment.class);
        }
        return day4AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day5Assessment, String> getDay5AssessmentDataDao() {
        if (day5AssessmentRuntimeDao == null) {
            day5AssessmentRuntimeDao = getRuntimeExceptionDao(Day5Assessment.class);
        }
        return day5AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day6Assessment, String> getDay6AssessmentDataDao() {
        if (day6AssessmentRuntimeDao == null) {
            day6AssessmentRuntimeDao = getRuntimeExceptionDao(Day6Assessment.class);
        }
        return day6AssessmentRuntimeDao;
    }


    public RuntimeExceptionDao<Day7Assessment, String> getDay7AssessmentDataDao() {
        if (day7AssessmentRuntimeDao == null) {
            day7AssessmentRuntimeDao = getRuntimeExceptionDao(Day7Assessment.class);
        }
        return day7AssessmentRuntimeDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        patientsRuntimeDao = null;
        dropRuntimeDao = null;
        day0AssessmentRuntimeDao = null;
        day1AssessmentRuntimeDao = null;
        day2AssessmentRuntimeDao = null;
        day3AssessmentRuntimeDao = null;
        day4AssessmentRuntimeDao = null;
        day5AssessmentRuntimeDao = null;
        day6AssessmentRuntimeDao = null;
        day7AssessmentRuntimeDao = null;

        patentsDao = null;
        dropDao = null;
        day0AssessmentDao = null;
        day1AssessmentDao = null;
        day2AssessmentDao = null;
        day3AssessmentDao = null;
        day4AssessmentDao = null;
        day5AssessmentDao = null;
        day6AssessmentDao = null;
        day7AssessmentDao = null;
    }
}
