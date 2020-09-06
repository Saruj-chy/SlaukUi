package com.example.slack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.slack.Model.Main_Model;
import com.example.slack.R;

import java.util.ArrayList;

public class RV_Adapter_main extends RecyclerView.Adapter<RV_Adapter_main.ViewHolder1> {

    private ArrayList<Main_Model> myList;
    private Context context;
    private String preGroupName = null;

    public RV_Adapter_main(ArrayList<Main_Model> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rv_mid_frag_main, parent, false);

        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        Main_Model current = myList.get(position);
        if (preGroupName == null)
        {
            holder.groupName.setVisibility(View.VISIBLE);
            holder.groupName.setText(current.getGroup());
            holder.itemName.setText(current.getTitle());
            preGroupName = current.getGroup();
            holder.groupName.setPadding(10, 2, 0, 10);
        }
        else if (current.getGroup().equals(preGroupName))
        {
            holder.groupName.setVisibility(View.GONE);
            holder.itemName.setText(current.getTitle());
        }
        else if (!current.getGroup().equals(preGroupName))
        {
            holder.groupName.setVisibility(View.VISIBLE);
            holder.groupName.setText(current.getGroup());
            holder.itemName.setText(current.getTitle());
            preGroupName = current.getGroup();

        }

    }


    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView groupName;
        TextView itemName;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.tv_rv_main_heading);
            itemName = itemView.findViewById(R.id.tv_items);
        }
    }




}
