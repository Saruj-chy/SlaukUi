package com.example.slack;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mContext;
    private ArrayList<Uploads> list = new ArrayList<>();
    final static int MyAdapterViewType_text = 1;
    final static int MyAdapterViewType_image = 2;
    final static int MyAdapterViewType_image_text = 3;
    final static int MyAdapterViewType_vedio = 4;
    final static int MyAdapterViewType_vedio_text = 5;

    public MyAdapter(Context mContext, ArrayList<Uploads> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        Uploads uploads = this.list.get(position);
        switch (uploads.getType()){
            case "text":
                return MyAdapterViewType_text;
            case "image":
                return MyAdapterViewType_image;
            case "image_text":
                return MyAdapterViewType_image_text;
            case "vedio":
                return MyAdapterViewType_vedio ;
            case "vedio_text":
                return MyAdapterViewType_vedio_text;
            default:
                return super.getItemViewType(position);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view;
        switch (viewtype){
            case MyAdapterViewType_text:
                view = layoutInflater.inflate(R.layout.layout_text,viewGroup,false);
                break;
            case MyAdapterViewType_image:
                view = layoutInflater.inflate(R.layout.layout_image,viewGroup,false);
                break;
            case MyAdapterViewType_image_text:
                view = layoutInflater.inflate(R.layout.layout_imagetext,viewGroup,false);
                break;
            case MyAdapterViewType_vedio:
                view = layoutInflater.inflate(R.layout.layout_vedio,viewGroup,false);
                break;
            case MyAdapterViewType_vedio_text:
                view = layoutInflater.inflate(R.layout.layout_vediotext,viewGroup,false);
                break;
            default:
                view = layoutInflater.inflate(R.layout.layout_text,viewGroup,false);
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Uploads uploads = list.get(position);

        switch (getItemViewType(position)){
            case MyAdapterViewType_text:
                ((ViewHolder)viewHolder).bind(uploads);
                break;
            case MyAdapterViewType_image:
                ((ViewHolder)viewHolder).bind2(uploads);
                break;
            case MyAdapterViewType_image_text:
                ((ViewHolder)viewHolder).bind3(uploads);
                break;
            case MyAdapterViewType_vedio:
                ((ViewHolder)viewHolder).bind4(uploads);
                break;
            case MyAdapterViewType_vedio_text:
                ((ViewHolder)viewHolder).bind5(uploads);
                break;
            default:
                ((ViewHolder)viewHolder).bind(uploads);
                break;
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv,time_tv,message_tv;
        ImageView profile_image,post_image;
        VideoView vedioView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.name);
            time_tv = itemView.findViewById(R.id.time);
            message_tv = itemView.findViewById(R.id.message);
            post_image = itemView.findViewById(R.id.image_view);
            profile_image = itemView.findViewById(R.id.image);
            vedioView = itemView.findViewById(R.id.vedio);
        }

        public void bind(Uploads uploads) {
            name_tv.setText(uploads.getName());
            time_tv.setText(uploads.getTime());
            message_tv.setText(uploads.getMessage());

            Picasso.get().load(uploads.getProfile_image()).into(profile_image);
        }

        public void bind2(Uploads uploads) {
            name_tv.setText(uploads.getName());
            time_tv.setText(uploads.getTime());

            Picasso.get().load(uploads.getProfile_image()).into(profile_image);
            Picasso.get().load(uploads.getPost_image()).into(post_image);
        }

        public void bind3(Uploads uploads) {
            name_tv.setText(uploads.getName());
            time_tv.setText(uploads.getTime());
            message_tv.setText(uploads.getMessage());

            Picasso.get().load(uploads.getProfile_image()).into(profile_image);
            Picasso.get().load(uploads.getPost_image()).into(post_image);
        }
        public void bind4(Uploads uploads) {
            name_tv.setText(uploads.getName());
            time_tv.setText(uploads.getTime());

            Picasso.get().load(uploads.getProfile_image()).into(profile_image);

           // vedioView.setVideoURI(Uri.parse(uploads.getVedio_view()));
           // vedioView.start();
        }
        public void bind5(Uploads uploads) {
            name_tv.setText(uploads.getName());
            time_tv.setText(uploads.getTime());
            message_tv.setText(uploads.getMessage());

            Picasso.get().load(uploads.getProfile_image()).into(profile_image);

           // vedioView.setVideoURI(Uri.parse(uploads.getVedio_view()));
           // vedioView.start();
        }
    }
}
