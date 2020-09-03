package com.example.slackui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.slackui.R;


public class ThirdFragment extends Fragment {


    public ThirdFragment() {
        // Required empty public constructor
    }


    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}