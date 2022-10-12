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

public class SignUpActivity extends AppCompatActivity {

    Button btn_DK;
    EditText userName,password,getPassword,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mapping();
        handleButtonAll();
    }

    public void mapping(){
        btn_DK = findViewById(R.id.btn_dk);
        userName = findViewById(R.id.username_DK);
        password = findViewById(R.id.password_DK);
        email = findViewById(R.id.edittxt_email);
        getPassword = findViewById(R.id.password_DK_re);
    }
    public void handleButtonAll(){

        btn_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateDK()) return;
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest  stringRequest = new StringRequest(Request.Method.POST, helper.registerURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String res = response.toString();

                        if(res.trim().toString().equals("already")){
                            Toast.makeText(getApplicationContext(),"Account is" + response,Toast.LENGTH_LONG).show();
                        }else{
                            openLoginActivity();
                            Toast.makeText(getApplicationContext(),"Create Account Success" + response,Toast.LENGTH_LONG).show();
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
                        hashMap.put("username",userName.getText().toString());
                        hashMap.put("email",email.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    private boolean validateDK() {
        if(userName.getText().length() == 0 || password.getText().length() ==0 ||
            getPassword.getText().length() == 0
        ){
            Toast.makeText(SignUpActivity.this,
                    "Pass or Username is empty",Toast.LENGTH_LONG).show();
            return false;
        }else if(!password.getText().toString().equals(getPassword.getText().toString())){
            Toast.makeText(SignUpActivity.this,
                    "Pass not math",Toast.LENGTH_LONG).show();
            return false;

        }else if(userName.getText().length() < 6){
            Toast.makeText(SignUpActivity.this,
                    "Username > 6",Toast.LENGTH_LONG).show();
            return false;

        }else {
            return true;
        }


    }

    public  void openLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}