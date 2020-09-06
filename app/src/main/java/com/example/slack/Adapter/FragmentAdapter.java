package com.example.slack.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.slack.Fragments.Fragment_Mid;
import com.example.slack.Fragments.LastFragment;
import com.example.slack.Fragments.MidFragment;
import com.example.slack.Fragments.SettingFragment;


public class FragmentAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs1;
    FragmentActivity activity ;

    public FragmentAdapter(FragmentManager fm, int NumOfTabs1, FragmentActivity activity) {
        super(fm);
        this.mNumOfTabs1 = NumOfTabs1;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SettingFragment tab0 = new SettingFragment();
                return tab0;
            case 1:
//                MidFragment tab1 = new MidFragment(activity);
                Fragment_Mid tab1 = new Fragment_Mid();
                return tab1;
            case 2:
                LastFragment tab2 = new LastFragment();
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