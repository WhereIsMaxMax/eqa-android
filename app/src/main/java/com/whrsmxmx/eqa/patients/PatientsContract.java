package com.whrsmxmx.eqa.patients;

import com.whrsmxmx.eqa.data.Patient;

import java.util.ArrayList;

/**
 * Created by Max on 16.12.2016.
 */

public interface PatientsContract {

    interface View{

        void showAddPatient();

        void showPatientsList(ArrayList<Patient> patients);

        void showPatientCard(String patientId);
    }

    interface UserActions{

        void addNewPatient();

        void showChoosePatientActionDialog();

        void deletePatient(String patientId);

        void updatePatient(String patientId);

        void openPatientCard(String patientId);
    }
}
