package com.whrsmxmx.eqa.assesment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.assesment.DecisionView;

import java.util.ArrayList;
import java.util.Arrays;

public class Day5Day6Fragment extends Fragment implements DecisionView.DecisionInterface {

    private static final String DAY_TAG = "DAY";
    private boolean mIsDay5;

    private Spinner mDevStageSpinner;
    private Spinner mICMSpinner;
    private Spinner mTESpinner;
    private Button saveButton;
    private EditText notesEditText;
    //    private CheckBox isDegenerateCheckBox;
    private DecisionView mDecisionView;

    private ArrayList<String> mDevStageArray;
    private ArrayList<String> mICMArray;
    private ArrayList<String> mTEArray;
    private Day5Day6Fragment.OnAssessmentListener mSaveListener;

    public Day5Day6Fragment() {
        // Required empty public constructor
    }

    public static Day5Day6Fragment newInstance(boolean isDay5) {
        Day5Day6Fragment day5day6Fragment = new Day5Day6Fragment();
        Bundle b = new Bundle();
        b.putBoolean(DAY_TAG, isDay5);
        day5day6Fragment.setArguments(b);
        return day5day6Fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_day5, container, false);

        if(getArguments()!=null&&!getArguments().isEmpty())
            mIsDay5 = getArguments().getBoolean(DAY_TAG);

        bind(v);

        init();

        return v;
    }


    private void bind(View v) {
        mDevStageSpinner = (Spinner) v.findViewById(R.id.first_spinner);
        mICMSpinner = (Spinner) v.findViewById(R.id.second_spinner);
        mTESpinner = (Spinner) v.findViewById(R.id.third_spinner);
        saveButton = (Button) v.findViewById(R.id.save_button);
        notesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);
    }

    private void init() {
        mDevStageArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.development_stage_string_array))
        );
        mICMArray = new ArrayList<>(Arrays.asList(
                getResources().getStringArray(R.array.ICM_string_array))
        );
        mTEArray = new ArrayList<>(Arrays.asList(
                getResources().getStringArray(R.array.TE_string_array))
        );

        mDevStageSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mDevStageArray));

        mICMSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mICMArray));

        mTESpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mTEArray));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveListener.onSaveClicked(
                        mDecisionView.getDecision(),
                        mIsDay5,
                        mDevStageArray.get(mDevStageSpinner.getSelectedItemPosition()),
                        mTEArray.get(mTESpinner.getSelectedItemPosition()),
                        mICMArray.get(mICMSpinner.getSelectedItemPosition()),
                        notesEditText.getText().toString()
                );
            }
        });
    }

    public void setInfo(String decision, boolean isDay5, String devStage, String ICM,
                        String TE, String notes){
        mIsDay5 = isDay5;

        mDecisionView.setDecisionSelection(decision);
        mDevStageSpinner.setSelection(devStage.isEmpty()?0:mDevStageArray.indexOf(devStage));
        mTESpinner.setSelection(TE.isEmpty()?0:mTEArray.indexOf(TE));
        mICMSpinner.setSelection(ICM.isEmpty()?0:mICMArray.indexOf(ICM));
        notesEditText.setText(notes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Day5Day6Fragment.OnAssessmentListener) {
            mSaveListener = (Day5Day6Fragment.OnAssessmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSaveListener = null;
    }

    @Override
    public void onDecisionClick(int decision) {

    }

    public interface OnAssessmentListener {
        void onSaveClicked(String decision,
                           boolean is5Day,
                           String devStage,
                           String ICM,
                           String TE,
                           String note);

        void onFragmentCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSaveListener.onFragmentCreated();
    }
}
