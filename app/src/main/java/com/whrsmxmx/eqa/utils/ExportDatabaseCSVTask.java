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
import com.whrsmxmx.eqa.patients_list.PatientsListActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Max on 17.01.2017.
 */

public class ExportDatabaseCSVTask extends AsyncTask<String, String, String> {
    private Context mContext;
    private ProgressDialog mDialog;
    private String[] dataArray;

    public ExportDatabaseCSVTask(Context context){
        mContext = context;
        mDialog = new ProgressDialog(context);
        List<Patient> patientList = ((PatientsListActivity)context).getHelper().getSimpleDataDao().queryForAll();
        ArrayList<String> data = new ArrayList<>();
        data.add(patientList.get(0).getName());
        Drop drop = (Drop) patientList.get(0).getDrops().toArray()[0];
        data.addAll(Arrays.asList(drop.getDay0Assessment().toStringArray()));
        dataArray = data.toArray(new String[data.size()]);
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

        File file = new File(exportDir, "ExcelFile.csv");
        try {

            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file), ';');

            //Headers
            String arrStr1[] = mContext.getResources().getStringArray(R.array.export);
            csvWrite.writeNext(arrStr1);

            csvWrite.writeNext(dataArray);

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
