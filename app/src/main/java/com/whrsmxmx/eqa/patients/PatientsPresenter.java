package com.whrsmxmx.eqa.patients;

import android.support.annotation.NonNull;

import com.whrsmxmx.eqa.data.Patient;

import java.util.List;

/**
 * Created by Max on 16.12.2016.
 */

public class PatientsPresenter implements PatientsContract.UserActions {

    private List<Patient> mPatients;
    private PatientsContract.View mPatientsView;

    PatientsPresenter(@NonNull List<Patient> patients, @NonNull PatientsContract.View patientsView){
        mPatients = patients;
        mPatientsView = patientsView;
    }

    @Override
    public void loadPatients() {
        mPatientsView.updatePatientsList();
    }

    @Override
    public void addNewPatient() {
        mPatientsView.showAddPatient();
    }

    @Override
    public void deletePatient(String patientId) {

    }

    @Override
    public void updatePatient(String patientId) {
//        mPatientsView.openUpdatePatient();
    }

    @Override
    public void openPatientCard(String patientId) {

    }
}
