package com.whrsmxmx.eqa.assesment;

import com.whrsmxmx.eqa.data.Drop;

import java.util.ArrayList;

/**
 * Created by Max on 22.12.2016.
 */

public interface AssessmentContract {

    interface View{

        void openDrop(boolean isDegenerate,
                      String blastomeres,
                      int fragmentationPercent,
                      ArrayList<String> anomalies,
                      String note);

        void openPatientsList();

        void setDropsAmount(int dropsNumber);

        void dropSaved();

        void lastDropSaved();
    }

    interface UserActionsListener{

        void getDropsAmount();

        void getDrop(int dropNumber);

        void saveClicked(Drop drop);

    }
}
