package com.example.slackui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.example.slackui.Adapter.FragmentAdapter;
import com.example.slackui.Adapter.FragmentAdapter2;
import com.example.slackui.Adapter.SettingsRecyclerAdapter;
import com.example.slackui.Class.SettingModel;
import com.example.slackui.Class.Student;
import com.example.slackui.Fragments.FirstFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<SettingModel> settingList = new ArrayList<>();
    ViewPager viewPager ;

//
//    ViewPager2 viewPager2 ;
//    private FragmentAdapter2 pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 1"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 2"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Fragment 3"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);




        viewPager = (ViewPager) findViewById(R.id.pager);

        final FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout2.getTabCount(), MainActivity.this );
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

//        final FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.container_fragment, FirstFragment
//                .newInstance()).commit();




    }

    public void setSelect(int position){
        viewPager.setCurrentItem(position);
        viewPager.setPadding(0, 0, 0, 0);
        viewPager.setClipToPadding(false);

    }




}
//
//  viewPager2 = (ViewPager2) findViewById(R.id.pager2);
//          pagerAdapter = new FragmentAdapter2(this, tabLayout2.getTabCount(), MainActivity.this);
//          viewPager2.setAdapter(pagerAdapter);
//          viewPager2.setCurrentItem(1);
//
//
//          viewPager2.setPadding(0, 0, 100, 0);
//          viewPager2.setClipToPadding(false);
//
//          viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//@Override
//public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                if(position==0){
//        viewPager2.setPadding(0, 0, 100, 0);
//        viewPager2.setClipToPadding(false);
//                }
//                else if(position==1){
//                    viewPager2.setPadding(0, 0, 0, 0);
//                    viewPager2.setClipToPadding(false);
//                }
//                else if(position==2){
//                    viewPager2.setPadding(0, 0, 0, 0);
//                    viewPager2.setClipToPadding(false);
//                }
//        }
//
//@Override
//public void onPageSelected(int position) {
//        super.onPageSelected(position);
//        }
//
//@Override
//public void onPageScrollStateChanged(int state) {
//        super.onPageScrollStateChanged(state);
//        }
//        });
//
//
