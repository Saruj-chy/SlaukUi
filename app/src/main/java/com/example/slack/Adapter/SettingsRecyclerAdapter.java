package com.example.slack.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.slack.Fragments.SettingFragment;
import com.example.slack.Model.SettingModel;
import com.example.slack.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SettingsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mCtx;
    private List<SettingModel> settingList;
    String logName, logDomain ;

    SettingFragment fragment;
    SharedPreferences sharedPreferences ;
    String positionId ;

    public SettingsRecyclerAdapter(Context mCtx, List<SettingModel> settingList, SettingFragment settingInterface) {
        this.mCtx = mCtx;
        this.settingList = settingList;
        this.fragment = settingInterface;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view ;
        view = inflater.inflate(R.layout.card_layout, null);


        sharedPreferences = mCtx.getSharedPreferences("positionId", MODE_PRIVATE);

        return new SettingsRecyclerAdapter.PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final SettingModel settings = settingList.get(position);

        ((PostViewHolder) holder).bind(settings, position);


    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }


    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLogoName, textViewLogoText ;
        LinearLayout linearLayout ;


        public PostViewHolder(View itemView) {
            super(itemView);

            textViewLogoName = itemView.findViewById(R.id.logo_name);
            textViewLogoText = itemView.findViewById(R.id.logo_textName);
            linearLayout = itemView.findViewById(R.id.linearLayout01);


        }

        public void bind(final SettingModel settings, final int position){
            String name= settings.getName() ;
            String combinedName = "" ;
            String[] parts = name.split(" ");
            for(int i=0; i<parts.length; i++){
                CharSequence firstChar = parts[i].subSequence(0, 1) ;
                combinedName = combinedName + firstChar;
            }
            textViewLogoName.setText(combinedName);
            textViewLogoText.setText(settings.getName());

//========    give position
            positionId = sharedPreferences.getString("positionId", "");
            if(!positionId.equals("")){
                if(Integer.valueOf(positionId) == position){
                    linearLayout.setBackgroundResource(R.drawable.input);
                }
            }
            else{
                textViewLogoName.setBackgroundResource(R.drawable.logo_background);
            }
            textViewLogoName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    logName= settings.getName();
                    logDomain = settings.getDomain() ;

                    fragment.sharedSaved(sharedPreferences,"positionId", String.valueOf(position));
                    fragment.Finish();
                    fragment.onLogoName(logName, logDomain);
                }
            });

//            notifyDataSetChanged();

        }



    }

}