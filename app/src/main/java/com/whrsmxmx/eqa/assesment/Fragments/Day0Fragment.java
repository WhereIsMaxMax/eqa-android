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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.assesment.DecisionView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.whrsmxmx.eqa.utils.StringsTricks.removeLastCharIfNotEmpty;

public class Day0Fragment extends Fragment implements DecisionView.DecisionInterface{

    //    views;
    private Spinner mMaturitySpinner;
    private LinearLayout mZonaContainer;
    private TextView mZonaTextView;
    private LinearLayout mPvsContainer;
    private TextView mPvsTextView;
    private LinearLayout mMembraneContainer;
    private TextView mMembraneTextView;
    private LinearLayout mCytoplasmContainer;
    private TextView mCytoplasmTextView;
    private Spinner mPbiSpinner;
    private Button mSaveButton;
    private EditText mNotesEditText;
//    private CheckBox isDegenerateCheckBox;
    private DecisionView mDecisionView;

//    data containers;
    private ArrayList<String> mMaturityArray;

    private boolean [] mZonaArrayCheckedList;
    private String [] mZonaArray;

    private boolean [] mPvsArrayCheckedList;
    private String [] mPvsArray;

    private boolean [] mMembraneArrayCheckedList;
    private String [] mMembraneArray;

    private boolean [] mCytoplasmArrayCheckedList;
    private String [] mCytoplasmArray;

    private ArrayList<String> mPbiArray;
    private OnAssessment0Listener mSaveListener;

//    listeners

     public static Day0Fragment newInstance() {
        return new Day0Fragment();
    }

    public Day0Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day0, container, false);

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

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mNotesEditText = (EditText) v.findViewById(R.id.notes_edit_text);
//        isDegenerateCheckBox = (CheckBox) v.findViewById(R.id.is_degenerate_checkbox);
        mDecisionView = (DecisionView) v.findViewById(R.id.decision_view);
    }

    private void init() {
        mMaturityArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.maturity_day_0_string_array))
        );

        mPbiArray = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.pbi_string_array))
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
//                mNotesEditText.setEnabled(!isChecked);
//
//            }
//        });

        mMaturitySpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mMaturityArray));
        mPbiSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mPbiArray));

        mZonaContainer.setOnClickListener(createClickListener(mZonaArray,
                mZonaArrayCheckedList, getResources().getString(R.string.zona_pellucida), mZonaTextView));

        mPvsContainer.setOnClickListener(createClickListener(mPvsArray, mPvsArrayCheckedList,
                getResources().getString(R.string.pvs), mPvsTextView));

        mCytoplasmContainer.setOnClickListener(createClickListener(mCytoplasmArray,
                mCytoplasmArrayCheckedList, getResources().getString(R.string.cytoplasm), mCytoplasmTextView));

        mMembraneContainer.setOnClickListener(createClickListener(mMembraneArray,
                mMembraneArrayCheckedList, getResources().getString(R.string.membrane), mMembraneTextView));

        mSaveButton.setOnClickListener(new View.OnClickListener() {
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
                        zonaSelectedArray,
                        pvsSelectedArray,
                        membraneSelectedArray,
                        cytoplasmSelectedArray,
                        mPbiArray.get(mPbiSpinner.getSelectedItemPosition()),
                        mNotesEditText.getText().toString());
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

    public void setInfo(String decision, String maturity, ArrayList<String> zonaPellucida,
                        ArrayList<String> pvs, ArrayList<String> membrane,
                        ArrayList<String> cytoplasm, String pbi, String note) {

//        isDegenerateCheckBox.setChecked(isDegenerate);

        mDecisionView.setDecisionSelection(decision);

        mMaturitySpinner.setSelection(mMaturityArray.indexOf(maturity));
        mPbiSpinner.setSelection(mPbiArray.indexOf(pbi));

        String zonaTextValue = "";
        for(String s : zonaPellucida){
            int index = Arrays.asList(mZonaArray).indexOf(s);
            mZonaArrayCheckedList[index] = true;
            zonaTextValue = zonaTextValue + mZonaArray[index] + "\n";
        }
        mZonaTextView.setText(removeLastCharIfNotEmpty(zonaTextValue));

        String pvSTextValue = "";
        for(String s : pvs){
            int index = Arrays.asList(mPvsArray).indexOf(s);
            mPvsArrayCheckedList[index] = true;
            pvSTextValue = pvSTextValue + mPvsArray[index] + "\n";
        }
        mPvsTextView.setText(removeLastCharIfNotEmpty(pvSTextValue));

        String membraneTextValue = "";
        for(String s : membrane){
            int index = Arrays.asList(mMembraneArray).indexOf(s);
            mMembraneArrayCheckedList[index] = true;
            membraneTextValue = membraneTextValue + mMembraneArray[index] + "\n";
        }
        mMembraneTextView.setText(removeLastCharIfNotEmpty(membraneTextValue));

        String cytoplasmTextValue = "";
        for(String s : cytoplasm){
            int index = Arrays.asList(mCytoplasmArray).indexOf(s);
            mCytoplasmArrayCheckedList[index] = true;
            cytoplasmTextValue = cytoplasmTextValue + mCytoplasmArray[index] + "\n";
        }
        mCytoplasmTextView.setText(removeLastCharIfNotEmpty(cytoplasmTextValue));

        mNotesEditText.setText(note);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAssessment0Listener) {
            mSaveListener = (OnAssessment0Listener) context;
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

    public interface OnAssessment0Listener {
        void onSaveClicked(String decision,
                           String maturity,
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
