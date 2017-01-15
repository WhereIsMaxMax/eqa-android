package com.whrsmxmx.eqa.assesment;

import android.util.Log;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.database.model.Day0Assessment;
import com.whrsmxmx.eqa.data.database.model.Day1Assessment;
import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
import com.whrsmxmx.eqa.data.database.model.Day3Assessment;
import com.whrsmxmx.eqa.data.database.model.Day4Assessment;
import com.whrsmxmx.eqa.data.database.model.Day5Assessment;
import com.whrsmxmx.eqa.data.database.model.Day6Assessment;
import com.whrsmxmx.eqa.data.database.model.Drop;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Max on 22.12.2016.
 */

public class AssessmentPresenter implements AssessmentContract.UserActionsListener {

    static final String TAG = AssessmentPresenter.class.getName();

    private RuntimeExceptionDao<Patient, String> mPatientDao;
    private RuntimeExceptionDao<Drop, String> mDropDao;
    private RuntimeExceptionDao<Day0Assessment, String> mDay0AssessmentDao;
    private RuntimeExceptionDao<Day1Assessment, String> mDay1AssessmentDao;
    private RuntimeExceptionDao<Day2Assessment, String> mDay2AssessmentDao;
    private RuntimeExceptionDao<Day3Assessment, String> mDay3AssessmentDao;
    private RuntimeExceptionDao<Day4Assessment, String> mDay4AssessmentDao;
    private RuntimeExceptionDao<Day5Assessment, String> mDay5AssessmentDao;
    private RuntimeExceptionDao<Day6Assessment, String> mDay6AssessmentDao;
    private AssessmentContract.View mView;
    private Patient mPatient;
    private ArrayList<Drop> mDrops;
    private int mDay;

    AssessmentPresenter(RuntimeExceptionDao<Patient, String> patientDao,
                        RuntimeExceptionDao<Drop, String> dropDao,
                        RuntimeExceptionDao<Day0Assessment, String> day0AssessmentDao,
                        RuntimeExceptionDao<Day1Assessment, String> day1AssessmentDao,
                        RuntimeExceptionDao<Day2Assessment, String> day2AssessmentDao,
                        RuntimeExceptionDao<Day3Assessment, String> day3AssessmentDao,
                        RuntimeExceptionDao<Day4Assessment, String> day4AssessmentDao,
                        RuntimeExceptionDao<Day5Assessment, String> day5AssessmentDao,
                        RuntimeExceptionDao<Day6Assessment, String> day6AssessmentDao,
                        AssessmentContract.View view, String patient_id){


        mPatientDao = patientDao;
        mDropDao = dropDao;
        mDay0AssessmentDao = day0AssessmentDao;
        mDay1AssessmentDao = day1AssessmentDao;
        mDay2AssessmentDao = day2AssessmentDao;
        mDay3AssessmentDao = day3AssessmentDao;
        mDay4AssessmentDao = day4AssessmentDao;
        mDay5AssessmentDao = day5AssessmentDao;
        mDay6AssessmentDao = day6AssessmentDao;

        mView = view;

        mPatient = patientDao.queryForId(patient_id);
        mDrops = new ArrayList<>(mPatient.getDrops());
        mDay = (int) TimeUnit.MILLISECONDS.toDays(
                new Date().getTime() - mPatient.getCreationDate().getTime());
        Log.i(TAG, "Day "+mDay);

        view.setDropsAmount(mPatient.getDropsNumber());
    }


    @Override
    public void getDay() {
        mView.setDay(mDay, mPatient.getName());
    }

    @Override
    public void getDrop(int dropNumber) {
        if (dropNumber < mDrops.size()){
            Drop drop = mDrops.get(dropNumber);
            Log.i(TAG, "openDrop " + drop.getNumber());
            switch (mDay){
                case 0:
                    if(drop.getDay0Assessment()!=null){
                        Day0Assessment day0Assessment = drop.getDay0Assessment();
                        mView.openAssessment(day0Assessment.isDegenerate(),
                                day0Assessment.getMaturity(),
                                day0Assessment.getZonaPellucida(),
                                day0Assessment.getPvs(),
                                day0Assessment.getMembrane(),
                                day0Assessment.getCytoplasm(),
                                day0Assessment.getDirBody(),
                                day0Assessment.getNote());
                    }else
                        mView.openAssessment(false,
                                "",
                                new ArrayList<String>(),
                                new ArrayList<String>(),
                                new ArrayList<String>(),
                                new ArrayList<String>(),
                                "",
                                "");
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:

            }

        }else{
            mView.lastDropSaved();
        }
    }

    @Override
    public void saveClicked(int dropNumber, boolean isDegenerate, String maturity,
                            ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                            ArrayList<String> membrane, ArrayList<String> cytoplasm,
                            String dirBody, String note) {
        Drop drop = mDrops.get(dropNumber);
        Day0Assessment assessment = drop.getDay0Assessment();
        if (assessment == null){
            assessment = new Day0Assessment(isDegenerate, maturity, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay0AssessmentDao.create(assessment);
        }else {
            assessment.setInfo(isDegenerate, maturity, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay0AssessmentDao.update(assessment);
        }
        drop.setDay0Assessment(assessment);
        updateOtherData(drop);
    }

    @Override
    public void saveClicked(int dropNumber, Day1Assessment assessment) {

    }

    @Override
    public void saveClicked(int dropNumber, Day2Assessment assessment) {

    }

    @Override
    public void saveClicked(int dropNumber, Day3Assessment assessment) {

    }

    @Override
    public void saveClicked(int dropNumber, Day4Assessment assessment) {

    }

    @Override
    public void saveClicked(int dropNumber, Day5Assessment assessment) {

    }

    @Override
    public void saveClicked(int dropNumber, Day6Assessment assessment) {

    }

    private void updateOtherData(Drop drop) {
        mDropDao.update(drop);
        mDrops.set(drop.getNumber(), drop);
        mPatient.setDrops(mDrops);
        mPatientDao.update(mPatient);

        if(drop.getNumber()<mDrops.size())
            mView.dropSaved();
        else
            mView.lastDropSaved();
    }

}
