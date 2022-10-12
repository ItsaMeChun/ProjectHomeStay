package com.example.projecthomestay.Activity;

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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecthomestay.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.NewsServiceAdapter;
import Helper.PaginationScrollListener;
import Helper.helper;
import Model.New;

public class editnews extends AppCompatActivity {
    private Button btnBack;
    private RecyclerView recyclerView;
    private ArrayList<New> newsList = new ArrayList<>();
    private NewsServiceAdapter newAdapter;
    private EditText searchTxt;
    private CharSequence charSequence = "";

    private int page = 1;

    private String nameNew ="";
    private String descriptionNew = "";
    private String imgNew = "";
    private String authorNew="";

    int idNew = 0;

    private  boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnews);
        mapping();
        handleBtnClick();
        newAdapter = new NewsServiceAdapter(getApplicationContext());
        getDataListNews(page);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        newAdapter.setData(newsList);
        recyclerView.setAdapter(newAdapter);

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
                newAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void mapping(){
        btnBack = findViewById(R.id.btn_News_Services_back);
        recyclerView = findViewById(R.id.rec_news_services);
        searchTxt = findViewById(R.id.search_news_services);
    }

    public ArrayList<New> getDataListNews(int page){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = helper.getAllNews + String.valueOf(page);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idNew = jsonObject.getInt("idNew");
                        nameNew = jsonObject.getString("nameNew");
                        descriptionNew = jsonObject.getString("descriptionNew");
                        imgNew = jsonObject.getString("imgNew");
                        authorNew = jsonObject.getString("authorNew");
                        newsList.add(new New(idNew,nameNew,descriptionNew,authorNew,imgNew));
                        newAdapter.notifyDataSetChanged();

                    }catch (Exception e){

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        return newsList;
    }




    public void handleBtnClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //getDataListNews

}
























































