package com.whrsmxmx.eqa.add_patient;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;

import io.fabric.sdk.android.Fabric;

public class PatientActivity extends AppCompatOrmActivity<DatabaseHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_add_person);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getResources().getString(R.string.patient));
        }

        initFragment(PatientFragment.newInstance());
    }

    private void initFragment(PatientFragment patientFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                patientFragment).commit();
    }
}
