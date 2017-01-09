package com.whrsmxmx.eqa.patients_list;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.add_patient.PatientActivity;
import com.whrsmxmx.eqa.assesment.AssessmentActivity;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.data.database.Model.Patient;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.utils.DefaultDateFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PatientsListFragment extends Fragment implements PatientsListContract.View {

//    Constants
    private static final String TAG = PatientsListFragment.class.getName();
    final static int PERSON_REQUEST = 111;

//    Listeners
    private PatientsListContract.UserActionsListener mActionsListener;

//    Views
    private android.support.v7.widget.RecyclerView mRecyclerView;

//    Adapters
    private RecyclerAdapter mRecyclerAdapter;

//    Primitives

    public PatientsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionsListener.addNewPatient();
            }
        });
    }

    public static PatientsListFragment newInstance() {
        return new PatientsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionsListener = new PatientsListPresenter(getPatientsFromDatabase(), this);
    }

    public List<Patient> getPatientsFromDatabase() {
        RuntimeExceptionDao<Patient, String> patientDao =
                ((AppCompatOrmActivity<DatabaseHelper>)getActivity()).getHelper().getSimpleDataDao();
        return patientDao.queryForAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        bind(v);
        init();

        return v;
    }

    private void bind(View v) {
                mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
    }

    private void init() {
        mRecyclerAdapter = new RecyclerAdapter(new ArrayList<Patient>(0), recyclerViewListener);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mActionsListener.loadPatients();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showAddPatient() {
        Log.i(TAG, "showAddPatient");
        startActivityForResult(new Intent(getActivity(), PatientActivity.class), PERSON_REQUEST);
    }

    @Override
    public void updatePatientsList() {
        Log.i(TAG, "updatePatientsList");
        mRecyclerAdapter.update((ArrayList<Patient>) getPatientsFromDatabase());
    }

    @Override
    public void showPatientCard(String patientId) {
        Log.i(TAG, "showPatientCard");
        Intent intent = new Intent(getActivity(), AssessmentActivity.class);
        intent.putExtra(Patient.PERSON_ID, patientId);
        startActivity(intent);
    }

    @Override
    public void openUpdatePatient(String patientId) {
        Log.i(TAG, "openUpdatePatient");
        Intent intent = new Intent(getActivity(), PatientActivity.class);
        intent.putExtra(Patient.PERSON_ID, patientId);
        startActivityForResult(intent, PERSON_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "OnActivityResult");
        if (requestCode == PERSON_REQUEST){
            Log.i(TAG, "PERSON_REQUEST");
            if (resultCode == Activity.RESULT_OK){
                mActionsListener.loadPatients();
                Log.i(TAG, "RESULT_OK");
            }
        }
    }

    private static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        List<Patient> mPatients;
        RecyclerViewListener mRecyclerViewListener;
        int[]colors = new int[]{Color.RED, Color.BLUE, Color.GREEN};

        RecyclerAdapter(List<Patient> patients, RecyclerViewListener recyclerViewListener){
            mPatients = patients;
            mRecyclerViewListener = recyclerViewListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_person_item, parent, false);
            return new RecyclerAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.idView.setText(String.valueOf(position+1));
            holder.surnameView.setText(String.valueOf(mPatients.get(position).getName()));
            holder.procedure.setText(String.valueOf(mPatients.get(position).getProcedure()));
//            holder.procedure.setBackgroundColor(colors[mPatients.get(position).getProcedure()]);
        }

        public Patient getItem(int position){
            return mPatients.get(position);
        }

        @Override
        public int getItemCount() {
            return mPatients.size();
        }

        public interface RecyclerViewListener{
            void onClick(Patient patient);

            void onLongClick(Patient patient);
        }

        class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener, View.OnLongClickListener{

            private TextView surnameView;
            private TextView idView;
            private TextView procedure;

            ViewHolder(LinearLayout itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
                surnameView = (TextView) itemView.findViewById(R.id.surname);
                idView = (TextView) itemView.findViewById(R.id.id);
                procedure = (TextView) itemView.findViewById(R.id.procedure);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Patient patient = getItem(position);
                mRecyclerViewListener.onClick(patient);
            }

            @Override
            public boolean onLongClick(View view) {
                int position = getAdapterPosition();
                Patient patient = getItem(position);
                mRecyclerViewListener.onLongClick(patient);
                return false;
            }

        }

        void update(ArrayList<Patient> data){
            mPatients.clear();
            mPatients.addAll(data);
            notifyDataSetChanged();
        }
    }

    RecyclerAdapter.RecyclerViewListener recyclerViewListener = new RecyclerAdapter.RecyclerViewListener() {
        @Override
        public void onClick(Patient patient) {
            mActionsListener.openPatientCard(patient.getName());
        }

        @Override
        public void onLongClick(final Patient patient) {
            final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(patient.getName())
                    .setMessage(getResources().getString(R.string.created)+" "+ DefaultDateFormatter.format(patient.getCreationDate()))
                    .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setNeutralButton(R.string.edit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mActionsListener.updatePatient(patient.getName());
                        }
                    })
                    .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mActionsListener.deletePatient(patient.getName());
                        }
                    })
                    .show();
        }
    };
}
