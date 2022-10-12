package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Helper.helper;

public class Activity_buyInfo extends AppCompatActivity {
    private EditText nameCustomer, phoneCustomer,emailCustomer,addressCustomer;

    private Button btnSent,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_info);
        mapping();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBtnSent();
            }
        });
    }
    public void mapping(){
        nameCustomer = findViewById(R.id.nameCustomer);
        phoneCustomer = findViewById(R.id.phoneCustomer);
        emailCustomer = findViewById(R.id.emailCustomer);
        addressCustomer = findViewById(R.id.addressCustomer);
        btnSent = findViewById(R.id.btnSent);
        btnBack = findViewById(R.id.btnBack);
    }


    public  void handleBtnSent(){
        final String name  = nameCustomer.getText().toString().trim();
        final String email = emailCustomer.getText().toString().trim();
        final String address = addressCustomer.getText().toString().trim();
        final String phone = phoneCustomer.getText().toString().trim();
        if(validate(name,email,address,phone) == false) return;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.postInformation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null ){
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                    StringRequest request = new StringRequest(Request.Method.POST, helper.insertDataCartDetail, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                AppActivity.cart.clear();
                                Intent intent = new Intent(getApplicationContext(),AppActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Thanks u so muck",Toast.LENGTH_LONG).show();

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
                                    jsonObject.put("idCart",response);
                                    jsonObject.put("idProduct",AppActivity.cart.get(i).get_idProduct());
                                    jsonObject.put("nameProduct",AppActivity.cart.get(i).getNameProduct());
                                    jsonObject.put("priceProduct",AppActivity.cart.get(i).getPrice());
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("nameCustomer",name);
                hashMap.put("phoneCustomer",phone);
                hashMap.put("emailCustomer",email);
                hashMap.put("addressCustomer",address);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    public boolean validate(String name,String email,String address,String phone){
        if( name.length() == 0 || email.length() == 0 || address.length() == 0 || phone.length() == 0){
            Toast.makeText(this,"Empty! all is required",Toast.LENGTH_LONG).show();
            return false;
        };
        if(name.length()<6){
            Toast.makeText(this,"Name < 6",Toast.LENGTH_LONG).show();
            return false;
        }
        if(PhoneNumberUtils.isGlobalPhoneNumber(phone)==false){
            Toast.makeText(this,"Not Phone",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!isEmailValid(email)){
            Toast.makeText(this,"Not Email",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}