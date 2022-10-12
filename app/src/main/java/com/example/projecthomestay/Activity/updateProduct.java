package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Helper.helper;
import Model.product;

public class updateProduct extends AppCompatActivity {
    private EditText nameProductUpdate,urlProductUpdate,descriptionProductUpdate,locationProductUpdate, priceProductUpdate,idCategoryProductUpdate;
    private product obj;
    private Button btnBack,btnUpdate;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        mapping();
        bindDataAllField();
        handleBtnClick();
    }

    public void mapping(){
        nameProductUpdate = findViewById(R.id.nameProductUpdate);
        urlProductUpdate = findViewById(R.id.imgProductUpdate);
        descriptionProductUpdate = findViewById(R.id.descProductUpdate);
        locationProductUpdate = findViewById(R.id.locationProductUpdate);
        priceProductUpdate = findViewById(R.id.priceProductUpdate);
        idCategoryProductUpdate = findViewById(R.id.categoryProductUpdate);
        btnBack = findViewById(R.id.btnBackServicesUpdate);
        btnUpdate = findViewById(R.id.btnServiceUpdate);
    }

    public void handleBtnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.updateProduct, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),"Update Success" + response,Toast.LENGTH_LONG).show();


                        if(response.equals("Failure")){
                            Toast.makeText(getApplicationContext(),"Failure" + response,Toast.LENGTH_LONG).show();
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
                        hashMap.put("_id",String.valueOf(obj.get_id()));
                        hashMap.put("nameProduct",nameProductUpdate.getText().toString());
                        hashMap.put("price",priceProductUpdate.getText().toString());
                        hashMap.put("description",descriptionProductUpdate.getText().toString());
                        hashMap.put("imgProduct",urlProductUpdate.getText().toString());
                        hashMap.put("_idCategory",idCategoryProductUpdate.getText().toString());
                        hashMap.put("location",locationProductUpdate.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    public void bindDataAllField(){
        obj = (product)getIntent().getSerializableExtra("object");
        nameProductUpdate.setText(obj.getNameProduct());
        urlProductUpdate.setText(obj.getImgProduct());
        descriptionProductUpdate.setText(obj.getDescription());
        locationProductUpdate.setText(obj.getLocation());
        priceProductUpdate.setText(String.valueOf(obj.getPrice()));
        idCategoryProductUpdate.setText(String.valueOf(obj.get_idCategory()));
    }
}