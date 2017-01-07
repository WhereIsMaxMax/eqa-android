package com.whrsmxmx.eqa.add_patient;

import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.query.In;
import com.whrsmxmx.eqa.data.Drop;
import com.whrsmxmx.eqa.data.Patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

class PatientPresenter implements PatientContract.UserActionsListener{

    private PatientContract.View mView;
    private RuntimeExceptionDao<Patient, String> mPatientDao;
    private RuntimeExceptionDao<Drop, String> mDropDao;
    private Patient mPatient;

    PatientPresenter(RuntimeExceptionDao<Patient, String> pDao,
                     RuntimeExceptionDao<Drop, String> dDao, PatientContract.View view){
        mPatientDao = pDao;
        mDropDao = dDao;
        mView = view;
    }

    @Override
    public void saveUser(String name, String procedure, Date date, int dropsNumber) {
        Collection<Drop> drops = new ArrayList<Drop>(dropsNumber);
        Patient p = new Patient(name, procedure, date, dropsNumber);
        for(int i = 0; i <dropsNumber; i++){
            Drop drop = new Drop(i, new Date(), false, "0", 0, new ArrayList<String>(), "");
            drop.setPatient(p);
            mDropDao.create(drop);
            drops.add(drop);
        }
        mPatientDao.create(p);
        mView.showUserList();
    }

    @Override
    public void updateUser(String name, String procedure, Date date, int dropsNumber) {
        if(mPatient!=null)
            mPatientDao.update(new Patient(name, procedure, date, dropsNumber, mPatient.getDrops()));
        else {
            mPatientDao.update(new Patient(name, procedure, date, dropsNumber, new ArrayList<Drop>(dropsNumber)));
            Log.w("PatientPresenter", "mPatient == null!!!");
        }
        mView.showUserList();
    }

    @Override
    public void openUserInfoForUpdate(String userId) {
        mPatient = mPatientDao.queryForId(userId);
        mView.showUserInfo(mPatient);
    }
}
