package com.whrsmxmx.eqa.assesment;

import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.Drop;
import com.whrsmxmx.eqa.data.Patient;

import java.util.ArrayList;

/**
 * Created by Max on 22.12.2016.
 */

public class AssessmentPresenter implements AssessmentContract.UserActionsListener {

    static final String TAG = AssessmentPresenter.class.getName();

    private RuntimeExceptionDao<Patient, String> mPatientDao;
    private RuntimeExceptionDao<Drop, String> mDropDao;
    private AssessmentContract.View mView;
    private Patient mPatient;
    private Drop mDrop;
    private ArrayList<Drop> mDrops;

    AssessmentPresenter(RuntimeExceptionDao<Patient, String> patientDao,
                        RuntimeExceptionDao<Drop, String> dropDao,
                        AssessmentContract.View view, String patient_id){


        mPatientDao = patientDao;
        mDropDao = dropDao;

        mView = view;

        mPatient = patientDao.queryForId(patient_id);
        mDrops = new ArrayList<>(mPatient.getDrops());
    }

    @Override
    public void getDropsAmount() {
        mView.setDropsAmount(mPatient.getDropsNumber());
    }

    @Override
    public void getDrop(int dropNumber) {
        if (mDrops.size() <= dropNumber){
            mDrop = mDrops.get(dropNumber);
            Log.i(TAG, "openDrop "+mDrop.getNumber());

            mView.openDrop(mDrop.isDegenerate(),
                    mDrop.getBlastomeres(),
                    mDrop.getFragmentationPercent(),
                    mDrop.getAnomalies(),
                    mDrop.getNote());
        }
        else{
            Log.w(TAG, "Drop is null, SOMETHING GOES WRONG!");
        }
    }

    @Override
    public void saveClicked(Drop drop) {
        drop.setPatient(mPatient);
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
