package com.whrsmxmx.eqa.assesment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whrsmxmx.eqa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Day2Fragment extends Fragment {


    public Day2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day2, container, false);
    }

}
//package com.whrsmxmx.eqa.assesment;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.whrsmxmx.eqa.R;
//import com.whrsmxmx.eqa.data.database.model.Day2Assessment;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class AssessmentView23 extends RelativeLayout implements AssessmentContract.Assessment2ViewInterface{
//
//    private LinearLayout propertiesContainer;
//    private TextView anomaliesTextView;
//    private Spinner fragmentationSpinner;
//    private Spinner blastomeresSpinner;
//    private Button saveButton;
//    private EditText notesEditText;
//    private CheckBox isDegenerateCheckBox;
//
//    private boolean [] propertiesArrayCheckedList;
//    private String [] propertiesArray;
//    private ArrayList <String> propertiesSelectedArray = new ArrayList<>();
//
//    private ArrayList<Integer> fragmentationArray;
//    private ArrayList<String> blastomeresArray;
////    private DropViewSavedListener mSaveListener;
//    private int mNumber;
//
//    public AssessmentView23(Context context) {
//        super(context);
//        init(context);
//    }
//
//    private void init(Context context) {
//        View v = LayoutInflater.from(context).inflate(R.layout.layout_assessment2and3, this);
//
//        propertiesArray = getResources().getStringArray(R.array.properties);
//        propertiesArrayCheckedList = new boolean[propertiesArray.length];
//        int[] frgmtA = getResources().getIntArray(R.array.fragmentation_percent);
//        fragmentationArray = new ArrayList<Integer>();
//        for(int frgmt : frgmtA){
//            fragmentationArray.add(frgmt);
//        }
//        blastomeresArray = new ArrayList<>(
//                Arrays.asList(getResources().getStringArray(R.array.blastomeres_number))
//        );
//
////        binding
//        propertiesContainer = (LinearLayout) this.findViewById(R.id.properties_container);
//        anomaliesTextView = (TextView) this.findViewById(R.id.properties_text_view);
//        fragmentationSpinner = (Spinner) this.findViewById(R.id.fragmentation_spinner);
//        blastomeresSpinner = (Spinner) this.findViewById(R.id.blastomeres_spinner);
//        saveButton = (Button) this.findViewById(R.id.save_button);
//        notesEditText = (EditText) this.findViewById(R.id.notes_edit_text);
//        isDegenerateCheckBox = (CheckBox) this.findViewById(R.id.is_degenerate_checkbox);
//
////        initialisation
//        isDegenerateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                blastomeresSpinner.setEnabled(!isChecked);
//                fragmentationSpinner.setEnabled(!isChecked);
//                notesEditText.setEnabled(!isChecked);
//
//            }
//        });
//        fragmentationSpinner.setAdapter(new ArrayAdapter<Integer>(getContext(),
//                android.R.layout.simple_spinner_dropdown_item,
//                fragmentationArray));
//        blastomeresSpinner.setAdapter(new ArrayAdapter<>(getContext(),
//                android.R.layout.simple_spinner_dropdown_item,
//                getResources().getStringArray(R.array.blastomeres_number)));
//
//        propertiesContainer.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isDegenerateCheckBox.isChecked()) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//                    builder.setMultiChoiceItems(propertiesArray, propertiesArrayCheckedList,
//                            new DialogInterface.OnMultiChoiceClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                                    propertiesArrayCheckedList[which] = isChecked;
//                                    if(isChecked)
//                                        propertiesSelectedArray.add(propertiesArray[which]);
//                                    else
//                                        propertiesSelectedArray.remove(propertiesArray[which]);
//                                }
//                            });
//                    builder.setTitle(R.string.properties);
//                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String anomaliesText = "";
//                            for (String s : propertiesSelectedArray){
//                                anomaliesText += s +" ";
//                            }
//                            anomaliesTextView.setText(anomaliesText);
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//
//                    dialog.show();
//                }
//            }
//        });
//        saveButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                mSaveListener.onDropSave();
//            }
////        });
////    }
//
//    public void setDropInfo(int number, boolean isDegenerate, String blastomeres,
//                            int fragmentation, ArrayList<String> properties, String notes){
//        mNumber = number;
//        isDegenerateCheckBox.setChecked(isDegenerate);
//        blastomeresSpinner.setSelection(blastomeresArray.indexOf(blastomeres));
//        fragmentationSpinner.setSelection(fragmentationArray.indexOf(fragmentation));
//        propertiesSelectedArray = properties;
//        String textValue = "";
//        for(int i = 0; i < properties.size(); i++){
//            propertiesArrayCheckedList[Arrays.asList(propertiesArray).indexOf(properties.get(i))] = true;
//            textValue = textValue + propertiesArray[i] + " ";
//        }
//        anomaliesTextView.setText(textValue);
//        notesEditText.setText(notes);
//    }
//
//    public void clear(){
//        isDegenerateCheckBox.setChecked(false);
//        blastomeresSpinner.setSelection(0);
//        fragmentationSpinner.setSelection(0);
//        anomaliesTextView.setText("");
//        notesEditText.setText("");
//    }
//
//    public Day2Assessment saveAssessment(){
//        return new Day2Assessment(isDegenerateCheckBox.isChecked(),
//                blastomeresArray.get(blastomeresSpinner.getSelectedItemPosition()),
//                fragmentationArray.get(fragmentationSpinner.getSelectedItemPosition()),
//                propertiesSelectedArray, notesEditText.getText().toString());
//    }
//
////    public void setOnSaveListener(DropViewSavedListener listener) {
////        mSaveListener = listener;
////    }
//
//    @Override
//    public Day2Assessment saveDay2Info() {
//        return null;
//    }
//}
