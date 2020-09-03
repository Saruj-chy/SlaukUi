package com.example.slackui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.slackui.MainActivity;
import com.example.slackui.R;


public class SecondFragment extends Fragment {

    MainActivity activity ;

    public SecondFragment() {
        // Required empty public constructor
    }
    public SecondFragment(MainActivity activity) {
        this.activity = activity ;
        // Required empty public constructor
    }


    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);

        Button btn = view.findViewById(R.id.buttonAdd) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setSelect(0);
            }
        });

        return  view ;
    }
}