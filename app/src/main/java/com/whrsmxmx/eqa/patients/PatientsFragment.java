package com.whrsmxmx.eqa.patients;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whrsmxmx.eqa.R;
import com.whrsmxmx.eqa.add_patient.PersonActivity;
import com.whrsmxmx.eqa.data.Patient;

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
        mRecyclerView.addOnItemTouchListener();
    }

    RecyclerItemClickListener.OnItemClickListener itemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mActionsListener.openPatientCard(mPatients.get(position));
        }

        @Override
        public void onItemLongClick(View view, int position) {
            mActionsListener.showChoosePatientActionDialog();
        }
    };

//    RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener(getActivity(), mRecyclerView,
//                                  recyclerItemClickListener) {
//        @Override
//        public void onItemClick(View view, int position) {
//            Intent i = new Intent(getActivity(), AssessmentActivity.class);
//            i.putExtra(PERSON_ID, position);
//            startActivity(i);
//        }
//
//        @Override
//        public void onItemLongClick(View view, int position) {
//            Toast.makeText(getActivity(), "Longclick"+position, Toast.LENGTH_LONG).show();
//        }
//    });

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
    public void showPatientCard(String patientId) {

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i(TAG, "OnActivityResult");
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_PERSON_REQUEST){
//            Log.i(TAG, "ADD_PERSON_REQUEST");
//            if (resultCode == RESULT_OK){
//                mPatients = patientDao.queryForAll();
//                Log.i(TAG, "RESULT_OK "+ mPatients.toString());
//                mRecyclerAdapter.update(mPatients);
//            }
//        }
//    }

    private static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

        private List<Patient> mPatients;

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
            holder.surnameView.setText(String.valueOf(mPatients.get(position).getSurname()));
//        todo: add type colour
        }

        @Override
        public int getItemCount() {
            return mPatients.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout linearLayout;
            TextView surnameView;
            TextView idView;
            ViewHolder(LinearLayout itemView) {
                super(itemView);
                linearLayout = itemView;
                surnameView = (TextView) itemView.findViewById(R.id.surname);
                idView = (TextView) itemView.findViewById(R.id.id);
            }
        }

        void update(ArrayList<Patient> data){
            mPatients.clear();
            mPatients.addAll(data);
            notifyDataSetChanged();
        }
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

        interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }

        private com.whrsmxmx.eqa.patients.PatientsFragment.RecyclerItemClickListener.OnItemClickListener mListener;

        private GestureDetector mGestureDetector;

        RecyclerItemClickListener(Context context, final RecyclerView recyclerView,
                                  com.whrsmxmx.eqa.patients.PatientsFragment.RecyclerItemClickListener.OnItemClickListener listener) {
            mListener = listener;

            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (childView != null && mListener != null) {
                        mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());

            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
