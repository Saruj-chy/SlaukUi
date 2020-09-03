package com.example.slackui.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.slackui.Class.SettingModel;
import com.example.slackui.Interfaces.SettingInterface;
import com.example.slackui.R;

import java.util.List;

public class Adapter {

}

//        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//
//    private Context mCtx;
//    private List<SettingModel> productList;
//    String logName, logDomain ;
//    final static int MyAdapterViewType_Text = 1 ;
//    //    final static int MyAdapterViewType_Image = 1 ;
//    final static int MyAdapterViewType_ImageText = 2 ;
//    final static int MyAdapterViewType_VideoText = 3 ;
//
//    SettingInterface settingInterface;
//
//    public Adapter(Context mCtx, List<SettingModel> productList, SettingInterface settingInterface) {
//        this.mCtx = mCtx;
//        this.productList = productList;
//        this.settingInterface = settingInterface;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//
//        SettingModel logclass = this.productList.get(position) ;
//        switch (logclass.getType()){
//            case "text":
//                return MyAdapterViewType_Text ;
//            case "image":
//                return MyAdapterViewType_ImageText ;
//
//            case "video":
//                return MyAdapterViewType_VideoText ;
//
//            default:
//                return super.getItemViewType(position) ;
//        }
//
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view ;
//        switch (viewType){
//            case MyAdapterViewType_Text:
//                view = inflater.inflate(R.layout.card_layout, null);
//                break;
//            case MyAdapterViewType_ImageText:
//                view = inflater.inflate(R.layout.card_layout, null);
//                break;
//            case MyAdapterViewType_VideoText:
//                view = inflater.inflate(R.layout.card_layout, null);
//                break;
//            default:
//                view =  inflater.inflate(R.layout.card_layout, null);
//                break;
//
//
//        }
//
//
//
//        return new Adapter.PostViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//
//        final SettingModel products = productList.get(position);
//
//        switch (getItemViewType(position)) {
//            case MyAdapterViewType_Text:
//                ((Adapter.PostViewHolder) holder).bind(products);
//                break;
//            case MyAdapterViewType_ImageText:
//                ((Adapter.PostViewHolder) holder).bind(products);
//                break;
//            case MyAdapterViewType_VideoText:
//                ((Adapter.PostViewHolder) holder).bind(products);
//                break;
//            default:
//                ((Adapter.PostViewHolder) holder).bind(products);
//                break;
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//
//    class PostViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textViewLogoName, textViewLogoText ;
//
//
//        public PostViewHolder(View itemView) {
//            super(itemView);
//
//            textViewLogoName = itemView.findViewById(R.id.logo_name);
//            textViewLogoText = itemView.findViewById(R.id.logo_textName);
//
//
//        }
//
//        public void bind(final SettingModel products){
//            String name= products.getLogoName() ;
//            String combinedName = "" ;
//            String[] parts = name.split(" ");
//            for(int i=0; i<parts.length; i++){
//                CharSequence firstChar = parts[i].subSequence(0, 1) ;
//                combinedName = combinedName + firstChar;
//            }
//            Log.e("TAG", "combinedName: "+combinedName ) ;
//
//            textViewLogoName.setText(combinedName);
//            textViewLogoText.setText(products.getDomain());
//            textViewLogoName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    logName= products.getLogoName();
//                    logDomain = products.getDomain() ;
//                    Log.e("TAG", "logName: "+logName+" logDomain: "+logDomain ) ;
//
//                    settingInterface.onLogoName(logName, logDomain);
//
//                }
//            });
//
//
//
//        }
//
//    }
//}