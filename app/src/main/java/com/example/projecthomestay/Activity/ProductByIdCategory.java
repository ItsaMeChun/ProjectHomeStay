package com.example.projecthomestay.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.ListProductAdapter;
import Adapter.ProductAdapter;
import Helper.PaginationScrollListener;
import Helper.helper;
import Model.ListProduct;
import Model.product;

public class ProductByIdCategory extends AppCompatActivity {

    private ArrayList<product> productList;
    private ArrayList<product> mproductList;
    private ArrayList<ListProduct> productListMD;
    private ProductAdapter productAdapterDemo;
    private RecyclerView recyclerViewCategoryList,recyclerViewProductList;
    private View fetchData;
    private ProgressBar prb;
    private EditText search;
    CharSequence searchStr = " ";


    private int _idCategoryPOST = 0;
    private int page = 1;
    private int page2 = 2;

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
        productList= new ArrayList<>();
        productListMD = new ArrayList<>();
        setContentView(R.layout.activity_product_by_id_category);
        mapping();
        getIdCategory();

        productAdapterDemo = new ProductAdapter(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL , false);
        recyclerViewProductList.setLayoutManager(linearLayoutManager1);
        getDataProductById();
        productAdapterDemo.setData(productList);
        recyclerViewProductList.setAdapter(productAdapterDemo);



        recyclerViewProductList.addOnScrollListener(new PaginationScrollListener(linearLayoutManager1) {
            @Override
            public void loadMoreItems() {
//                isLoading = true;
//                prb.setVisibility(View.VISIBLE);
//                page++;
//                currentPage++;
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


        searchProduct();

    }

    public  void loadNextPage(int page){
        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                productList.addAll(getDataProductById());
                productAdapterDemo.notifyDataSetChanged();
                isLoading = false;
                prb.setVisibility(View.GONE);
                if(currentPage == totalPage){
                    Toast.makeText(getApplicationContext(),"End product in shop", Toast.LENGTH_LONG).show();
                    isLastPage = true;
                }
            }
        },2000);
    }


    private void mapping() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        fetchData = inflater.inflate(R.layout.process_bar,null);
        recyclerViewProductList = findViewById(R.id.recv_product_page);
        prb = findViewById(R.id.processBar1);
        search = findViewById(R.id.searchProductById);
    }

    private void getIdCategory() {
        _idCategoryPOST = getIntent().getIntExtra("_id",2);
    }




    public ArrayList<product> getDataProductById(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = helper.getGetProductById;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               try{
                   JSONArray jsonArray = new JSONArray(response);
                   for (int i = 0; i < response.length(); i++) {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       _id = jsonObject.getInt("_id");
                       nameProduct = jsonObject.getString("nameProduct");
                       price = jsonObject.getInt("price");
                       description = jsonObject.getString("description");
                       imgProduct = jsonObject.getString("imgProduct");
                       _idCategory = jsonObject.getInt("_idCategory");
                       location= jsonObject.getString("location");

                       productList.add(new product(_id,nameProduct,price,description,imgProduct,_idCategory,location));

                   }
               }catch (Exception e){

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
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("_id",String.valueOf(_idCategoryPOST));
                return param;
            }
        };
        productAdapterDemo.notifyDataSetChanged();
        requestQueue.add(stringRequest);
        return productList;
    }

    public  void searchProduct(){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productAdapterDemo.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}