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

public class AddNew extends AppCompatActivity {
    private EditText nameNew,descriptionNew,authorNew,imgNew;
    private Button btnBack,btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        mapping();
        handleBtnClick();
    }
    public void mapping(){
        nameNew = findViewById(R.id.titleNew);
        descriptionNew = findViewById(R.id.descNewAdd);
        authorNew = findViewById(R.id.authorNewAdd);
        imgNew = findViewById(R.id.imgNewAdd);
        btnBack = findViewById(R.id.btnBackofNew);
        btnAdd = findViewById(R.id.btnBackofNew1);
    }

    public  void reset(){
        nameNew.setText("");
        descriptionNew.setText("");
        authorNew.setText("");
        imgNew.setText("");
    }

    public boolean validate(){
        if(nameNew.length() < 6 || descriptionNew.length() < 10 || authorNew.length() <  6 || imgNew.length() < 10){
            return  false;
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
                if(validate() == false){
                    Toast.makeText(getApplicationContext(),"inValid",Toast.LENGTH_LONG).show();
                }else{
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.addNews, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(),"Create new News" + response,Toast.LENGTH_LONG).show();
                                reset();

                            if(response.equals("Failure")){
                                Toast.makeText(getApplicationContext(),"News is" + response,Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"News is" + error,Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("nameNew",nameNew.getText().toString());
                            hashMap.put("descriptionNew",descriptionNew.getText().toString());
                            hashMap.put("authorNew",authorNew.getText().toString());
                            hashMap.put("imgNew",imgNew.getText().toString().trim());
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });

    }
}