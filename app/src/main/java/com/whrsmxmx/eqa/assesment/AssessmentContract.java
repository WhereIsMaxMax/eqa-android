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

        void openAssessment(boolean isDegenerate,
                             String maturity,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

        void openAssessment(boolean isDegenerate,
                             String maturity,
                             String npb,
                             ArrayList<String> zonaPellucida,
                             ArrayList<String> pvs,
                             ArrayList<String> membrane,
                             ArrayList<String> cytoplasm,
                             String pbi,
                             String note);

        void openAssessment(boolean isDegenerate,
                             String blastomeres,
                             int fragmentationPercent,
                             ArrayList<String> anomalies,
                             String note);

        void openAssessment(String developmentStage);

        void openAssessment(boolean isDegenerate,
                            String developmentStage,
                            String bkm,
                            String te,
                            String note);

        void openDecision(String decision);

        void setDropsAmount(int dropsNumber);

        void dropSaved();

        void lastDropSaved();

        void setDay(int day, String patientName);
    }

    interface UserActionsListener{

        void getDay();

        void getDrop(int dropNumber);

        void saveClicked(int dropNumber,
                         boolean isDegenerate,
                         String maturity,
                         ArrayList<String> zonaPellucida,
                         ArrayList<String> pvs,
                         ArrayList<String> membrane,
                         ArrayList<String> cytoplasm,
                         String dirBody,
                         String note);

        void saveClicked(int dropNumber, Day1Assessment assessment);
        void saveClicked(int dropNumber, Day2Assessment assessment);
        void saveClicked(int dropNumber, Day3Assessment assessment);
        void saveClicked(int dropNumber, Day4Assessment assessment);
        void saveClicked(int dropNumber, Day5Assessment assessment);
        void saveClicked(int dropNumber, Day6Assessment assessment);

    }

    interface Assessment0ViewInterface {
        Day0Assessment saveDay0Info();
    }

    interface Assessment1ViewInterface {
        Day1Assessment saveDay1Info();
    }

    interface Assessment2ViewInterface {
        Day2Assessment saveDay2Info();
    }

    interface Assessment3ViewInterface {
        Day3Assessment saveDay3Info();
    }

    interface Assessment4ViewInterface {
        Day4Assessment saveDay4Info();
    }

    interface Assessment5ViewInterface {
        Day5Assessment saveDay5Info();
    }

    interface Assessment6ViewInterface {
        Day6Assessment saveDay6Info();
    }
}
