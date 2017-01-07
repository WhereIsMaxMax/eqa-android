package com.whrsmxmx.eqa.assesment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.Drop;
import com.whrsmxmx.eqa.data.Patient;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentFragment extends Fragment implements AssessmentContract.View, AssessmentView.DropViewSavedListener{

    final static String TAG = AssessmentFragment.class.getName();
    private AssessmentContract.UserActionsListener mListener;
    private String patient_id;

    private AssessmentView assessmentView;
    private DropsView mDropsView;
    private FrameLayout dropsContainer;
//    private Drop mDrop;
    private int mDropNumber = 0;
//    private int mDropsNumber;


    public AssessmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getActivity().getIntent().hasExtra(Patient.PERSON_ID)){
            patient_id = getActivity().getIntent().getStringExtra(Patient.PERSON_ID);
        }else
            Log.w(TAG, "Has no patient id!!!");

        mListener = (AssessmentContract.UserActionsListener) new AssessmentPresenter(
                ((AssessmentActivity)getActivity()).getHelper().getSimpleDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDropDataDao(), this, patient_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.content_assessment, container, false);

        bind(v);

        return v;
    }

    private void bind(View v) {
        dropsContainer = (FrameLayout) v.findViewById(R.id.drops_container);
        FrameLayout assessmentContainer = (FrameLayout) v.findViewById(R.id.assessment_container);

        assessmentView = new AssessmentView(getContext());
        assessmentView.setOnSaveListener(this);
        assessmentContainer.addView(assessmentView);

        mListener.getDropsAmount();

        mListener.getDrop(mDropNumber);

        Log.i(TAG, "openDrop " + mDropNumber);
    }

    @Override
    public void setDropsAmount(int dropsNumber) {
        mDropsView = new DropsView(getActivity(), dropsNumber);
        mDropsView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        dropsContainer.addView(mDropsView);
    }

    @Override
    public void dropSaved() {
        Log.i(TAG, "dropSaved " + mDropNumber);
        mDropNumber++;
        Log.i(TAG, "getDrop " + mDropNumber);
        mListener.getDrop(mDropNumber);
    }

    @Override
    public void lastDropSaved() {
        Toast.makeText(getContext(), "Last drop was saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void openDrop(boolean isDegenerate, String blastomeres, int fragmentationPercent,
                         ArrayList<String> anomalies, String note) {

        mDropsView.changeDrop(mDropNumber);
        assessmentView.setDropInfo(mDropNumber, isDegenerate, blastomeres, fragmentationPercent, anomalies, note);
    }


    public void onDropChanged(int dropNumber) {
        mListener.getDrop(dropNumber);
    }

    @Override
    public void onDropSave() {
        mListener.saveClicked(assessmentView.saveDrop());
    }

    @Override
    public void openPatientsList() {

    }
}
