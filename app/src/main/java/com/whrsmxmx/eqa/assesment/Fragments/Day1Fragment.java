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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.assesment.DecisionView;

import java.util.ArrayList;
import java.util.Arrays;

public class Day1Fragment extends Fragment implements DecisionView.DecisionInterface{

    //    views;
    private Spinner mMaturitySpinner;
    private Spinner mNPBSpinner;
    private LinearLayout mZonaContainer;
    private TextView mZonaTextView;
    private LinearLayout mPvsContainer;
    private TextView mPvsTextView;
    private LinearLayout mMembraneContainer;
    private TextView mMembraneTextView;
    private LinearLayout mCytoplasmContainer;
    private TextView mCytoplasmTextView;
    private Spinner mPbiSpinner;
    private Button saveButton;
    private EditText notesEditText;
//    private CheckBox isDegenerateCheckBox;
    private DecisionView mDecisionView;

    //    data containers;
    private ArrayList<String> mMaturityArray;
    private ArrayList<String> mPbiArray;
    private ArrayList<String> mNPBArray;

    private boolean [] mZonaArrayCheckedList;
    private String [] mZonaArray;

    private boolean [] mPvsArrayCheckedList;
    private String [] mPvsArray;

    private boolean [] mMembraneArrayCheckedList;
    private String [] mMembraneArray;

    private boolean [] mCytoplasmArrayCheckedList;
    private String [] mCytoplasmArray;

    private Day1Fragment.OnAssessment1Listener mSaveListener;

    public static Fragment newInstance() {
        return new Day1Fragment();
    }

    public Day1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day1, container, false);

        bind(v);

        init();

        return v;
    }


    private void bind(View v) {
//        binding
        mZonaContainer = (LinearLayout) v.findViewById(R.id.multiselect_container0);
        mZonaTextView = (TextView) v.findViewById(R.id.multiselect0_text_view);

        mPvsContainer = (LinearLayout) v.findViewById(R.id.multiselect_container1);
        mPvsTextView = (TextView) v.findViewById(R.id.multiselect1_text_view);

        mMembraneContainer = (LinearLayout) v.findViewById(R.id.multiselect_container2);
        mMembraneTextView = (TextView) v.findViewById(R.id.multiselect2_text_view);

        mCytoplasmContainer = (LinearLayout) v.findViewById(R.id.multiselect_container3);
        mCytoplasmTextView = (TextView) v.findViewById(R.id.multiselect3_text_view);

        mMaturitySpinner = (Spinner) v.findViewById(R.id.spinner0);
        mPbiSpinner = (Spinner) v.findViewById(R.id.spinner1);
        mNPBSpinner = (Spinner) v.findViewById(R.id.spinner2);

        saveButton = (Button) v.findViewById(R.id.save_button);
        notesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
//        isDegenerateCheckBox = (CheckBox) v.findViewById(R.id.is_degenerate_checkbox);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);

    }

    private void init() {
        mMaturityArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.maturity_day_1_string_array))
        );

        mPbiArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.pbi_string_array))
        );

        mNPBArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.npb_day_1_string_array))
        );

        mZonaArray = getResources().getStringArray(R.array.zona_pellucida_string_array);
        mZonaArrayCheckedList = new boolean[mZonaArray.length];
        mPvsArray = getResources().getStringArray(R.array.pvs_string_array);
        mPvsArrayCheckedList = new boolean[mPvsArray.length];
        mMembraneArray = getResources().getStringArray(R.array.membrane_string_array);
        mMembraneArrayCheckedList = new boolean[mMembraneArray.length];
        mCytoplasmArray = getResources().getStringArray(R.array.cytoplasm_string_array);
        mCytoplasmArrayCheckedList = new boolean[mCytoplasmArray.length];

