package com.whrsmxmx.eqa.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.opencsv.CSVWriter;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.data.database.model.Drop;
import com.whrsmxmx.eqa.data.database.model.Patient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Max on 17.01.2017.
 */

public class ExportDatabaseCSVTask extends AsyncTask<String, String, String> {
    private Context mContext;
    private ProgressDialog mDialog;
    private Patient mPatient;
    private String mPatientName;

    public ExportDatabaseCSVTask(Patient patient, Context context){
        mContext = context;
        mDialog = new ProgressDialog(context);
        mPatientName = patient.getName();
        mPatient = patient;
    }

    @Override
    protected void onPreExecute() {
        this.mDialog.setMessage("Exporting database...");
        this.mDialog.show();
    }

    protected String doInBackground(final String... args){
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, mPatientName +"_excel.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file), ';');

            //Headers
            String columnNames[] = mContext.getResources().getStringArray(R.array.export);
            csvWrite.writeNext(columnNames);

            ArrayList<Drop> drops = new ArrayList<>(mPatient.getDrops());

            for(Drop d : drops){
                ArrayList<String> dataArrayList = new ArrayList<>();
                dataArrayList.add(String.valueOf(d.getNumber()));
                if(d.getDay0Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay0Assessment().toStringArray()));
                if(d.getDay1Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay1Assessment().toStringArray()));
                if(d.getDay3Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay3Assessment().toStringArray()));
                if(d.getDay4Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay4Assessment().toStringArray()));
                if(d.getDay5Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay5Assessment().toStringArray()));
                if(d.getDay6Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay6Assessment().toStringArray()));
                if(d.getDay7Assessment()!=null)
                    dataArrayList.addAll(Arrays.asList(d.getDay7Assessment().toStringArray()));

                csvWrite.writeNext(dataArrayList.toArray(new String[dataArrayList.size()]));
            }

            csvWrite.close();
            return "";
        }
        catch (IOException e){
            Log.e("ExportDatabase", e.getMessage(), e);
            return "";
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(final String success) {

        if (this.mDialog.isShowing()){
            this.mDialog.dismiss();
        }
        if (success.isEmpty()){
            Toast.makeText(mContext, "Export successful!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Export failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
