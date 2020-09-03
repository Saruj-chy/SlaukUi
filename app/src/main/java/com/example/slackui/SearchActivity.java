package com.example.slackui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    EditText editText ;
    String key,  data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.editText) ;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.e("editText", "charSequence: "+ charSequence) ;
//                Log.e("editText", "i: "+ i) ;
//                Log.e("editText", "i1: "+ i1) ;
//                Log.e("editText", "i2: "+ i2) ;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editText.getText().toString() ;
//                Log.e("editText", "str: "+ str) ;

                int lengthOfStr = str.length() ;
                int lastIndexOfSpace = str.lastIndexOf(" ");
                Log.e("editText", "lengthOfStr: "+ lengthOfStr) ;
                Log.e("editText", "lastIndexOfSpace: "+ lastIndexOfSpace) ;


                Log.e("editText", "Maximum: "+ Math.max(0, lastIndexOfSpace)) ;
                Log.e("editText", "diff: "+ (lengthOfStr-lastIndexOfSpace+1)) ;

                if(lastIndexOfSpace<0){
                    key = str.substring(0, lengthOfStr-1) ;
                }else{
                    key = str.substring(lastIndexOfSpace, lengthOfStr);
                }

//                 key = str.substring(Math.max(0, lastIndexOfSpace), lengthOfStr-lastIndexOfSpace+1);
                Log.e("editText", "key: "+ key) ;


                HashMap<String, String> map = new HashMap<>();
                map.put("@", "Mydata");
                if(map.containsKey(key)){
                    data = map.get(key);
                    Log.e("editText", "key: "+ data) ;
                }else{
                    // not found
                    Log.e("editText", "key: "+ map.containsKey(key)) ;
                }

            }
        });

        Spanned spanned ;
        String target_str = "<b>Sarose<b> <i>Datta<i>" ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(target_str, Html.FROM_HTML_MODE_LEGACY) ;
        }
        else{
            spanned = Html.fromHtml(target_str) ;
        }

//        editText.setText(spanned);


    }
}