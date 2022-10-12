package com.example.projecthomestay.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.CartAdapter;
import Helper.helper;
import Model.Cart;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewCartList;
    public static   TextView priceAll;
    private Button btnCheckOut;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> carts;
    public  static int priceAll1 ;

   Button btnBuyLogin;
   Button btnInfo;




    public CustomerPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerPage newInstance(String param1, String param2) {
        CustomerPage fragment = new CustomerPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context thiscontext = container.getContext();
        View view = inflater.inflate(R.layout.fragment_customer_page, container, false);
        recyclerViewCartList = view.findViewById(R.id.rcv_cart);
        priceAll = view.findViewById(R.id.priceALL);
        btnCheckOut = view.findViewById(R.id.btn_checkout);
        cartAdapter = new CartAdapter(thiscontext);
        cartAdapter.setData(AppActivity.cart);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(thiscontext, RecyclerView.VERTICAL , false);
        recyclerViewCartList.setLayoutManager(linearLayoutManager1);
        recyclerViewCartList.setAdapter(cartAdapter);

        for(int i =0 ;i< AppActivity.cart.size(); i ++){
            priceAll1 += AppActivity.cart.get(i).getPrice();
        }
        setTongTien();



        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                if(AppActivity.cart.size() == 0){
                    Toast.makeText(thiscontext,"Not product",Toast.LENGTH_LONG).show();
                }else{
                    if(!AppActivity.isLogging){
                        openDiaglog(Gravity.CENTER,thiscontext);
                    }else{
                        postDataToServer(thiscontext);
                    }
                }
            }
        });
        return view;
    }

    private void openDiaglog(int bottom,Context thiscontext) {
        final Dialog dialog = new Dialog(thiscontext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        Window window = dialog.getWindow();
        if(window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = bottom;
        window.setAttributes(windowAttr);

        if(Gravity.CENTER == bottom){
            dialog.setCancelable(true);
        }

        btnBuyLogin = dialog.findViewById(R.id.Login_buy);
        btnInfo = dialog.findViewById(R.id.send_buy);
        btnBuyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(),Activity_buyInfo.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    public static void setTongTien(){
        priceAll1 = 0;
        for(int i =0 ;i< AppActivity.cart.size(); i ++){
            priceAll1 += AppActivity.cart.get(i).getPrice();
        }
        priceAll.setText("$" + priceAll1);
    }

    public  void postDataToServer(Context thiscontext) {
        RequestQueue requestQueue1 = Volley.newRequestQueue(thiscontext);
        StringRequest request = new StringRequest(Request.Method.POST, helper.addCatertLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success")){
                    AppActivity.cart.clear();
                    Intent intent = new Intent(thiscontext,AppActivity.class);
                    startActivity(intent);
                    Toast.makeText(thiscontext,"Thanks you for booking",Toast.LENGTH_LONG).show();
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