//        initialisation
//        isDegenerateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mMaturitySpinner.setEnabled(!isChecked);
//                mPbiSpinner.setEnabled(!isChecked);
//                mNPBSpinner.setEnabled(!isChecked);
//                notesEditText.setEnabled(!isChecked);
//
//            }
//        });
        mMaturitySpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mMaturityArray));
        mPbiSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mPbiArray));
        mNPBSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mNPBArray));

        mZonaContainer.setOnClickListener(createClickListener(mZonaArray,
                mZonaArrayCheckedList, getResources().getString(R.string.zona_pellucida), mZonaTextView));

        mPvsContainer.setOnClickListener(createClickListener(mPvsArray, mPvsArrayCheckedList,
                getResources().getString(R.string.pvs), mPvsTextView));

        mCytoplasmContainer.setOnClickListener(createClickListener(mCytoplasmArray,
                mCytoplasmArrayCheckedList, getResources().getString(R.string.cytoplasm), mCytoplasmTextView));

        mMembraneContainer.setOnClickListener(createClickListener(mMembraneArray,
                mMembraneArrayCheckedList, getResources().getString(R.string.membrane), mMembraneTextView));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> zonaSelectedArray = new ArrayList<>();
                for (int i = 0; i < mZonaArray.length; i ++){
                    if(mZonaArrayCheckedList[i])
                        zonaSelectedArray.add(mZonaArray[i]);
                }
                ArrayList<String> pvsSelectedArray = new ArrayList<>();
                for (int i = 0; i < mPvsArray.length; i ++){
                    if(mPvsArrayCheckedList[i])
                        pvsSelectedArray.add(mPvsArray[i]);
                }
                ArrayList<String> membraneSelectedArray = new ArrayList<>();
                for (int i = 0; i < mMembraneArray.length; i ++){
                    if(mMembraneArrayCheckedList[i])
                        membraneSelectedArray.add(mMembraneArray[i]);
                }
                ArrayList<String> cytoplasmSelectedArray = new ArrayList<>();
                for (int i = 0; i < mCytoplasmArray.length; i ++){
                    if(mCytoplasmArrayCheckedList[i])
                        cytoplasmSelectedArray.add(mCytoplasmArray[i]);
                }
                mSaveListener.onSaveClicked(mDecisionView.getDecision(),
                        mMaturityArray.get(mMaturitySpinner.getSelectedItemPosition()),
                        mNPBArray.get(mNPBSpinner.getSelectedItemPosition()),
                        zonaSelectedArray,
                        pvsSelectedArray,
                        membraneSelectedArray,
                        cytoplasmSelectedArray,
                        mPbiArray.get(mPbiSpinner.getSelectedItemPosition()),
                        notesEditText.getText().toString());
            }
        });
    }

    private View.OnClickListener createClickListener(final String[] array, final boolean [] checkedArray,
                                                     final String title, final TextView textView){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isDegenerateCheckBox.isChecked()) {

                    final ArrayList<String> selectedArray = new ArrayList<>();
                    for (int i = 0; i < array.length; i ++){
                        if(checkedArray[i])
                            selectedArray.add(array[i]);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMultiChoiceItems(array, checkedArray,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    checkedArray[which] = isChecked;
                                    if(isChecked)
                                        selectedArray.add(array[which]);
                                    else
                                        selectedArray.remove(array[which]);
                                }
                            });
                    builder.setTitle(title);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String text = "";
                            for (String s : selectedArray){
                                text += s +"\n";
                            }
                            textView.setText(removeLastCharIfNotEmpty(text));
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();

                    dialog.show();
//                }
            }
        };
    }

    public void setInfo(String decision, String maturity, String npbs, ArrayList<String> zonaPellucida,
                        ArrayList<String> pvs, ArrayList<String> membrane,
                        ArrayList<String> cytoplasm, String pbi, String note) {

//        isDegenerateCheckBox.setChecked(isDegenerate);
        mDecisionView.setDecisionSelection(decision);

        mMaturitySpinner.setSelection(maturity.isEmpty()?0:mMaturityArray.indexOf(maturity));
        mPbiSpinner.setSelection(pbi.isEmpty()?0:mPbiArray.indexOf(pbi));
        mNPBSpinner.setSelection(npbs.isEmpty()?0:mNPBArray.indexOf(npbs));

        String zonaTextValue = "";
        mZonaArrayCheckedList = new boolean[mZonaArrayCheckedList.length];
        for(String s : zonaPellucida){
            int index = Arrays.asList(mZonaArray).indexOf(s);
            mZonaArrayCheckedList[index] = true;
            zonaTextValue = zonaTextValue + mZonaArray[index] + "\n";
        }
        mZonaTextView.setText(removeLastCharIfNotEmpty(zonaTextValue));

        String pvSTextValue = "";
        mPvsArrayCheckedList = new boolean[mPvsArrayCheckedList.length];
        for(String s : pvs){
            int index = Arrays.asList(mPvsArray).indexOf(s);
            mPvsArrayCheckedList[index] = true;
            pvSTextValue = pvSTextValue + mPvsArray[index] + "\n";
        }
        mPvsTextView.setText(removeLastCharIfNotEmpty(pvSTextValue));

        String membraneTextValue = "";
        mMembraneArrayCheckedList = new boolean[mMembraneArrayCheckedList.length];
        for(String s : membrane){
            int index = Arrays.asList(mMembraneArray).indexOf(s);
            mMembraneArrayCheckedList[index] = true;
            membraneTextValue = membraneTextValue + mMembraneArray[index] + "\n";
        }
        mMembraneTextView.setText(removeLastCharIfNotEmpty(membraneTextValue));

        String cytoplasmTextValue = "";
        mCytoplasmArrayCheckedList = new boolean[mCytoplasmArrayCheckedList.length];
        for(String s : cytoplasm){
            int index = Arrays.asList(mCytoplasmArray).indexOf(s);
            mCytoplasmArrayCheckedList[index] = true;
            cytoplasmTextValue = cytoplasmTextValue + mCytoplasmArray[index] + "\n";
        }
        mCytoplasmTextView.setText(removeLastCharIfNotEmpty(cytoplasmTextValue));

        notesEditText.setText(note);
    }

    private String removeLastCharIfNotEmpty(String text){
        if (!text.isEmpty())
            text = text.substring(0, text.length()-1);
        return text;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAssessment1Listener) {
            mSaveListener = (OnAssessment1Listener) context;
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

    public interface OnAssessment1Listener {
        void onSaveClicked(String decision,
                           String maturity,
                           String npbs,
                           ArrayList<String> zonaPellucida,
                           ArrayList<String> pvs,
                           ArrayList<String> membrane,
                           ArrayList<String> cytoplasm,
                           String dirBody,
                           String note);

        void onFragmentCreated();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSaveListener.onFragmentCreated();
    }
}
