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
public class Day1Fragment extends Fragment {


    public Day1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day1, container, false);
    }

    public static Fragment newInstance() {
        return new Day1Fragment();
    }
}
