package com.example.myapplication_sf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // created at: 22 dec 2020..

    private EditText name, password;
    private Button saveData, loadData;
    private TextView name_Display, pass_Display;
    private String s_name,s_pass;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // All id has been defined here..

        name= findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        saveData= findViewById(R.id.button_save);
        loadData= findViewById(R.id.button2_display);
        name_Display= findViewById(R.id.textViewName);
        pass_Display= findViewById(R.id.textViewPassWord);

        // Starting define of display property..
        name_Display.setVisibility(View.INVISIBLE);
        pass_Display.setVisibility(View.INVISIBLE);
        saveData.setEnabled(false);
     //   saveData.setBackground(getDrawable(R.drawable.design_blur));
        loadData.setEnabled(false);
        loadData.setVisibility(View.INVISIBLE);
        saveData.setVisibility(View.VISIBLE);

      // text changing save button controller..

        name.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
    }



    public void Save_Data(View view) {

    try{
    SharedPreferences preferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString("PersonName",s_name);
    editor.putString("PersonPassword",s_pass);
    editor.commit();

    // property color change setting change..
    saveData.setEnabled(false);
    saveData.setVisibility(View.INVISIBLE);
    loadData.setVisibility(View.VISIBLE);
    loadData.setEnabled(true);

    }
    catch (Exception e){
    Toast.makeText(this,"Something Wrong",Toast.LENGTH_SHORT).show();
    }

    }

    public void Load_Data(View view) {
        try{
            SharedPreferences preferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
            if(preferences.contains("PersonName")&& preferences.contains("PersonPassword")){
                String s_name=  preferences.getString("PersonName","No data in name field");
                String s_pass=  preferences.getString("PersonPassword","No data in password field");
                name_Display.setText(s_name);
                pass_Display.setText(s_pass);

                // property color change setting change..
                saveData.setVisibility(View.VISIBLE);
                loadData.setVisibility(View.INVISIBLE);
                loadData.setEnabled(false);
                name_Display.setVisibility(View.VISIBLE);
                pass_Display.setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(this,"Data missing..",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this," Something Wrong ..",Toast.LENGTH_SHORT).show();
        }


    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            s_name = name.getText().toString().trim();
            s_pass = password.getText().toString().trim();
            saveData.setEnabled(!s_name.isEmpty()&& !s_pass.isEmpty());
            if(!s_name.isEmpty()&& !s_pass.isEmpty()){

                saveData.setBackground(getDrawable(R.drawable.button));
            }
            else{

                saveData.setBackground(getDrawable(R.drawable.design_blur));

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}