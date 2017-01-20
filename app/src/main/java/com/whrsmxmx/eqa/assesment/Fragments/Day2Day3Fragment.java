package com.whrsmxmx.eqa.assesment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.assesment.DecisionView;

import static com.whrsmxmx.eqa.utils.StringsTricks.*;

public class Day2Day3Fragment extends Fragment implements DecisionView.DecisionInterface{

    final static String DAY_TAG = "DAY";

    private boolean mIsDay3;
    private LinearLayout mFeaturesContainer;
    private TextView mFeaturesTextView;
    private Spinner fragmentationSpinner;
    private Spinner blastomeresSpinner;
    private Button saveButton;
    private EditText notesEditText;
//    private CheckBox isDegenerateCheckBox;
    private DecisionView mDecisionView;

    private boolean [] mFeaturesArrayCheckedList;
    private String [] mFeaturesArray;
    private ArrayList <String> mFeaturesSelectedArray = new ArrayList<>();
    private ArrayList<Integer> fragmentationArray;
    private ArrayList<String> blastomeresArray;

    private OnAssessment2Listener mSaveListener;


    public static Day2Day3Fragment newInstance(boolean isDay3){
        Day2Day3Fragment day2Fragment = new Day2Day3Fragment();
        Bundle b = new Bundle();
        b.putBoolean(DAY_TAG, isDay3);
        day2Fragment.setArguments(b);
        return day2Fragment;
    }

    public Day2Day3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day2, container, false);

        if(!getArguments().isEmpty())
            mIsDay3 = getArguments().getBoolean(DAY_TAG);

        bind(v);

        init();

        return v;
    }

    private void bind(View v) {
//        binding
        mFeaturesContainer = (LinearLayout) v.findViewById(R.id.properties_container);
        mFeaturesTextView = (TextView) v.findViewById(R.id.properties_text_view);
        fragmentationSpinner = (Spinner) v.findViewById(R.id.fragmentation_spinner);
        blastomeresSpinner = (Spinner) v.findViewById(R.id.blastomeres_spinner);
        saveButton = (Button) v.findViewById(R.id.save_button);
        notesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
//        isDegenerateCheckBox = (CheckBox) v.findViewById(R.id.is_degenerate_checkbox);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);
    }

    private void init() {
        mFeaturesArray = getResources().getStringArray(R.array.properties);
        mFeaturesArrayCheckedList = new boolean[mFeaturesArray.length];
        int[] frgmtA = getResources().getIntArray(R.array.fragmentation_percent);
        fragmentationArray = new ArrayList<Integer>();
        for(int frgmt : frgmtA){
            fragmentationArray.add(frgmt);
        }
        blastomeresArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(mIsDay3 ?
                        R.array.blastomeres_number_day2_string_array :
                        R.array.blastomeres_number_day3_string_array))
        );

//        isDegenerateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                blastomeresSpinner.setEnabled(!isChecked);
//                fragmentationSpinner.setEnabled(!isChecked);
//                notesEditText.setEnabled(!isChecked);
//
//            }
//        });
        fragmentationSpinner.setAdapter(new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                fragmentationArray));
        blastomeresSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.blastomeres_number_day2_string_array)));

        mFeaturesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isDegenerateCheckBox.isChecked()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMultiChoiceItems(mFeaturesArray, mFeaturesArrayCheckedList,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    mFeaturesArrayCheckedList[which] = isChecked;
                                    if(isChecked)
                                        mFeaturesSelectedArray.add(mFeaturesArray[which]);
                                    else
                                        mFeaturesSelectedArray.remove(mFeaturesArray[which]);
                                }
                            });
                    builder.setTitle(R.string.properties);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String anomaliesText = "";
                            for (String s : mFeaturesSelectedArray){
                                anomaliesText += s +"\n";
                            }
                            mFeaturesTextView.setText(removeLastCharIfNotEmpty(anomaliesText));
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();

                    dialog.show();
//                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveListener.onSaveClicked(
                        mIsDay3,
                        mDecisionView.getDecision(),
                        blastomeresArray.get(blastomeresSpinner.getSelectedItemPosition()),
                        fragmentationArray.get(fragmentationSpinner.getSelectedItemPosition()),
                        mFeaturesSelectedArray,
                        notesEditText.getText().toString()
                );
            }
        });
    }

    public void setInfo(String decision, boolean isDay3, String blastomeres,
                            int fragmentation, ArrayList<String> properties, String notes){

        mIsDay3 = isDay3;
//        isDegenerateCheckBox.setChecked(isDegenerate);
        mDecisionView.setDecisionSelection(decision);
        blastomeresSpinner.setSelection(blastomeresArray.indexOf(blastomeres));
        fragmentationSpinner.setSelection(fragmentationArray.indexOf(fragmentation));
        mFeaturesSelectedArray = properties;

        String textValue = "";
        for(String s : properties){
            int index = Arrays.asList(mFeaturesArray).indexOf(s);
            mFeaturesArrayCheckedList[index] = true;
            textValue = textValue + mFeaturesArray[index] + "\n";
        }
        mFeaturesTextView.setText(removeLastCharIfNotEmpty(textValue));
        notesEditText.setText(notes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Day2Day3Fragment.OnAssessment2Listener) {
            mSaveListener = (Day2Day3Fragment.OnAssessment2Listener) context;
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

    public interface OnAssessment2Listener {
        void onSaveClicked(boolean is3Day,
                           String decision,
                           String blastomeres,
                           int percent,
                           ArrayList<String> anomalies,
                           String note);

        void onFragmentCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSaveListener.onFragmentCreated();
    }
//
//    public void clear(){
//        isDegenerateCheckBox.setChecked(false);
//        blastomeresSpinner.setSelection(0);
//        fragmentationSpinner.setSelection(0);
//        mFeaturesTextView.setText("");
//        notesEditText.setText("");
//    }
//
//    public Day2Assessment saveAssessment(){
//        return new Day2Assessment(isDegenerateCheckBox.isChecked(),
//                blastomeresArray.get(blastomeresSpinner.getSelectedItemPosition()),
//                fragmentationArray.get(fragmentationSpinner.getSelectedItemPosition()),
//                mFeaturesSelectedArray, notesEditText.getText().toString());
//    }
//
////    public void setOnSaveListener(DropViewSavedListener listener) {
////        mSaveListener = listener;
////    }
//
}
