package com.example.slackui.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.slackui.Fragments.FirstFragment;
import com.example.slackui.Fragments.SecondFragment;
import com.example.slackui.Fragments.ThirdFragment;
import com.example.slackui.MainActivity;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs1;
    MainActivity activity ;

    public FragmentAdapter(FragmentManager fm, int NumOfTabs1, MainActivity activity) {
        super(fm);
        this.mNumOfTabs1 = NumOfTabs1;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FirstFragment tab0 = new FirstFragment();
                return tab0;
            case 1:
                SecondFragment tab1 = new SecondFragment(activity);
                return tab1;
            case 2:
                ThirdFragment tab2 = new ThirdFragment();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs1;
    }
}