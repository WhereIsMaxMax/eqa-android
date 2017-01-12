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
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssessmentFragment extends Fragment implements AssessmentContract.View, DropViewSavedListener{

    final static String TAG = AssessmentFragment.class.getName();
    private AssessmentContract.UserActionsListener mListener;
    private String patient_id;

    private FrameLayout assessmentContainer;
    private DropsView mDropsView;
    private FrameLayout dropsContainer;
    private int mDropNumber = 0;
    private AssessmentViewInterface mAssessmentView;
//    private int mDay = 0;


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
                ((AssessmentActivity)getActivity()).getHelper().getDropDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay0AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay1AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay2AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay3AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay4AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay5AssessmentDataDao(),
                ((AssessmentActivity)getActivity()).getHelper().getDay6AssessmentDataDao(),
                this, patient_id);

//        mListener.getDay();
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
        assessmentContainer = (FrameLayout) v.findViewById(R.id.assessment_container);

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
        getActivity().finish();
    }

    public void onDropChanged(int dropNumber) {
        mDropNumber = dropNumber;
        mListener.getDrop(dropNumber);
    }

    @Override
    public void onDropSave() {
        mListener.saveClicked(mDropNumber, mAssessmentView.saveInfo());
    }

    @Override
    public void open0Assessment(boolean isDegenerate, String maturity, ArrayList<String> zonaPellucida,
                                ArrayList<String> pvs, ArrayList<String> membrane,
                                ArrayList<String> cytoplasm, String pbi, String note) {

        mDropsView.changeDrop(mDropNumber);

        AssessmentView01 view = new AssessmentView01(getContext());
        mAssessmentView = view;
        view.setOnSaveListener(this);
        assessmentContainer.removeAllViews();
        assessmentContainer.addView(view);
        view.setInfo(isDegenerate, maturity,
                zonaPellucida, pvs, membrane, cytoplasm, pbi, note);
    }

    @Override
    public void open1Assessment(boolean isDegenerate, String maturity, ArrayList<String> zonaPellucida, ArrayList<String> pvs, ArrayList<String> membrane, ArrayList<String> cytoplasm, String pbi, String note) {

    }

    @Override
    public void open2Assessment(boolean isDegenerate, String blastomeres, int fragmentationPercent, ArrayList<String> anomalies, String note) {

    }

    @Override
    public void open3Assessment(boolean isDegenerate, String blastomeres, int fragmentationPercent, ArrayList<String> anomalies, String note) {

    }

    @Override
    public void open4Assessment(String developmentStage) {

    }

    @Override
    public void open5Assessment(String developmentStage, String bkm, String te) {

    }

    @Override
    public void open6Assessment(String developmentStage, String bkm, String te) {

    }

    @Override
    public void openDecision(String decision) {

    }

    @Override
    public void openPatientsList() {

    }
}
