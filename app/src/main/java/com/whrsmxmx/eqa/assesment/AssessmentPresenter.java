package com.whrsmxmx.eqa.assesment;

import android.util.Log;
import android.util.TimeUtils;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.database.model.Assessment;
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
    }

    @Override
    public void getDropsAmount() {
        mView.setDropsAmount(mPatient.getDropsNumber());
    }

    @Override
    public void getDrop(int dropNumber) {
        if (dropNumber < mDrops.size()){
            Drop drop = mDrops.get(dropNumber);
            ArrayList<Assessment> assessments = new ArrayList<>(drop.getAssessments());
            Log.i(TAG, "openDrop " + drop.getNumber());
            switch (mDay){
                case 0:
                    Day0Assessment day0Assessment = (Day0Assessment)assessments.get(mDay);
                    mView.open0Assessment(day0Assessment.isDegenerate(),
                            day0Assessment.getMaturity(),
                            day0Assessment.getZonaPellucida(),
                            day0Assessment.getPvs(),
                            day0Assessment.getMembrane(),
                            day0Assessment.getCytoplasm(),
                            day0Assessment.getDirBody(),
                            day0Assessment.getNote());
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
//            if(drop.getDay2Assessment()!=null){
//                Day2Assessment day2Assessment = drop.getDay2Assessment();
//
//                mView.openDrop(drop.isDegenerate(),
//                        day2Assessment.getBlastomeres(),
//                        day2Assessment.getPercent(),
//                        day2Assessment.getAnomalies(),
//                        day2Assessment.getNote());
//            }else {
//                mView.openDrop(false, "", 0, new ArrayList<String>(), "");
//            }
        }else{
            mView.lastDropSaved();
        }
    }

    @Override
    public void saveClicked(int dropNumber, Assessment assessment) {
        Drop drop = mDrops.get(dropNumber);
        assessment.setDrop(drop);
        switch (mDay){
            case 0:
                Day0Assessment day0Assessment = (Day0Assessment) assessment;
                mDay0AssessmentDao.update(day0Assessment);
                ArrayList<Assessment> assessments = new ArrayList<>(drop.getAssessments());
                assessments.set(mDay, day0Assessment);
                drop.setAssessments(assessments);
                mDropDao.update(drop);
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
