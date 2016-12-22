package com.whrsmxmx.eqa.patients_list;

interface PatientsListContract {

    interface View{

        void showAddPatient();

        void updatePatientsList();

        void showPatientCard(String patientId);

        void openUpdatePatient(String patientId);
    }

    interface UserActionsListener {

        void loadPatients();

        void addNewPatient();

        void deletePatient(String patientId);

        void updatePatient(String patientId);

        void openPatientCard(String patientId);
    }
}
