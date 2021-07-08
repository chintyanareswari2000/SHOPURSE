package com.example.projekakhirmoop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    //declare
    Button btn;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //pemetaan
        btn = (Button) findViewById(R.id.btn_shop);
        nama = findViewById(R.id.nama);

        //mendapatkan intent dari page login
        String username = getIntent().getStringExtra("uname");
        nama.setText(username);

        //onclickbtn untuk ke page shopping list
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


    }

    //method untuk ke shopping list page
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //method untuk ke map page
    public void goToMap(View v){
        Intent intent = new Intent(this,Gmaps.class);
        startActivity(intent);
    }

    //method untuk logout
    public void goToLogin(View v){
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }


}