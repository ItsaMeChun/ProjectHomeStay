package com.example.projecthomestay.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import Adapter.ProductAdapter;
import Helper.PaginationScrollListener;
import Helper.helper;
import Model.ListProduct;
import Model.product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<product> productList;
    private ArrayList<product> mproductList;
    private ArrayList<ListProduct> productListMD;
    private ProductAdapter productAdapterDemo;
    private RecyclerView recyclerViewCategoryList,recyclerViewProductList;
    private EditText search;
    private View fetchData;
    private ProgressBar prb;

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

    public ProductPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductPage newInstance(String param1, String param2) {
        ProductPage fragment = new ProductPage();
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
        View view = inflater.inflate(R.layout.fragment_product_page, container, false);
        productList= new ArrayList<>();
        Context thiscontext = container.getContext();
        getDataListProduct(page);
        recyclerViewProductList = view.findViewById(R.id.recv_all_product_page);

        productAdapterDemo = new ProductAdapter(thiscontext);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(thiscontext, RecyclerView.VERTICAL , false);
        recyclerViewProductList.setLayoutManager(linearLayoutManager1);
        productAdapterDemo.setData(productList);
        recyclerViewProductList.setAdapter(productAdapterDemo);

        recyclerViewProductList.addOnScrollListener(new PaginationScrollListener(linearLayoutManager1) {
            @Override
            public void loadMoreItems() {
//                isLoading = true;
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

        search = view.findViewById(R.id.search_product);
        //final CharSequence[] searchStr = {""};

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productAdapterDemo.getFilter().filter(charSequence);
                //searchStr[0] = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private  ArrayList<product> getDataListProduct(int page){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
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
                        productAdapterDemo.notifyDataSetChanged();
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
    public  void loadNextPage(int pageLoad){
        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                productList.addAll(getDataListProduct(pageLoad));
                productAdapterDemo.notifyDataSetChanged();
                isLoading = false;
                Toast.makeText(getContext().getApplicationContext(),"LaodData",Toast.LENGTH_LONG).show();
                if(currentPage == totalPage){
                    isLastPage = true;
                }
            }
        },2000);
    }

}