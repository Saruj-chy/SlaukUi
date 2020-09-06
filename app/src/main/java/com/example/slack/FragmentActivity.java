package com.example.slack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.slack.Adapter.FragmentAdapter;
import com.example.slack.Model.SettingModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {


    List<SettingModel> settingList = new ArrayList<>();
    ViewPager viewPager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_fragment);

        final TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 1"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 2"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 3"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);




        viewPager = (ViewPager) findViewById(R.id.pager);

        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout2.getTabCount(), FragmentActivity.this );
        viewPager.setAdapter(adapter);


        viewPager.setCurrentItem(1);




        //-extra
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));// ------  page er sathe tab change korte


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    viewPager.setPadding(0, 0, 100, 0);
                    viewPager.setClipToPadding(false);
                }
                else if(position==1){
                    viewPager.setPadding(0, 0, 0, 0);
                    viewPager.setClipToPadding(false);
                }
                else if(position==2){
                    viewPager.setPadding(0, 0, 0, 0);
                    viewPager.setClipToPadding(false);
                }

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("tab", "onPageSelected: "+position ) ;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.e("tab", "onPageScrollStateChanged: "+ state ) ;
            }
        });


        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelect(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public void setSelect(int position){
        viewPager.setCurrentItem(position);
        viewPager.setPadding(0, 0, 0, 0);
        viewPager.setClipToPadding(false);

    }

}