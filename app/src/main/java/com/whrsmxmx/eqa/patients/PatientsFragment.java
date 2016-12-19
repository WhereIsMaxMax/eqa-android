package com.whrsmxmx.eqa.patients;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.whrsmxmx.eqa.add_patient.PersonActivity;
import com.whrsmxmx.eqa.data.AppCompatOrmActivity;
import com.whrsmxmx.eqa.data.Patient;
import com.whrsmxmx.eqa.data.database.DatabaseHelper;
import com.whrsmxmx.eqa.utils.DefaultDateFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class PatientsFragment extends Fragment implements PatientsContract.View {

//    Constants
    private static final String TAG = PatientsFragment.class.getName();
    final static int ADD_PERSON_REQUEST = 111;
    final static String PERSON_ID = "PERSON_ID";

//    Listeners
    private PatientsContract.UserActions mActionsListener;

//    Views
    private android.support.v7.widget.RecyclerView mRecyclerView;


    private RecyclerAdapter mRecyclerAdapter;

    public PatientsFragment() {
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

    public static PatientsFragment newInstance() {
        return new PatientsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionsListener = new PatientsPresenter(getPatientsFromDatabase(), this);
    }

    public List<Patient> getPatientsFromDatabase() {
        RuntimeExceptionDao<Patient, String> patientDao =
                ((AppCompatOrmActivity<DatabaseHelper>)getActivity()).getHelper().getSimpleDataDao();
        return patientDao.queryForAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        bind(v);
        init(v);

        return v;
    }

    private void bind(View v) {
                mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
    }

    private void init(View v) {
        mRecyclerAdapter = new RecyclerAdapter(new ArrayList<Patient>(0));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadPatients();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void showAddPatient() {
        startActivityForResult(new Intent(getActivity(), PersonActivity.class), ADD_PERSON_REQUEST);
    }

    @Override
    public void showPatientsList(ArrayList<Patient> patients) {

    }

    @Override
    public void updatePatientsList() {
        mRecyclerAdapter.update((ArrayList<Patient>) getPatientsFromDatabase());
    }

    @Override
    public void showPatientCard(String patientId) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "OnActivityResult");
        if (requestCode == ADD_PERSON_REQUEST){
            Log.i(TAG, "ADD_PERSON_REQUEST");
            if (resultCode == Activity.RESULT_OK){
                mActionsListener.loadPatients();
                Log.i(TAG, "RESULT_OK");
            }
        }
    }

    private static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        public List<Patient> mPatients;

        RecyclerAdapter(List<Patient> patients){
            mPatients = patients;
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
//        todo: add type colour
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

        public class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener, View.OnLongClickListener{

            private TextView surnameView;
            private TextView idView;
            private RecyclerViewListener recyclerViewListener;

            ViewHolder(LinearLayout itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
                surnameView = (TextView) itemView.findViewById(R.id.surname);
                idView = (TextView) itemView.findViewById(R.id.id);
            }


            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Patient patient = getItem(position);
                recyclerViewListener.onClick(patient);
            }

            @Override
            public boolean onLongClick(View view) {
                int position = getAdapterPosition();
                Patient patient = getItem(position);
                recyclerViewListener.onLongClick(patient);
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
                    .setMessage(getResources().getString(R.string.created)+ DefaultDateFormatter.format(patient.getCreationDate()))
                    .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mActionsListener.updatePatient(patient.getName());
                        }
                    })
                    .setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mActionsListener.deletePatient(patient.getName());
                        }
                    })
                    .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
    };
}
