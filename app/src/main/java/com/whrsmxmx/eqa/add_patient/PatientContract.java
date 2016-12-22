package com.whrsmxmx.eqa.add_patient;

import com.whrsmxmx.eqa.data.Patient;

import java.util.Date;

interface PatientContract {

    interface View{

        void showUserInfo(Patient patient);

        void showUserList();

    }

    interface UserActionsListener{

        void saveUser(String name, int procedure, Date date, int drops);

        void openUserInfoForUpdate(String userId);
    }
}
