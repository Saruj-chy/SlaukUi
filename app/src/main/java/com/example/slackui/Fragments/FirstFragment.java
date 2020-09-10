package com.example.slackui.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.slackui.Adapter.SettingsRecyclerAdapter;
import com.example.slackui.Class.SettingModels;
import com.example.slackui.Constants.Config;
import com.example.slackui.Interfaces.SettingInterface;
import com.example.slackui.MainActivity;
import com.example.slackui.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class FirstFragment extends Fragment implements SettingInterface {

    RecyclerView firstFragment_RV;
    ImageButton addBtn ;
    TextView textName, textDomain;
    LinearLayout linearLayout01, linearLayout02, linearLayout03, linearLayout04, linearLayout05, linearLayout06 ;

    List<SettingModels> settingList = new ArrayList<>();
    private String settingName, settingDomain ;

    //=========   sharedprefarence
    SharedPreferences sharedPreferences,  sharedPreferences1 ;


    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance() {
        return new FirstFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("name", MODE_PRIVATE);
        sharedPreferences1 = this.getActivity().getSharedPreferences("domain", MODE_PRIVATE);

        InitializeFields(view) ;
        loadProducts();


        return view ;
    }
//    private void loadProducts1() {
//
//        try {
//            JSONObject object = new JSONObject(Constant.log_json);
//            Log.e("setting", "response: "+object );
//            JSONArray logArray = object.getJSONArray("log");
//            Log.e("setting", "logArray: "+logArray );
//
//
//            for(int i=0;i<logArray.length();i++){
//
//                JSONObject settingObject = logArray.getJSONObject(i);
//                Log.e("setting", "settingObject: "+settingObject );
//
//
//
////                SettingModel aSettingModel = new SettingModel() ;
////                Field[] fields =  aSettingModel.getAllFields() ;
////                Log.e("setting", "fields: "+fields.toString() );
////
////                for(int j=0; j<fields.length; j++ ){
////                    String fieldName = fields[j].getName() ;
////                    Log.e("setting", "fieldName: "+fieldName );
////                    String fieldValueInJson =settingObject.has(fieldName)? settingObject.getString(fieldName) : "" ;
////                    Log.e("setting", "fieldValueInJson: "+ fieldValueInJson ) ;
////                    fields[j].set(aSettingModel, fieldValueInJson);
////                }
////                settingList.add(aSettingModel);
//
//                settingList.add(new SettingModel(
//                        settingObject.getString("id"),
//                        settingObject.getString("name"),
//                        settingObject.getString("domain")
//                ));
//            }
////            SettingsRecyclerAdapter adapter = new SettingsRecyclerAdapter(getContext(), settingList, FirstFragment.this);
////            firstFragment_RV.setAdapter(adapter);
////            GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
////            firstFragment_RV.setLayoutManager(manager);
////            Log.e("setting", "data: "+ settingList.toString()) ;
//
//
//        } catch (JSONException  e) {
//            e.printStackTrace();
//            Log.e("setting", "error: "+ e.toString() ) ;
//
//        }
//
//    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SETTING_JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("serverResponse", "response: " + response );
                        try {
                            //converting the string to json array object
//                            JSONArray array = new JSONArray(response);

                            JSONObject object = new JSONObject(response);
//                            Log.e("serverResponse", "response: "+object );
                            JSONArray sectionArray = object.getJSONArray("section");
//                            Log.e("serverResponse", "sectionArray: "+sectionArray );



                            for(int i=0;i<sectionArray.length();i++){

                                JSONObject settingObject = sectionArray.getJSONObject(i);
//                                Log.e("serverResponse", "settingObject: "+settingObject );



                            SettingModels aSettingModel = new SettingModels() ;
                            Field[] fields =  aSettingModel.getAllFields() ;
//                            Log.e("serverResponse", "fields: "+fields.toString() );

                            for(int j=0; j<fields.length; j++ ){
                                String fieldName = fields[j].getName() ;
//                                Log.e("serverResponse", "fieldName: "+fieldName );
                                String fieldValueInJson =settingObject.has(fieldName)? settingObject.getString(fieldName) : "" ;
                                Log.e("serverResponse", "fieldValueInJson: "+ fieldValueInJson ) ;
                                fields[j].set(aSettingModel, fieldValueInJson) ;
                            }
                            settingList.add(aSettingModel);

//                                settingList.add(new SettingModels(
//                                        settingObject.getString("section_id"),
//                                        settingObject.getString("section_title"),
//                                        settingObject.getString("section_identifier")
//                                ));
                            }
                            Log.d("serverResponse", "prductList: "+settingList.size()) ;

                            SettingsRecyclerAdapter adapter = new SettingsRecyclerAdapter(getContext(), settingList, FirstFragment.this);
                            firstFragment_RV.setAdapter(adapter);
                            GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                            firstFragment_RV.setLayoutManager(manager);

//                            Toast.makeText(MainActivity.this, "successfull", Toast.LENGTH_LONG).show();
                        } catch (JSONException | IllegalAccessException e) {
                            e.printStackTrace();
                            Log.e("serverResponseError", "error: "+e.toString() );

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("serverResponse", "VolleyError: "+error.toString() );
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }



    private void InitializeFields(View view) {
        firstFragment_RV = view.findViewById(R.id.firstFrament_RV) ;
        addBtn = view.findViewById(R.id.add_btn) ;
        textName = view.findViewById(R.id.textView_Name) ;
        textDomain = view.findViewById(R.id.textView_Domain) ;

        linearLayout01 = view.findViewById(R.id.linearLayout01) ;
        linearLayout02 = view.findViewById(R.id.linearLayout02) ;
        linearLayout03 = view.findViewById(R.id.linearLayout03) ;
        linearLayout04 = view.findViewById(R.id.linearLayout04) ;
        linearLayout05 = view.findViewById(R.id.linearLayout05) ;
        linearLayout06 = view.findViewById(R.id.linearLayout06) ;
    }

    @Override
    public void onLogoName(String name, String domain) {
        textName.setText(name);
        textDomain.setText(domain);

        sharedSaved(sharedPreferences, "name", name) ;
        sharedSaved(sharedPreferences1, "domain", domain) ;
        Log.e("TAG", "name 1 : "+name+ "  domain: "+domain ) ;

    }

    public void Finish(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }, 100);
    }



    @Override
    public void onStart() {
        super.onStart();
        settingName = sharedPreferences.getString("name", "");
        settingDomain = sharedPreferences1.getString("domain", "");
        onLogoName(settingName, settingDomain);

    }

    public void sharedSaved(SharedPreferences sharedPreferences, String state, String memberState){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(state, memberState);
        editor.apply();
    }
}