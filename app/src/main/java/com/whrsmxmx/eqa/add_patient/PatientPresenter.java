package com.whrsmxmx.eqa.add_patient;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.data.Patient;

import java.util.Date;

/**
 * Created by Max on 22.12.2016.
 */

class PatientPresenter implements PatientContract.UserActionsListener{

    private PatientContract.View mView;
    private RuntimeExceptionDao<Patient, String> mPatientDao;

    PatientPresenter(RuntimeExceptionDao<Patient, String> dao, PatientContract.View view){
        mPatientDao = dao;
        mView = view;
    }

    @Override
    public void saveUser(String name, int procedure, Date date, int drops) {
        Patient p = new Patient(name, procedure, date, drops);
        mPatientDao.create(p);
        mView.showUserList();
    }

    @Override
    public void openUserInfoForUpdate(String userId) {
        mView.showUserInfo(mPatientDao.queryForId(userId));
    }
}
