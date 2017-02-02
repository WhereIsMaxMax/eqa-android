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
public class Day7Fragment extends Fragment implements DecisionView.DecisionInterface{

    final static String DAY_TAG = "DAY";

    private Button saveButton;
    private EditText notesEditText;
    private DecisionView mDecisionView;

    private Day7Fragment.OnAssessmentListener mSaveListener;


    public static Day7Fragment newInstance(){
        return new Day7Fragment();
    }

    public Day7Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day7, container, false);

        bind(v);

        init();

        return v;
    }

    private void bind(View v) {
        saveButton = (Button) v.findViewById(R.id.save_button);
        notesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);
    }

    private void init() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveListener.onSaveClicked(
                        mDecisionView.getDecision(),
                        notesEditText.getText().toString()
                );
            }
        });
    }

    public void setInfo(String decision, String notes){
        mDecisionView.setDecisionSelection(decision);
        notesEditText.setText(notes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Day7Fragment.OnAssessmentListener) {
            mSaveListener = (Day7Fragment.OnAssessmentListener) context;
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
                           String note);

        void onFragmentCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSaveListener.onFragmentCreated();
    }
}
