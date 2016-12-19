package com.whrsmxmx.eqa.add_patient;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.data.Patient;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.utils.DefaultDateFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class PersonActivity extends AppCompatOrmActivity<DatabaseHelper> {

    private TextView mDateTextView;
    private EditText mNameEditText;
    private Spinner mProcedureSpinner;
    private Spinner mDropNumberSpinner;
    private Button mOkButton;

    private RuntimeExceptionDao<Patient, String> mPatientDao;
    private Calendar mCalendar;
    private Patient mPatient;

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.patient));
        setSupportActionBar(toolbar);

        mPatientDao = getHelper().getSimpleDataDao();

        if(getIntent().hasExtra(Patient.NAME)){
            mName = getIntent().getStringExtra(Patient.NAME);
            mPatient = mPatientDao.queryForId(mName);
        }

        bind();
        init();
    }

    private void bind() {
        mDateTextView = (TextView) findViewById(R.id.date_text_view);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mProcedureSpinner = (Spinner) findViewById(R.id.procedure_type_spinner);
        mDropNumberSpinner = (Spinner) findViewById(R.id.drops_number_spinner);
        mOkButton = (Button) findViewById(R.id.btn);
    }

    private void init() {
        mCalendar = Calendar.getInstance();
        final DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mCalendar.set(i, i1, i2);
                mDateTextView.setText(DefaultDateFormatter.format(mCalendar.getTime()));
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog.show();
            }
        });

        final ArrayList<String>dropsNumberArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.drop_number_string_array))
        );

        mDropNumberSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                dropsNumberArray));

        mProcedureSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.procedure_string_array)));
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient p = new Patient(mNameEditText.getText().toString(),
                        mProcedureSpinner.getSelectedItemPosition(),
                        mCalendar.getTime(),
                        Integer.valueOf(dropsNumberArray.get(mDropNumberSpinner.getSelectedItemPosition())));
                mPatientDao.create(p);
                setResult(RESULT_OK);
                finish();
            }
        });


        if (mPatient==null){
            dateDialog.show();
            mDateTextView.setText(DefaultDateFormatter.format(mCalendar.getTime()));
        }else{
            mDateTextView.setText(DefaultDateFormatter.format(mPatient.getCreationDate()));
//            todo check
            mDropNumberSpinner.setSelection(dropsNumberArray.indexOf(mPatient.getDropsNumber()));
        }
    }
}
