package com.example.slack.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.slack.MainActivity;
import com.example.slack.MyAdapter;
import com.example.slack.R;
import com.example.slack.Uploads;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class LastFragment extends Fragment {

    MyAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Uploads> arrayList_uploads = new ArrayList<>();
    private ArrayList<String> user_name = new ArrayList<>();
    private EditText editText;
    private String key;
    private String store_string="";
    private int lastIndexOfEmptySpace;
    SpannableStringBuilder builder = new SpannableStringBuilder();


    public LastFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_last, container, false);

        editText = view.findViewById(R.id.edit_text);

        mentionEditText();

        loadMyRecyclerView(view);

        showData();

        return view ;
    }

    private void mentionEditText() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editText.getText().toString();
                lastIndexOfEmptySpace = str.lastIndexOf(" ");
                int strlength = str.length();

                if(lastIndexOfEmptySpace<0){
                    key = str;
                }
                else{
                    key = str.substring(lastIndexOfEmptySpace+1,strlength);
                }


                HashMap<String,ArrayList<String>> map =new HashMap<>();
                map.put("@",user_name);

                if(map.containsKey(key.trim())){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    final String[] names = new String[user_name.size()];

                    for(int i=0;i<user_name.size();i++){
                        names[i]=user_name.get(i);
                    }


                    builder.setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final SpannableStringBuilder builder = new SpannableStringBuilder();
                            String selected_item = "<"+names[which]+"> ";
                            String previous_str = editText.getText().toString();

                            store_string  = previous_str + selected_item;

                            String[] separated = store_string.split(" ");
                            Boolean color_spanned = false;

                            for(int i=0;i<separated.length;i++){

                                if(("@".equals(String.valueOf(separated[i].charAt(0))))&&("<".equals(String.valueOf(separated[i].charAt(1))))){
                                    color_spanned=true;
                                    Log.e("Java_123","true "+separated[i]);
                                    Log.e("Java_123","true "+separated[i].length());

                                }
                                if(color_spanned){
                                    SpannableString megentaSpannable= new SpannableString(separated[i]+" ");
                                    megentaSpannable.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, separated[i].length(), 0);
                                    builder.append(megentaSpannable);
                                }
                                else{
                                    SpannableString blackSpannable= new SpannableString(separated[i]+" ");
                                    blackSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, separated[i].length()-1, 0);
                                    builder.append(blackSpannable);

                                }
                                if(">".equals(String.valueOf(separated[i].charAt(separated[i].length()-1)))){
                                    color_spanned=false;
                                }
                            }
                            editText.setText(builder);

                            int position =editText.length();
                            Editable etext = editText.getText();
                            Selection.setSelection(etext, position);


                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else{

                }


            }
        });
    }

    private void loadMyRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(getContext(),arrayList_uploads);
        recyclerView.setAdapter(adapter);

    }

    private void showData() {
        FirebaseFirestore.getInstance().collection("Newsfeed")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        arrayList_uploads.clear();
                        user_name.clear();
                        for(DocumentSnapshot doc: task.getResult()){

                            user_name.add(doc.getString("name"));

                            Uploads model = new
                                    Uploads(doc.getString("type"),
                                    doc.getString("name"),
                                    doc.getString("time"),
                                    doc.getString("message"),
                                    doc.getString("profile_image"),
                                    doc.getString("post_image"),
                                    doc.getString("vedio_view")
                                    //STUCK HERE

                            );
                            arrayList_uploads.add(model);
                        }

                        loadPost(arrayList_uploads);
                    }
                });


    }

    private void loadPost(ArrayList<Uploads> arrayList_uploads) {
        adapter.notifyDataSetChanged();

    }
}