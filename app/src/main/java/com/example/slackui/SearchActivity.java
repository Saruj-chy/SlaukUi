package com.example.slackui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    EditText editText ;
    String key;
    String[] data;

    private String store_string="";
    String[] nameList ;

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
                int lengthOfStr = str.length() ;
                int lastIndexOfSpace = str.lastIndexOf(" ");


                Log.e("editText", "Maximum: "+ Math.max(0, lastIndexOfSpace)) ;
                Log.e("editText", "diff: "+ (lengthOfStr-lastIndexOfSpace+1)) ;

                if(lastIndexOfSpace<0){
                    key = str ;
                }else{
                    key = str.substring(lastIndexOfSpace+1, lengthOfStr);
                }

//                 key = str.substring(Math.max(0, lastIndexOfSpace), lengthOfStr-lastIndexOfSpace+1);
                Log.e("editText", "key: "+ key) ;

                nameList = new String[]{"Saruj", "Rofiq", "Dipa", "Maitheli"};

                HashMap<String, String[]> map = new HashMap<>();
                map.put("@", nameList);
                Log.e("Java_123","map key "+map.containsKey(key.trim()));

                if(map.containsKey(key.trim())){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);

                    final String[] names = new String[nameList.length];

                    for(int i=0;i<nameList.length;i++){
                        names[i]=nameList[i];
                    }

                    builder.setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final SpannableStringBuilder builder = new SpannableStringBuilder();
                            String selected_item = "<"+names[which]+"> ";
                            String previous_str = editText.getText().toString();

                            store_string  = previous_str + selected_item;
                            Log.e("Java_123","store_string "+store_string);

                            String[] separated = store_string.split(" ");
                            Boolean color_spanned = false;
                            Log.e("Java_123","separated "+separated.toString());

                            for(int i=0;i<separated.length;i++){

                                if(("@".equals(String.valueOf(separated[i].charAt(0))))&&("<".equals(String.valueOf(separated[i].charAt(1))))
                                        && (">".equals(String.valueOf(separated[i].charAt(separated[i].length()-1))))    ){
                                    color_spanned=true;
                                    Log.e("Java_123","true "+separated[i]);
                                    Log.e("Java_123","true "+separated[i].length());
                                    Log.e("Java_123","true "+separated[i].charAt(0));

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



//                if(map.containsKey(key)){
//                    data = map.get(key);
//                    Log.e("editText", "key: "+ data) ;
//                }else{
//                    // not found
//                    Log.e("editText", "key: "+ map.containsKey(key)) ;
//                }

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