package com.whrsmxmx.eqa.add_patient;

import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.Date;

interface PatientContract {

    interface View{

        void showUserInfo(Patient patient);

        void showUserList();

        void showNameExistsDialog();
    }

    interface UserActionsListener{

        void saveUser(String name, String procedure, Date date, int drops);

        void updateUser(String name, String procedure, Date date, int drops);

        void openUserInfoForUpdate(String userId);
    }
}
