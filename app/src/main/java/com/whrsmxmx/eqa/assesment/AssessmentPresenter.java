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
    public void getDrop(int dropNumber, boolean isDirectClick) {
        if (dropNumber < mDrops.size()){
            Drop drop = mDrops.get(dropNumber);
            if ((drop.getDecision()!=null&&!drop.getDecision().isEmpty())&&!isDirectClick){
                mView.showNextDrop();
            } else {
                Log.i(TAG, "openDrop " + drop.getNumber());
                switch (mDay){
                    case 0:
                        if(drop.getDay0Assessment()!=null){
                            Day0Assessment day0Assessment = drop.getDay0Assessment();
                            mView.openAssessment(day0Assessment.getDecision(),
                                    day0Assessment.getMaturity(),
                                    day0Assessment.getZonaPellucida(),
                                    day0Assessment.getPvs(),
                                    day0Assessment.getMembrane(),
                                    day0Assessment.getCytoplasm(),
                                    day0Assessment.getDirBody(),
                                    day0Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    "",
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    "",
                                    "");
                        break;
                    case 1:
                        if(drop.getDay1Assessment()!=null){
                            Day1Assessment day1Assessment = drop.getDay1Assessment();
                            mView.openAssessment(day1Assessment.getDecision(),
                                    day1Assessment.getMaturity(),
                                    day1Assessment.getNpbs(),
                                    day1Assessment.getZonaPellucida(),
                                    day1Assessment.getPvs(),
                                    day1Assessment.getMembrane(),
                                    day1Assessment.getCytoplasm(),
                                    day1Assessment.getDirBody(),
                                    day1Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    "",
                                    "",
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    new ArrayList<String>(),
                                    "",
                                    "");
                        break;
                    case 2:
                        if(drop.getDay2Assessment()!=null){
                            Day2Assessment day2Assessment = drop.getDay2Assessment();
                            mView.openAssessment(day2Assessment.getDecision(),
                                    false,
                                    day2Assessment.getBlastomeres(),
                                    day2Assessment.getFragmentation(),
                                    day2Assessment.getAnomalies(),
                                    day2Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    false,
                                    "",
                                    0,
                                    new ArrayList<String>(),
                                    "");
                        break;
                    case 3:
                        if(drop.getDay3Assessment()!=null){
                            Day3Assessment day3Assessment = drop.getDay3Assessment();
                            mView.openAssessment(day3Assessment.getDecision(),
                                    true,
                                    day3Assessment.getBlastomeres(),
                                    day3Assessment.getFragmentation(),
                                    day3Assessment.getAnomalies(),
                                    day3Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    true,
                                    "",
                                    0,
                                    new ArrayList<String>(),
                                    "");
                        break;
                    case 4:
                        if(drop.getDay4Assessment()!=null){
                            Day4Assessment day4Assessment = drop.getDay4Assessment();
                            mView.openAssessment(day4Assessment.getDecision(),
                                    day4Assessment.getDevStage(),
                                    day4Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    "",
                                    "");

                        break;
                    case 5:
                        if(drop.getDay5Assessment()!=null){
                            Day5Assessment day5Assessment = drop.getDay5Assessment();
                            mView.openAssessment(day5Assessment.getDecision(),
                                    true,
                                    day5Assessment.getDevStage(),
                                    day5Assessment.getICM(),
                                    day5Assessment.getTE(),
                                    day5Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    true,
                                    "",
                                    "",
                                    "",
                                    "");
                        break;
                    case 6:
                        if(drop.getDay6Assessment()!=null){
                            Day6Assessment day6Assessment = drop.getDay6Assessment();
                            mView.openAssessment(day6Assessment.getDecision(),
                                    false,
                                    day6Assessment.getDevStage(),
                                    day6Assessment.getICM(),
                                    day6Assessment.getTE(),
                                    day6Assessment.getNote());
                        }else
                            mView.openAssessment(drop.getDecision(),
                                    false,
                                    "",
                                    "",
                                    "",
                                    "");
                        break;
                    default:
//                    todo: show decision
                }
            }
        }else{
            mView.lastDropSaved();
        }
    }

    @Override
    public void saveClicked(int dropNumber, String decision, String maturity,
                            ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                            ArrayList<String> membrane, ArrayList<String> cytoplasm,
                            String dirBody, String note) {
        Drop drop = mDrops.get(dropNumber);
        drop.setDecision(decision);
        Day0Assessment assessment = drop.getDay0Assessment();
        if (assessment == null){
            assessment = new Day0Assessment(decision, maturity, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay0AssessmentDao.create(assessment);
        }else {
            assessment.setInfo(decision, maturity, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay0AssessmentDao.update(assessment);
        }
        drop.setDay0Assessment(assessment);
        updateOtherData(drop);
    }

    @Override
    public void saveClicked(int dropNumber, String decision, String maturity, String npbs,
                            ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                            ArrayList<String> membrane, ArrayList<String> cytoplasm, String dirBody,
                            String note) {

        Drop drop = mDrops.get(dropNumber);
        drop.setDecision(decision);
        Day1Assessment assessment = drop.getDay1Assessment();
        if (assessment == null){
            assessment = new Day1Assessment(decision, maturity, npbs, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay1AssessmentDao.create(assessment);
        }else {
            assessment.setInfo(decision, maturity, npbs, zonaPellucida, pvs,
                    membrane, cytoplasm, dirBody, note);
            assessment.setDrop(drop);
            mDay1AssessmentDao.update(assessment);
        }
        drop.setDay1Assessment(assessment);
        updateOtherData(drop);
    }

    @Override
    public void saveClicked(int dropNumber, boolean is3Day, String decision, String blastomeres, int percent,
                            ArrayList<String> anomalies, String note) {
        Drop drop = mDrops.get(dropNumber);
        drop.setDecision(decision);
        if (!is3Day){
            Day2Assessment assessment = drop.getDay2Assessment();
            if(assessment == null){
                assessment = new Day2Assessment(decision, blastomeres, percent, anomalies, note);
                assessment.setDrop(drop);
                mDay2AssessmentDao.create(assessment);
            }else {
                assessment.setInfo(decision, blastomeres, percent, anomalies, note);
                assessment.setDrop(drop);
                mDay2AssessmentDao.update(assessment);
            }
            drop.setDay2Assessment(assessment);
        }else {
            Day3Assessment assessment = drop.getDay3Assessment();
            if(assessment == null){
                assessment = new Day3Assessment(decision, blastomeres, percent, anomalies, note);
                assessment.setDrop(drop);
                mDay3AssessmentDao.create(assessment);
            }else {
                assessment.setInfo(decision, blastomeres, percent, anomalies, note);
                assessment.setDrop(drop);
                mDay3AssessmentDao.update(assessment);
            }
            drop.setDay3Assessment(assessment);
        }
        updateOtherData(drop);
    }

    @Override
    public void saveClicked(int dropNumber, String decision, String devStage, String note) {
        Drop drop = mDrops.get(dropNumber);
        drop.setDecision(decision);
        Day4Assessment assessment = drop.getDay4Assessment();
        if (assessment == null){
            assessment = new Day4Assessment(decision, devStage, note);
            assessment.setDrop(drop);
            mDay4AssessmentDao.create(assessment);
        }else {
            assessment.setInfo(decision, devStage, note);
            assessment.setDrop(drop);
            mDay4AssessmentDao.update(assessment);
        }
        drop.setDay4Assessment(assessment);
        updateOtherData(drop);
    }

    @Override
    public void saveClicked(int dropNumber, String decision, boolean is5Day, String devStage, String ICM, String TE, String note) {
        Drop drop = mDrops.get(dropNumber);
        drop.setDecision(decision);
        if (is5Day){
            Day5Assessment assessment = drop.getDay5Assessment();
            if(assessment == null){
                assessment = new Day5Assessment(decision, devStage, ICM, TE, note);
                assessment.setDrop(drop);
                mDay5AssessmentDao.create(assessment);
            }else {
                assessment.setInfo(decision, devStage, ICM, TE, note);
                assessment.setDrop(drop);
                mDay5AssessmentDao.update(assessment);
            }
            drop.setDay5Assessment(assessment);
        }else {
            Day6Assessment assessment = drop.getDay6Assessment();
            if(assessment == null){
                assessment = new Day6Assessment(decision, devStage, ICM, TE, note);
                assessment.setDrop(drop);
                mDay6AssessmentDao.create(assessment);
            }else {
                assessment.setInfo(decision, devStage, ICM, TE, note);
                assessment.setDrop(drop);
                mDay6AssessmentDao.update(assessment);
            }
            drop.setDay6Assessment(assessment);
        }
        updateOtherData(drop);
    }

    private void updateOtherData(Drop drop) {
        mDropDao.update(drop);
        mDrops.set(drop.getNumber(), drop);
        mPatient.setDrops(mDrops);
        mPatientDao.update(mPatient);

        if(drop.getNumber()<mDrops.size())
            mView.showNextDrop();
        else
            mView.lastDropSaved();
    }

}
