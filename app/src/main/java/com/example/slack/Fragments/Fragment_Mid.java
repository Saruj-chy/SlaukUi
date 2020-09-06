package com.example.slack.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slack.Adapter.RV_Adapter_main;
import com.example.slack.Constant.Constant;
import com.example.slack.Model.Main_Model;
import com.example.slack.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fragment_Mid extends Fragment {
    private static final String TAG = "Fragment_Mid";
    private ArrayList<Main_Model> myList = new ArrayList<>();

    private BottomNavigationView bottomNavigationView;
    private ImageView imageView_search;
    private RecyclerView recyclerView_main;

    private RV_Adapter_main rv_adapter_main ;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Called");



    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Called before View");
        final View view = inflater.inflate(R.layout.layout_frag_mid, container, false);
        Log.d(TAG, "onCreateView: Called after View");

        bottomNavigationView = view.findViewById(R.id.nav_bottom);
        imageView_search = view.findViewById(R.id.imgV_search);
        recyclerView_main = view.findViewById(R.id.rv_mid_frag_main);

        recyclerViewHandler();
        mJsonParsing();

        clickSearchView();
        clickBottomNav();

        return view;
    }

    private void recyclerViewHandler() {
        recyclerView_main.setHasFixedSize(true);
        recyclerView_main.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_adapter_main = new RV_Adapter_main(myList, getContext());
        recyclerView_main.setAdapter(rv_adapter_main);
    }

    private void mJsonParsing() {
        try {
            JSONObject object = new JSONObject(Constant.new_json);
            JSONArray jsonArray_data = object.getJSONObject("person").getJSONArray("data");
            myList.clear();
            for (int i = 0; i < jsonArray_data.length(); i++){
                JSONObject itemOf_jsonArray_data = jsonArray_data.getJSONObject(i);
                Main_Model main_model = new Main_Model();
                Field[] fields = main_model.getAllFields();

                for (int j = 0; j < fields.length; j++)
                {
                    String currenfieldName = fields[j].getName();
                    String jsonFieldValue = itemOf_jsonArray_data.has(currenfieldName) ? itemOf_jsonArray_data.getString(currenfieldName) : "";
                    fields[j].setAccessible(true);
                    fields[j].set(main_model, jsonFieldValue);
                }

            /*    if (myList.containsKey(main_model.getProjects()) && myList.get(main_model.getProjects()) != null){
                    myList.get(main_model.getProjects()).add(main_model);
                }else {
                    ArrayList<Main_Model> clientList = new ArrayList<>();
                    clientList.add(main_model);
                    myList.put(main_model.getProjects(), clientList);

                    keyOfMyList.add(main_model.getProjects());
                }*/

            myList.add(main_model);

            }
            Collections.sort(myList, new Comparator<Main_Model>() {
                @Override
                public int compare(Main_Model o1, Main_Model o2) {

                    if (o1.getGroup().equalsIgnoreCase("Projects"))
                    {
                        return -1;
                    }else if (o1.getGroup().equalsIgnoreCase("Clients"))
                    {
                        return 1;
                    }

                    return 0;
                }
            });
            rv_adapter_main.notifyDataSetChanged();
            Log.d(TAG, "mJsonParsing: "+myList.size());


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "mJsonParsing: set Field value :: " + e.toString());
            e.printStackTrace();
        }
    }




    private void clickBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homeItem:
                        Toast.makeText(getActivity(), "Clicked Home", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.notifyItem:
                        Toast.makeText(getActivity(), "Clicked Notify", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.infoItem:
                        Toast.makeText(getActivity(), "Clicked Info", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.settingItem:
                        Toast.makeText(getActivity(), "Clicked Settings", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.locationItem:
                        Toast.makeText(getActivity(), "Clicked Location", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

    }

    private void clickSearchView() {
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "searchView", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
