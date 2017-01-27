package com.whrsmxmx.eqa.add_patient;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.model.Patient;
import com.whrsmxmx.eqa.utils.DefaultDateFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
    private ArrayList<Integer> mDropsNumberArray;
    private ArrayList<String> mProcedureArray;

    public PatientFragment() {
        // Required empty public constructor
    }

    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListener = new PatientPresenter(((PatientActivity)getActivity()).getHelper().getSimpleDataDao(),
                ((PatientActivity)getActivity()).getHelper().getDropDataDao(),
                this);
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

        int[] drop_number_array = getResources().getIntArray(R.array.drop_number_array);
        mDropsNumberArray = new ArrayList<Integer>();

        for (int aDrop_number_array : drop_number_array) {
            mDropsNumberArray.add(aDrop_number_array);
        }

        mDropNumberSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                mDropsNumberArray));

        mProcedureArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.procedure_string_array)));

        mProcedureSpinner.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, mProcedureArray
                ));

        if (!getActivity().getIntent().hasExtra(Patient.PERSON_ID)){
            dateDialog.show();
            mDateTextView.setText(DefaultDateFormatter.format(mCalendar.getTime()));
            mOkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    set calendar time to midnight, to facilitate day calculation
                    mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                    mCalendar.set(Calendar.MINUTE, 0);
//                    mCalendar.setTime(new Date(mCalendar.getTime().getTime() + mCalendar.getTimeZone().getRawOffset()));
                    mListener.saveUser(mNameEditText.getText().toString(),
                            mProcedureArray.get(mProcedureSpinner.getSelectedItemPosition()),
                            mCalendar.getTime(),
                            mDropsNumberArray.get(mDropNumberSpinner.getSelectedItemPosition()));
                }
            });
        }else{
            mOkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.updateUser(mNameEditText.getText().toString(),
                            mProcedureArray.get(mProcedureSpinner.getSelectedItemPosition()),
                            mCalendar.getTime(),
                            mDropsNumberArray.get(mDropNumberSpinner.getSelectedItemPosition()));
                }
            });
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
        mNameEditText.setText(patient.getName());
        mDateTextView.setText(DefaultDateFormatter.format(patient.getCreationDate()));
        mDropNumberSpinner.setSelection(mDropsNumberArray.indexOf(patient.getDropsNumber()));
        mProcedureSpinner.setSelection(mProcedureArray.indexOf(patient.getProcedure()));
    }

    @Override
    public void showUserList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showNameExistsDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.oops)
                .setMessage(R.string.another_name)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
