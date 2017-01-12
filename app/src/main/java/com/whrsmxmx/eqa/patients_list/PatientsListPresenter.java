package com.whrsmxmx.eqa.patients_list;

import android.support.annotation.NonNull;

import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.List;

/**
 * Created by Max on 16.12.2016.
 */

public class PatientsListPresenter implements PatientsListContract.UserActionsListener {

    private List<Patient> mPatients;
    private PatientsListContract.View mPatientsView;

    PatientsListPresenter(@NonNull List<Patient> patients, @NonNull PatientsListContract.View patientsView){
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
        mPatientsView.openUpdatePatient(patientId);
    }

    @Override
    public void openPatientCard(String patientId) {
        mPatientsView.showPatientCard(patientId);
    }
}
