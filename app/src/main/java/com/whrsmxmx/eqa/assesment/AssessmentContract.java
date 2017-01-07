package com.whrsmxmx.eqa.assesment;

import com.whrsmxmx.eqa.data.Drop;

/**
 * Created by Max on 22.12.2016.
 */

public interface AssessmentContract {

    interface View{

        void openDrop(Drop drop);

        void openPatientsList();

    }

    interface UserActionsListener{

        void dropClicked(int dropNumber);

        void saveClicked(Drop drop);

    }
}
