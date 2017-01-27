package com.whrsmxmx.eqa.assesment.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Day4Fragment extends Fragment implements DecisionView.DecisionInterface{

    final static String DAY_TAG = "DAY";

    private Spinner mSpinner;
    private Button saveButton;
    private EditText notesEditText;
    private DecisionView mDecisionView;
    private ArrayList<String> mStrings;

    private Day4Fragment.OnAssessmentListener mSaveListener;


    public static Day4Fragment newInstance(){
        return new Day4Fragment();
    }

    public Day4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day4, container, false);

        bind(v);

        init();

        return v;
    }

    private void bind(View v) {
        mSpinner = (Spinner) v.findViewById(R.id.spinner);
        saveButton = (Button) v.findViewById(R.id.save_button);
        notesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);
    }

    private void init() {

        mStrings = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.day4_string_array))
        );

        mSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mStrings));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveListener.onSaveClicked(
                        mDecisionView.getDecision(),
                        mStrings.get(mSpinner.getSelectedItemPosition()),
                        notesEditText.getText().toString()
                );
            }
        });
    }

    public void setInfo(String decision, String devStage, String notes){
        mDecisionView.setDecisionSelection(decision);
        mSpinner.setSelection(devStage.isEmpty()?0:mStrings.indexOf(devStage));
        notesEditText.setText(notes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Day4Fragment.OnAssessmentListener) {
            mSaveListener = (Day4Fragment.OnAssessmentListener) context;
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
                           String devStage,
                           String note);

        void onFragmentCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSaveListener.onFragmentCreated();
    }
}
