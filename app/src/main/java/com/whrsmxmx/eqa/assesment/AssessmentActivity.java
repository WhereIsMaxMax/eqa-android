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
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.util.ArrayList;

public class AssessmentActivity extends AppCompatOrmActivity<DatabaseHelper>
        implements AssessmentContract.View, DropsInteractionInterface, Day0Fragment.OnAssessment0Listener, Day1Fragment.OnAssessment1Listener {

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
//                getSupportFragmentManager().beginTransaction().replace(R.id.assessment_container, (Day0Fragment)mAssessmentFragment).commit();

                break;
            case 1:
                mAssessmentFragment = Day1Fragment.newInstance();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.assessment_container, mAssessmentFragment).commit();
    }

    @Override
    public void openAssessment(boolean isDegenerate, String maturity,
                               ArrayList<String> zonaPellucida,
                               ArrayList<String> pvs, ArrayList<String> membrane,
                               ArrayList<String> cytoplasm, String pbi, String note) {
        ((Day0Fragment)mAssessmentFragment).setInfo(isDegenerate, maturity, zonaPellucida,
                pvs, membrane, cytoplasm, pbi, note);
    }

    @Override
    public void openAssessment(boolean isDegenerate, String maturity, String npb,
                               ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                               ArrayList<String> membrane, ArrayList<String> cytoplasm,
                               String pbi, String note) {
        ((Day1Fragment)mAssessmentFragment).setInfo(isDegenerate, maturity, npb, zonaPellucida,
                pvs, membrane, cytoplasm, pbi, note);
    }

    @Override
    public void openAssessment(boolean isDegenerate, String blastomeres, int fragmentationPercent,
                               ArrayList<String> anomalies, String note) {

    }

    @Override
    public void openAssessment(String developmentStage) {

    }

    @Override
    public void openAssessment(boolean isDegenerate, String developmentStage, String bkm, String te, String note) {

    }

    @Override
    public void openDecision(String decision) {

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
    public void onSaveClicked(boolean isDegenerate, String maturity,
                              ArrayList<String> zonaPellucida,
                              ArrayList<String> pvs, ArrayList<String> membrane,
                              ArrayList<String> cytoplasm, String dirBody, String note) {
        mListener.saveClicked(mDropNumber, isDegenerate, maturity, zonaPellucida, pvs, membrane, cytoplasm,
                dirBody, note);
    }

    @Override
    public void onSaveClicked(boolean isDegenerate, String maturity, String npbs,
                              ArrayList<String> zonaPellucida, ArrayList<String> pvs,
                              ArrayList<String> membrane, ArrayList<String> cytoplasm,
                              String dirBody, String note) {
        mListener.saveClicked(mDropNumber, isDegenerate, maturity, npbs, zonaPellucida, pvs, membrane, cytoplasm,
                dirBody, note);
    }

    @Override
    public void onFragmentCreated() {
        mListener.getDrop(mDropNumber);
    }
}