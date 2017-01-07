package com.whrsmxmx.eqa.assesment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;

public class AssessmentActivity extends AppCompatOrmActivity<DatabaseHelper> implements DropsInteraction{

    AssessmentFragment assessmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        setupFragment();
    }

    private void setupFragment() {
        assessmentFragment = new AssessmentFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, assessmentFragment).commit();
    }


    @Override
    public void onDropClicked(int dropNumber) {
        assessmentFragment.onDropChanged(dropNumber);
        Toast.makeText(this, "Drop # "+dropNumber, Toast.LENGTH_LONG).show();

    }

}