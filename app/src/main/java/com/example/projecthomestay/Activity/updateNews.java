package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import Model.New;
import Model.product;

public class updateNews extends AppCompatActivity {

    private EditText nameNewsUpdate,urlNewsUpdate,descriptionNewsUpdate,authorNewsUpdate;
    private New obj;
    private Button btnBack,btnUpdate;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);
        mapping();
        bindDataAllField();
        handleBtnClick();
    }

    public void mapping(){
        nameNewsUpdate = findViewById(R.id.titleNewUpdate);
        urlNewsUpdate = findViewById(R.id.imgNewUpdate);
        descriptionNewsUpdate = findViewById(R.id.descNewUpdate);
        authorNewsUpdate = findViewById(R.id.authorNewUpdate);
        btnBack = findViewById(R.id.btnBackEdtNews);
        btnUpdate = findViewById(R.id.btnSentNewUpdate);
    }

    public void bindDataAllField(){
        obj = (New)getIntent().getSerializableExtra("object");
        nameNewsUpdate.setText(obj.getNameView());
        urlNewsUpdate.setText(obj.getImgNew());
        descriptionNewsUpdate.setText(obj.getDescriptionNew());
        authorNewsUpdate.setText(obj.getAuthorNew());
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
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.updateNews, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Update Success" + response,Toast.LENGTH_LONG).show();
//                        notifyDataSetChanged();

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
                        hashMap.put("idNew",String.valueOf(obj.getIdNew()));
                        hashMap.put("nameNew",nameNewsUpdate.getText().toString());
                        hashMap.put("descriptionNew",descriptionNewsUpdate.getText().toString());
                        hashMap.put("imgNew",urlNewsUpdate.getText().toString());
                        hashMap.put("authorNew",authorNewsUpdate.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

}