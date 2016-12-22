package com.whrsmxmx.eqa.patients_list;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;

public class PatientsListActivity extends AppCompatOrmActivity<DatabaseHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        initFragment(PatientsListFragment.newInstance());
    }

    private void initFragment(PatientsListFragment mainFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content_frame, mainFragment);
        transaction.commit();
    }
}
