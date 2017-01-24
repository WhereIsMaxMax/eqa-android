package com.whrsmxmx.eqa.assesment;

import com.whrsmxmx.eqa.data.database.model.Day0Assessment;
import com.whrsmxmx.eqa.data.database.model.Day1Assessment;
import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
import com.whrsmxmx.eqa.data.database.model.Day3Assessment;
import com.whrsmxmx.eqa.data.database.model.Day4Assessment;
import com.whrsmxmx.eqa.data.database.model.Day5Assessment;
import com.whrsmxmx.eqa.data.database.model.Day6Assessment;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Max on 22.12.2016.
 */

public interface AssessmentContract {

    interface View{

//        day 0
        void openAssessment(String decision,
                             String maturity,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

//        day 1
        void openAssessment(String decision,
                             String maturity,
                             String npb,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

//        day 2 and day 3
        void openAssessment(String decision,
                            boolean isDay3,
                             String blastomeres,
                             int fragmentationPercent,
                             ArrayList<String> anomalies,
                             String note);

//        day 4
        void openAssessment(String decision,
                            String developmentStage,
                            String note);

//        day 5 and day 6
        void openAssessment(String decision,
                            boolean isDay5,
                            String developmentStage,
                            String ism,
                            String te,
                            String note);

        void setDropsAmount(int dropsNumber);

        void showNextDrop();

        void lastDropSaved();

        void setDay(int day, String patientName);
    }

    interface UserActionsListener{

        void getDay();

        void getDrop(int dropNumber, boolean isDirectClick);

//        day 0
        void saveClicked(int dropNumber,
                         String decision,
                         String maturity,
                         ArrayList<String> zonaPellucida,
                         ArrayList<String> pvs,
                         ArrayList<String> membrane,
                         ArrayList<String> cytoplasm,
                         String dirBody,
                         String note);

//        day 1
        void saveClicked(int dropNumber,
                         String decision,
                         String maturity,
                         String npbs,
                         ArrayList<String> zonaPellucida,
                         ArrayList<String> pvs,
                         ArrayList<String> membrane,
                         ArrayList<String> cytoplasm,
                         String dirBody,
                         String note);

//        day 2 and day 3
        void saveClicked(int dropNumber,
                         boolean is3Day,
                         String decision,
                         String blastomeres,
                         int percent,
                         ArrayList<String> anomalies,
                         String note);

//        day 4
        void saveClicked(int dropNumber,
                         String decision,
                         String devStage,
                         String note);

//        day 5 and day 6
        void saveClicked(int dropNumber,
                         String decision,
                         boolean is5Day,
                         String devStage,
                         String ICM,
                         String TE,
                         String note);
    }
}
