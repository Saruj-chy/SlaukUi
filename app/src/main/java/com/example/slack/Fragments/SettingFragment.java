package com.example.slack.Fragments;

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
import com.android.volley.toolbox.StringRequest;
import com.example.slack.Adapter.SettingsRecyclerAdapter;
import com.example.slack.Constant.Config;
import com.example.slack.Constant.Constant;
import com.example.slack.FragmentActivity;
import com.example.slack.Interfaces.SettingInterface;
import com.example.slack.MainActivity;
import com.example.slack.Model.SettingModel;
import com.example.slack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class SettingFragment extends Fragment implements SettingInterface {

    RecyclerView firstFragment_RV;
    ImageButton addBtn ;
    TextView textName, textDomain;
    LinearLayout linearLayout01, linearLayout02, linearLayout03, linearLayout04, linearLayout05, linearLayout06 ;

    List<SettingModel> settingList = new ArrayList<>();
    private String settingName, settingDomain ;

    //=========   sharedprefarence
    SharedPreferences sharedPreferences,  sharedPreferences1 ;


    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("name", MODE_PRIVATE);
        sharedPreferences1 = this.getActivity().getSharedPreferences("domain", MODE_PRIVATE);

        InitializeFields(view) ;
        loadProducts();

        setClickedListen() ;


        return view ;
    }

    private void setClickedListen() {
        linearLayout01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }

    private void loadProducts() {

        try {
            JSONObject object = new JSONObject(Constant.log_json);
            Log.e("setting", "response: "+object );
            JSONArray logArray = object.getJSONArray("log");

            for(int i=0;i<logArray.length();i++){

                JSONObject settingObject = logArray.getJSONObject(i);
                Log.e("setting", "response: "+settingObject );



//                SettingModel aSettingModel = new SettingModel() ;
//                Field[] fields =  aSettingModel.getAllFields() ;
//                Log.e("setting", "fields: "+fields.toString() );
//
//                for(int j=0; j<fields.length; j++ ){
//                    String fieldName = fields[j].getName() ;
//                    Log.e("setting", "fieldName: "+fieldName );
//                    String fieldValueInJson =settingObject.has(fieldName)? settingObject.getString(fieldName) : "" ;
//                    Log.e("setting", "fieldValueInJson: "+ fieldValueInJson ) ;
//                    fields[j].set(aSettingModel, fieldValueInJson);
//                }
//                settingList.add(aSettingModel);

                settingList.add(new SettingModel(
                        settingObject.getString("id"),
                        settingObject.getString("name"),
                        settingObject.getString("domain")
                ));
            }
            SettingsRecyclerAdapter adapter = new SettingsRecyclerAdapter(getContext(), settingList, SettingFragment.this);
            firstFragment_RV.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            firstFragment_RV.setLayoutManager(manager);
            Log.e("setting", "data: "+ settingList.toString()) ;


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("setting", "error: "+ e.toString() ) ;

        }

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
                Intent intent = new Intent(getContext(), FragmentActivity.class);
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