package com.whrsmxmx.eqa.assesment;

import com.whrsmxmx.eqa.data.database.model.Assessment;
import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
import com.whrsmxmx.eqa.data.database.model.Drop;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 22.12.2016.
 */

public interface AssessmentContract {

    interface View{

        void open0Assessment(boolean isDegenerate,
                             String maturity,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

        void open1Assessment(boolean isDegenerate,
                             String maturity,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

        void open2Assessment(boolean isDegenerate,
                             String blastomeres,
                             int fragmentationPercent,
                             ArrayList<String> anomalies,
                             String note);

        void open3Assessment(boolean isDegenerate,
                             String blastomeres,
                             int fragmentationPercent,
                             ArrayList<String> anomalies,
                             String note);

        void open4Assessment(String developmentStage);

        void open5Assessment(String developmentStage,
                             String bkm,
                             String te);

        void open6Assessment(String developmentStage,
                             String bkm,
                             String te);

        void openDecision(String decision);

        void openPatientsList();

        void setDropsAmount(int dropsNumber);

        void dropSaved();

        void lastDropSaved();
    }

    interface UserActionsListener{

        void getDropsAmount();

        void getDrop(int dropNumber);

        void saveClicked(int dropNumber, Assessment assessment);

    }
}
