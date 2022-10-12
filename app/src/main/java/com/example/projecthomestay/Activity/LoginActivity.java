package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecthomestay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Helper.helper;
import Model.User;

public class LoginActivity extends AppCompatActivity {


    EditText username,password;
    Button btnLogin;
    TextView  btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingView();
        handleBtnALl();
    }

    private void mappingView() {
        username = findViewById(R.id.username_LG);
        password = findViewById(R.id.password_LG);
        btnLogin = findViewById(R.id.btn_login_LG);
        btnSignUp = findViewById(R.id.btn_signup_LG);
    }

    private void handleBtnALl() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().length()==0 || password.getText().length()==0){
                    Toast.makeText(LoginActivity.this,
                            "Pass or Username is empty",Toast.LENGTH_LONG).show();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.loginURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            saveProductAndSetCurrentUser();
                        }else{
                            Toast.makeText(LoginActivity.this, "Password or email not match", Toast.LENGTH_LONG).show();
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
                        hashMap.put("username",username.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openActivitySignUp(){
        saveProductAndSetCurrentUser();
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void opentActivityApp(){
        Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
    }

    public void saveProductAndSetCurrentUser(){
        AppActivity.isLogging = true;
        AppActivity.userlogin = new User(username.getText().toString());
        if(AppActivity.cart.size() > 0){
            postDataToServer();
        }else{
            opentActivityApp();
        }
    }

    public  void postDataToServer() {
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, helper.addCatertLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().toString().equals("Success")){
                    AppActivity.cart.clear();
                    Intent intent = new Intent(getApplicationContext(),AppActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Thanks you for booking",Toast.LENGTH_LONG).show();
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
                JSONArray jsonArray = new JSONArray();
                for(int i = 0; i< AppActivity.cart.size();i++){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userName",AppActivity.userlogin.getUserName());
                        jsonObject.put("idProduct",AppActivity.cart.get(i).get_idProduct());
                        jsonObject.put("nameProduct",AppActivity.cart.get(i).getNameProduct());
                        jsonObject.put("price",AppActivity.cart.get(i).getPrice());
                        jsonObject.put("slProduct",AppActivity.cart.get(i).getNumber());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);

                }
                hashMap.put("res_data",jsonArray.toString());
                return  hashMap;
            }
        };
        requestQueue1.add(request);
    }
}