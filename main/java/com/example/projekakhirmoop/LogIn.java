package com.example.projekakhirmoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LogIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //petakan setiap unsur yang dipakai dalam xml
        TextInputLayout textInputLayoutUname = findViewById(R.id.username);
        TextInputLayout textInputLayoutPass = findViewById(R.id.password);
        Button login = findViewById(R.id.btn_login);
        TextView alert = findViewById(R.id.alert);

        //untuk melakukan action ketika tombol di klik
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mengambil value dari input
                String textUname = String.valueOf(textInputLayoutUname.getEditText().getText().toString());
                String textPass = String.valueOf(textInputLayoutPass.getEditText().getText().toString());

                //membuat intent
                Intent myIntent = new Intent(v.getContext(),Dashboard.class);
                myIntent.putExtra("uname", textUname);
                myIntent.putExtra("pass", textPass);

                //if else condition untuk password
                if(textPass.equals("password")){
                    startActivity(myIntent);
                }else {
                    alert.setVisibility(View.VISIBLE);
                }



            }
        });


    }
}