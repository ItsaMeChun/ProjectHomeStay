package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecthomestay.R;

import java.util.HashMap;
import java.util.Map;

import Helper.helper;

public class AddCategory extends AppCompatActivity {
    private Button btnBack,btnAdd;
    private EditText nameCategory,imgCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        mapping();
        handleBtnClick();
    }

    public void mapping(){
        btnAdd = findViewById(R.id.btnAddNewCategory);
        btnBack = findViewById(R.id.btnBackCategory);
        nameCategory = findViewById(R.id.add_nameCategory);
        imgCategory = findViewById(R.id.urlCategory);
    }

    public  void resetField(){
        nameCategory.setText("");
        imgCategory.setText("");
    }

    public  boolean validate(){
        if(nameCategory.length() < 6 || imgCategory.length() <6){
            return false;
        }
        return true;
    }

    public void handleBtnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()){
                    Toast.makeText(getApplicationContext(),"inValid",Toast.LENGTH_LONG).show();
                }else{
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.addCategory, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                resetField();
                                Toast.makeText(AddCategory.this,"Create category Success" + response,Toast.LENGTH_LONG).show();


                            if(response.equals("already")){
                                Toast.makeText(AddCategory.this,"Category is" + response,Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("nameCategory",nameCategory.getText().toString());
                            hashMap.put("imgCategory",imgCategory.getText().toString().trim());
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
}