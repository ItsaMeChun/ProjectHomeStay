package com.example.projecthomestay.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projecthomestay.R;

public class settingsPage extends AppCompatActivity {
    TextView userName;
    ConstraintLayout addCategory, addProduct, next, addNew,aboutUs, edtNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        mapping();
        handleBtnClick();
    }

    public void mapping() {
        addProduct = findViewById(R.id.AddProduct);
        addCategory = findViewById(R.id.addCategory);
        addNew = findViewById(R.id.AddNew);
        aboutUs = findViewById(R.id.AboutUs);
        next = findViewById(R.id.next);
        userName = findViewById(R.id.userServices);
        edtNews = findViewById(R.id.edtNews);

        if(AppActivity.isLogging){
            userName.setText(AppActivity.userlogin.getUserName());
        }else{
            userName.setText("Welcome to Booking Homestay");
        }
    }

    public void handleBtnClick() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, services.class);
                startActivity(intent);
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, AddProduct.class);
                startActivity(intent);
            }
        });
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, AddCategory.class);
                startActivity(intent);
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, AddNew.class);
                startActivity(intent);
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, AboutUs.class);
                startActivity(intent);
            }
        });

        edtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsPage.this, editnews.class);
                startActivity(intent);
            }
        });
    }
}