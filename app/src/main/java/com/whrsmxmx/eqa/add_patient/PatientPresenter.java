package com.whrsmxmx.eqa.add_patient;

import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.database.model.Day0Assessment;
import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
import com.whrsmxmx.eqa.data.database.model.Drop;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

class PatientPresenter implements PatientContract.UserActionsListener{

    final static String TAG = PatientPresenter.class.getName();

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
        Collection<Drop> drops = new ArrayList<Drop>();
        Patient p = new Patient(name, procedure, date, dropsNumber);
        for(int i = 0; i < dropsNumber; i++){
            Drop drop = new Drop(i, false);
            drop.setPatient(p);
//            way to create Day0Assessment
//            drop.setDay0Assessment(new Day0Assessment(false, "",new ArrayList<String>(),
//                    new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),"", ""));
            mDropDao.create(drop);
            drops.add(drop);
        }
        p.setDrops(drops);
        mPatientDao.create(p);
        mView.showUserList();
    }

    @Override
    public void updateUser(String name, String procedure, Date date, int dropsNumber) {
        if(mPatient!=null)
            mPatientDao.update(new Patient(name, procedure, date, dropsNumber, mPatient.getDrops()));
        else {
            mPatientDao.update(new Patient(name, procedure, date, dropsNumber, new ArrayList<Drop>(dropsNumber)));
            Log.w(TAG, "updatePatient mPatient == null!!!");
        }
        mView.showUserList();
    }

    @Override
    public void openUserInfoForUpdate(String userId) {
        mPatient = mPatientDao.queryForId(userId);
        mView.showUserInfo(mPatient);
    }
}
