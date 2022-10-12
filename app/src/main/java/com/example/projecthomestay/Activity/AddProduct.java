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

public class AddProduct extends AppCompatActivity {
    private Button btnBack,btnAdd;
    private EditText nameProduct,imgProduct,idCategory,locationProduct,descProduct,priceProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mapping();
        handleBtnClick();
    }

    public void mapping() {
        btnAdd = findViewById(R.id.btnAddNewProduct);
        btnBack = findViewById(R.id.btnBackAdd);
        nameProduct = findViewById(R.id.nameNew_product);
        imgProduct = findViewById(R.id.imgProductAdd);
        idCategory = findViewById(R.id.categoryProdutAdd);
        locationProduct = findViewById(R.id.locationProductAdd);
        descProduct = findViewById(R.id.descProductAdd);
        priceProduct = findViewById(R.id.priceProductAdd);
    }

    public  boolean Validate(){
        if(nameProduct.getText().length() < 10 || priceProduct.getText().length() == 0 ||  descProduct.getText().length() < 10 || imgProduct.getText().length() <10
            || locationProduct.getText().length() < 10 || idCategory.getText().length() == 0
        ) return false;
        return true;
    }

    public void reset(){
        nameProduct.setText("");
        imgProduct.setText("");
        idCategory.setText("");
        locationProduct.setText("");
        descProduct.setText("");
        priceProduct.setText("");
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
                if(Validate()){
                    postData();

                }else{
                    Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public  void postData(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.addProduct, new Response.Listener<String>() {
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
                hashMap.put("nameProduct",nameProduct.getText().toString());
                hashMap.put("price",priceProduct.getText().toString());
                hashMap.put("description",descProduct.getText().toString());
                hashMap.put("imgProduct",imgProduct.getText().toString());
                hashMap.put("location",locationProduct.getText().toString());
                hashMap.put("_idCategory",idCategory.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}