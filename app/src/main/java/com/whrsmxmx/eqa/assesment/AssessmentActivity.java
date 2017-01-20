package com.whrsmxmx.eqa.assesment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.assesment.fragments.Day0Fragment;
import com.whrsmxmx.eqa.assesment.fragments.Day1Fragment;
import com.whrsmxmx.eqa.assesment.fragments.Day2Day3Fragment;
import com.whrsmxmx.eqa.assesment.fragments.Day4Fragment;
import com.whrsmxmx.eqa.assesment.fragments.Day5Day6Fragment;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.ArrayList;

public class AssessmentActivity extends AppCompatOrmActivity<DatabaseHelper>
        implements AssessmentContract.View, DropsInteractionInterface, Day0Fragment.OnAssessment0Listener,
        Day1Fragment.OnAssessment1Listener, Day2Day3Fragment.OnAssessment2Listener,
        Day4Fragment.OnAssessmentListener, Day5Day6Fragment.OnAssessmentListener, DecisionView.DecisionInterface{

    final static String TAG = AssessmentActivity.class.getName();
    private AssessmentContract.UserActionsListener mListener;
    private String mPatient_id;

    private FrameLayout assessmentContainer;
    private DropsView mDropsView;
    private FrameLayout dropsContainer;
    private int mDropNumber = 0;
    private Fragment mAssessmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra(Patient.PERSON_ID)){
            mPatient_id = getIntent().getStringExtra(Patient.PERSON_ID);
        }else
            Log.w(TAG, "Has no patient id!!!");

        bind();

        mListener = (AssessmentContract.UserActionsListener) new AssessmentPresenter(
                getHelper().getSimpleDataDao(),
                getHelper().getDropDataDao(),
                getHelper().getDay0AssessmentDataDao(),
                getHelper().getDay1AssessmentDataDao(),
                getHelper().getDay2AssessmentDataDao(),
                getHelper().getDay3AssessmentDataDao(),
                getHelper().getDay4AssessmentDataDao(),
                getHelper().getDay5AssessmentDataDao(),
                getHelper().getDay6AssessmentDataDao(),
                this, mPatient_id);

        mListener.getDay();
    }

    private void bind() {
        dropsContainer = (FrameLayout) findViewById(R.id.drops_container);
        assessmentContainer = (FrameLayout) findViewById(R.id.assessment_container);
    }

    @Override
    public void setDropsAmount(int dropsNumber) {
        mDropsView = new DropsView(this, dropsNumber);
        mDropsView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        dropsContainer.addView(mDropsView);
    }

    @Override
    public void setDay(int day, String patientName) {
        getSupportActionBar().setTitle(patientName + " day " + day);
        Log.i(TAG, "setDay " + day);
        switch (day){
            case 0:
                mAssessmentFragment = Day0Fragment.newInstance();
                break;
            case 1:
                mAssessmentFragment = Day1Fragment.newInstance();
                break;
            case 2:
                mAssessmentFragment = Day2Day3Fragment.newInstance(false);
                break;
            case 3:
//                use same fragment but set flag
                mAssessmentFragment = Day2Day3Fragment.newInstance(true);
                break;
            case 4:
                mAssessmentFragment = Day4Fragment.newInstance();
                break;
            case 5:
                mAssessmentFragment = Day5Day6Fragment.newInstance(true);
                break;
            case 6:
                mAssessmentFragment = Day5Day6Fragment.newInstance(false);
                break;
            default:
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.assessment_container, mAssessmentFragment).commit();
    }

//    day0
    @Override
    public void openAssessment(String decision, String maturity,
                               ArrayList<String> zonaPellucida,
                               ArrayList<String> pvs, ArrayList<String> membrane,
                               ArrayList<String> cytoplasm, String pbi, String note) {
        ((Day0Fragment)mAssessmentFragment).setInfo(decision, maturity, zonaPellucida,
                pvs, membrane, cytoplasm, pbi, note);
    }

//    day1
    @Override
    public void openAssessment(String decision, String maturity, String npb,
                               ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                               ArrayList<String> membrane, ArrayList<String> cytoplasm,
                               String pbi, String note) {
        ((Day1Fragment)mAssessmentFragment).setInfo(decision, maturity, npb, zonaPellucida,
                pvs, membrane, cytoplasm, pbi, note);
    }

//    day2 and day3
    @Override
    public void openAssessment(String decision, boolean isDay3, String blastomeres, int fragmentationPercent,
                               ArrayList<String> anomalies, String note) {
        ((Day2Day3Fragment)mAssessmentFragment).setInfo(decision, isDay3, blastomeres, fragmentationPercent,
                anomalies, note);
    }

//    day4
    @Override
    public void openAssessment(String decision, String developmentStage, String note) {
        ((Day4Fragment)mAssessmentFragment).setInfo(decision, developmentStage, note);
    }

//    day5 and day6
    @Override
    public void openAssessment(String decision, boolean isDay5, String developmentStage,
                               String ism, String te, String note) {
        ((Day5Day6Fragment)mAssessmentFragment).setInfo(decision, isDay5, developmentStage, ism,
                te, note);
    }


    @Override
    public void onDropClicked(int dropNumber) {
        mDropNumber = dropNumber;
        mListener.getDrop(dropNumber);
        Toast.makeText(this, "Drop # "+dropNumber, Toast.LENGTH_LONG).show();
    }

    @Override
    public void dropSaved() {
        Log.i(TAG, "dropSaved " + mDropNumber);
        mDropNumber++;
        Log.i(TAG, "getDrop " + mDropNumber);
        mListener.getDrop(mDropNumber);
        mDropsView.changeDrop(mDropNumber);
    }

    @Override
    public void lastDropSaved() {
        Toast.makeText(this, "Last drop was saved", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onSaveClicked(String decision, String maturity,
                              ArrayList<String> zonaPellucida,
                              ArrayList<String> pvs, ArrayList<String> membrane,
                              ArrayList<String> cytoplasm, String dirBody, String note) {
        mListener.saveClicked(mDropNumber, decision, maturity, zonaPellucida, pvs, membrane,
                cytoplasm,dirBody, note);
    }

    @Override
    public void onSaveClicked(String decision, String maturity, String npbs,
                              ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                              ArrayList<String> membrane, ArrayList<String> cytoplasm,
                              String dirBody, String note) {
        mListener.saveClicked(mDropNumber, decision, maturity, npbs, zonaPellucida, pvs,
                membrane, cytoplasm, dirBody, note);
    }

    @Override
    public void onSaveClicked(boolean is3Day, String decision, String blastomeres, int percent,
                              ArrayList<String> anomalies, String note) {
        mListener.saveClicked(mDropNumber, is3Day, decision, blastomeres, percent, anomalies, note);
    }

    @Override
    public void onSaveClicked(String decision, String devStage, String note) {
        mListener.saveClicked(mDropNumber, decision, devStage, note);
    }

    @Override
    public void onSaveClicked(String decision, boolean is5Day, String devStage, String ICM, String TE, String note) {
        mListener.saveClicked(mDropNumber, decision, is5Day, devStage, ICM, TE, note);
    }

    @Override
    public void onFragmentCreated() {
        mListener.getDrop(mDropNumber);
    }

    @Override
    public void onDecisionClick(int decision) {

    }
}