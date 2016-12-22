package com.whrsmxmx.eqa.add_patient;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.Patient;
import com.whrsmxmx.eqa.utils.DefaultDateFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 */
public class PatientFragment extends Fragment implements PatientContract.View {

    private PatientContract.UserActionsListener mListener;

    private TextView mDateTextView;
    private EditText mNameEditText;
    private Spinner mProcedureSpinner;
    private Spinner mDropNumberSpinner;
    private Button mOkButton;

    private Calendar mCalendar;
    final ArrayList<String> dropsNumberArray = new ArrayList<>(
            Arrays.asList(getResources().getStringArray(R.array.drop_number_string_array))
    );

    private String mName;

    public PatientFragment() {
        // Required empty public constructor
    }

    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = new PatientPresenter(((PatientActivity)getActivity()).getHelper().getSimpleDataDao(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_patient, container, false);

        bind(v);
        init();

        return v;
    }

    private void bind(View v) {
        mDateTextView = (TextView) v.findViewById(R.id.date_text_view);
        mNameEditText = (EditText) v.findViewById(R.id.name_edit_text);
        mProcedureSpinner = (Spinner) v.findViewById(R.id.procedure_type_spinner);
        mDropNumberSpinner = (Spinner) v.findViewById(R.id.drops_number_spinner);
        mOkButton = (Button) v.findViewById(R.id.btn);
    }

    private void init() {
        mCalendar = Calendar.getInstance();
        final DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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

        mDropNumberSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                dropsNumberArray));

        mProcedureSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.procedure_string_array)));
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.saveUser(mNameEditText.getText().toString(),
                        mProcedureSpinner.getSelectedItemPosition(),
                        mCalendar.getTime(),
                        Integer.valueOf(dropsNumberArray.get(mDropNumberSpinner.getSelectedItemPosition())));
                getActivity().setResult(Activity.RESULT_OK);
            }
        });

        if (!getActivity().getIntent().hasExtra(Patient.PERSON_ID)){
            dateDialog.show();
            mDateTextView.setText(DefaultDateFormatter.format(mCalendar.getTime()));
        }else{
            mListener.openUserInfoForUpdate(getActivity().getIntent().getStringExtra(Patient.PERSON_ID));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showUserInfo(Patient patient) {
        mDateTextView.setText(DefaultDateFormatter.format(patient.getCreationDate()));
        mDropNumberSpinner.setSelection(dropsNumberArray.indexOf(patient.getDropsNumber()));
    }

    @Override
    public void showUserList() {
        getActivity().finish();
    }
}
