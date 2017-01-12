package com.whrsmxmx.eqa.assesment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.model.Assessment;
import com.whrsmxmx.eqa.data.database.model.Day0Assessment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Max on 11.01.2017.
 */

public class AssessmentView01 extends RelativeLayout implements AssessmentViewInterface {

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
    private Button saveButton;
    private EditText notesEditText;
    private CheckBox isDegenerateCheckBox;

    private ArrayList<String> mMaturityArray;

    private boolean [] mZonaArrayCheckedList;
    private String [] mZonaArray;
    private ArrayList<String> mZonaSelectedArray = new ArrayList<>();

    private boolean [] mPvsArrayCheckedList;
    private String [] mPvsArray;
    private ArrayList<String> mPvsSelectedArray = new ArrayList<>();

    private boolean [] mMembraneArrayCheckedList;
    private String [] mMembraneArray;
    private ArrayList<String> mMembraneSelectedArray = new ArrayList<>();

    private boolean [] mCytoplasmArrayCheckedList;
    private String [] mCytoplasmArray;
    private ArrayList<String> mCytoplasmSelectedArray = new ArrayList<>();

    private ArrayList<String> mPbiArray;
    private DropViewSavedListener mSaveListener;
    private int mNumber;


    public AssessmentView01(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_assessment0and1, this);

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

//        binding
        mZonaContainer = (LinearLayout) this.findViewById(R.id.multiselect_container0);
        mZonaTextView = (TextView) this.findViewById(R.id.multiselect0_text_view);

        mPvsContainer = (LinearLayout) this.findViewById(R.id.multiselect_container1);
        mPvsTextView = (TextView) this.findViewById(R.id.multiselect1_text_view);

        mMembraneContainer = (LinearLayout) this.findViewById(R.id.multiselect_container2);
        mMembraneTextView = (TextView) this.findViewById(R.id.multiselect2_text_view);

        mCytoplasmContainer = (LinearLayout) this.findViewById(R.id.multiselect_container3);
        mCytoplasmTextView = (TextView) this.findViewById(R.id.multiselect3_text_view);

        mMaturitySpinner = (Spinner) this.findViewById(R.id.spinner0);
        mPbiSpinner = (Spinner) this.findViewById(R.id.spinner1);

        saveButton = (Button) this.findViewById(R.id.save_button);
        notesEditText = (EditText) this.findViewById(R.id.notes_edit_text);
        isDegenerateCheckBox = (CheckBox) this.findViewById(R.id.is_degenerate_checkbox);

//        initialisation
        isDegenerateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMaturitySpinner.setEnabled(!isChecked);
                mPbiSpinner.setEnabled(!isChecked);
                notesEditText.setEnabled(!isChecked);

            }
        });
        mMaturitySpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mMaturityArray));
        mPbiSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mPbiArray));

        mZonaContainer.setOnClickListener(createClickListener(mZonaArray, mZonaSelectedArray,
                mZonaArrayCheckedList, getResources().getString(R.string.zona_pellucida), mZonaTextView));

        mPvsContainer.setOnClickListener(createClickListener(mPvsArray, mPvsSelectedArray,
                mPvsArrayCheckedList, getResources().getString(R.string.pvs), mPvsTextView));

        mCytoplasmContainer.setOnClickListener(createClickListener(mCytoplasmArray, mCytoplasmSelectedArray,
                mCytoplasmArrayCheckedList, getResources().getString(R.string.cytoplasm), mCytoplasmTextView));

        mMembraneContainer.setOnClickListener(createClickListener(mMembraneArray, mMembraneSelectedArray,
                mMembraneArrayCheckedList, getResources().getString(R.string.membrane), mMembraneTextView));

//        mZonaContainer.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isDegenerateCheckBox.isChecked()) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//                    builder.setMultiChoiceItems(mZonaArray, mZonaArrayCheckedList,
//                            new DialogInterface.OnMultiChoiceClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                                    mZonaArrayCheckedList[which] = isChecked;
//                                    if(isChecked)
//                                        mZonaSelectedArray.add(mZonaArray[which]);
//                                    else
//                                        mZonaSelectedArray.remove(mZonaArray[which]);
//                                }
//                            });
//                    builder.setTitle(R.string.zona_pellucida);
//                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String anomaliesText = "";
//                            for (String s : mZonaSelectedArray){
//                                anomaliesText += s +" ";
//                            }
//                            mZonaTextView.setText(anomaliesText);
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//
//                    dialog.show();
//                }
//            }
//        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveListener.onDropSave();
            }
        });
    }



    public void setOnSaveListener(DropViewSavedListener listener) {
        mSaveListener = listener;
    }

    public void setInfo(boolean isDegenerate, String maturity, ArrayList<String> zonaPellucida,
                        ArrayList<String> pvs, ArrayList<String> membrane,
                        ArrayList<String> cytoplasm, String pbi, String note) {

        isDegenerateCheckBox.setChecked(isDegenerate);

        mMaturitySpinner.setSelection(mMaturityArray.indexOf(maturity));
        mPbiSpinner.setSelection(mPbiArray.indexOf(pbi));

        mZonaSelectedArray = zonaPellucida;
        String zonaTextValue = "";
        for(int i = 0; i < zonaPellucida.size(); i++){
            mZonaArrayCheckedList[Arrays.asList(mZonaArray).indexOf(zonaPellucida.get(i))] = true;
            zonaTextValue = zonaTextValue + mZonaArray[i] + " ";
        }
        mZonaTextView.setText(zonaTextValue);

        mPvsSelectedArray = pvs;
        String pvSTextValue = "";
        for(int i = 0; i < pvs.size(); i++){
            mPvsArrayCheckedList[Arrays.asList(mPvsArray).indexOf(pvs.get(i))] = true;
            pvSTextValue = pvSTextValue + mPvsArray[i] + " ";
        }
        mPvsTextView.setText(pvSTextValue);

        mMembraneSelectedArray = membrane;
        String membraneTextValue = "";
        for(int i = 0; i < membrane.size(); i++){
            mMembraneArrayCheckedList[Arrays.asList(mMembraneArray).indexOf(membrane.get(i))] = true;
            membraneTextValue = membraneTextValue + mMembraneArray[i] + " ";
        }
        mMembraneTextView.setText(membraneTextValue);

        mCytoplasmSelectedArray = cytoplasm;
        String cytoplasmTextValue = "";
        for(int i = 0; i < cytoplasm.size(); i++){
            mCytoplasmArrayCheckedList[Arrays.asList(mCytoplasmArray).indexOf(cytoplasm.get(i))] = true;
            cytoplasmTextValue = cytoplasmTextValue + mCytoplasmArray[i] + "";
        }
        mCytoplasmTextView.setText(cytoplasmTextValue);

        notesEditText.setText(note);
    }

    private OnClickListener createClickListener(final String[] array, final ArrayList<String> selectedArray,
                                                final boolean [] checkedArray, final String title, final TextView textView){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDegenerateCheckBox.isChecked()) {

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
                                text += s +" ";
                            }
                            textView.setText(text);
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();

                    dialog.show();
                }
            }
        };
    }

    @Override
    public Assessment saveInfo() {
        return new Day0Assessment(isDegenerateCheckBox.isChecked(),
                mMaturityArray.get(mMaturitySpinner.getSelectedItemPosition()),
                mZonaSelectedArray,
                mPvsSelectedArray,
                mMembraneSelectedArray,
                mCytoplasmSelectedArray,
                mPbiArray.get(mPbiSpinner.getSelectedItemPosition()),
                notesEditText.getText().toString());
    }
}
