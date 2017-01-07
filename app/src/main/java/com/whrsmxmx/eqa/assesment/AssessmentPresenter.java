package com.whrsmxmx.eqa.assesment;

import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.Drop;
import com.whrsmxmx.eqa.data.Patient;

import java.util.ArrayList;
import java.util.Date;

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

    AssessmentPresenter(RuntimeExceptionDao<Patient, String> patientDao,
                        RuntimeExceptionDao<Drop, String> dropDao,
                        AssessmentContract.View view, String patient_id){


        mPatientDao = patientDao;
        mDropDao = dropDao;
        mView = view;

        mPatient = patientDao.queryForId(patient_id);
    }

    @Override
    public void dropClicked(int dropNumber) {
        ArrayList<Drop> dropsArray = new ArrayList<Drop>(mPatient.getDrops());
        if (dropsArray.get(dropNumber)!=null)
            mView.openDrop(dropsArray.get(dropNumber));
        else{
            Log.w(TAG, "Drop is null, SOMETHING GOES WRONG!");
        }
    }

    @Override
    public void saveClicked(Drop drop) {
        drop.setPatient(mPatient);
        mPatient.addDrop(drop);
        mPatientDao.update(mPatient);
    }

}
