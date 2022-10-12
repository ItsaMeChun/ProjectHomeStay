package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecthomestay.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.ServicesAdapter;
import Helper.PaginationScrollListener;
import Helper.helper;
import Model.product;

public class services extends AppCompatActivity {
    private Button btnBack;
    private RecyclerView recyclerView;
    private ArrayList<product> productList;
    private ServicesAdapter servicesAdapter;
    private EditText searchTxt;
    private CharSequence charSequence = "";

    private int page = 1;

    private String nameProduct ="";
    private int price = 0;
    private String description = "";
    private String imgProduct = "";
    private int _idCategory;
    private String location="";

    int _id = 0;

    private  boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        mapping();
        handleBtnClick();
        productList = new ArrayList<>();
        servicesAdapter = new ServicesAdapter(getApplicationContext());
        getDataListProduct(page);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        servicesAdapter.setData(productList);
        recyclerView.setAdapter(servicesAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager1) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                page++;
                currentPage++;
//                loadNextPage(page);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                servicesAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void mapping(){
        btnBack = findViewById(R.id.btnServices_back);
        recyclerView = findViewById(R.id.rec_services);
        searchTxt = findViewById(R.id.search_product_services);
    }

    private  ArrayList<product> getDataListProduct(int page){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = helper.getAllProduct + String.valueOf(page);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        _id = jsonObject.getInt("_id");
                        nameProduct = jsonObject.getString("nameProduct");
                        price = jsonObject.getInt("price");
                        description = jsonObject.getString("description");
                        imgProduct = jsonObject.getString("imgProduct");
                        _idCategory = jsonObject.getInt("_idCategory");
                        location= jsonObject.getString("location");

                        productList.add(new product(_id,nameProduct,price,description,imgProduct,_idCategory,location));
                        servicesAdapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return productList;
    }
    public  void loadNextPage(int page){
        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                productList.addAll(getDataListProduct(page));
                servicesAdapter.notifyDataSetChanged();
                isLoading = false;
                Toast.makeText(getApplicationContext(),"LaodData",Toast.LENGTH_LONG).show();
                if(currentPage == totalPage){
                    isLastPage = true;
                }
            }
        },2000);
    }

    public void handleBtnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}