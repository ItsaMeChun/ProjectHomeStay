package com.example.projecthomestay.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.List;

import Adapter.CategoryAdapter;
import Adapter.ListProductAdapter;
import Category.ListCategoryAdapter;
import Category.categoryA;
import Helper.helper;
import Model.Category;
import Model.ListProduct;
import Model.product;
import Popular.Popular;
import Product.Product;
import Adapter.ProductAdapter;
import homestay.homestay;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnShowDetail;
    Context thiscontext;
    private ArrayList<Category> categoryList;
    private CategoryAdapter categoryAdapterDemo;
    private EditText search;
    private TextView userName;
    private Button btnLogin,btnLogout;
    private ImageView btnSetting;


    private RecyclerView recyclerViewCategoryList,recyclerViewProductList;
    private ListCategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    private ArrayList<product> productList;
    private ArrayList<ListProduct> productListMD;
    private ListProductAdapter productAdapterDemo;

    String nameProduct ="";
    int price = 0;
    String description = "";
    String imgProduct = "";
    int _idCategory;
    String location="";

    int _id = 0;
    String nameCategory ="";
    String imgCategory ="";

    public HomePage() {
        // Required empty public constructor

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
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


        categoryList = new ArrayList<>();
        productList= new ArrayList<>();
        productListMD = new ArrayList<>();
        getDataCategory();
        getDataListProduct();
        Context thiscontext = container.getContext();
        View view =  inflater.inflate(R.layout.fragment_home_page, container, false);

        userName = view.findViewById(R.id.txtUserName);
        if(AppActivity.userlogin != null){
            userName.setText(AppActivity.userlogin.getUserName());
        }else{
            userName.setText("Welcome to Booking Homestay");
        }

        recyclerViewCategoryList = view.findViewById(R.id.categories);
        categoryAdapterDemo = new CategoryAdapter(thiscontext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(thiscontext, RecyclerView.HORIZONTAL , false);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        categoryAdapterDemo.setData(categoryList);
        recyclerViewCategoryList.setAdapter(categoryAdapterDemo);

        recyclerViewProductList = view.findViewById(R.id.product_rec);
        productAdapterDemo = new ListProductAdapter(thiscontext);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(thiscontext, RecyclerView.HORIZONTAL , false);
        recyclerViewProductList.setLayoutManager(linearLayoutManager1);
        productAdapterDemo.setData(productListMD);
        recyclerViewProductList.setAdapter(productAdapterDemo);

        btnSetting = view.findViewById(R.id.btnSetting);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!AppActivity.isLogging){
                    Toast.makeText(thiscontext,"You need login to use this function", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(thiscontext,settingsPage.class);
                    startActivity(intent);
                }

            }
        });


        search = view.findViewById(R.id.search_homestay);
        search.setVisibility(View.GONE);
        final CharSequence[] searchStr = {""};
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                categoryAdapterDemo.getFilter().filter(charSequence);
                searchStr[0] = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        btnLogin = view.findViewById(R.id.titleSub);
        btnLogout = view.findViewById(R.id.order);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!AppActivity.isLogging){
                    Toast.makeText(thiscontext,"Not Logging", Toast.LENGTH_LONG).show();
                }else{
                    AppActivity.isLogging = false;
                    AppActivity.userlogin = null;
                    userName.setText("Welcome to booking homestay");
                    Toast.makeText(thiscontext,"Success Logout", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppActivity.isLogging){
                    Toast.makeText(thiscontext,"isLogging", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(thiscontext,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private  void getDataListProduct(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(helper.getProduct, new Response.Listener<JSONArray>() {
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
        productListMD.add(new ListProduct(productList,"New homestay in app"));
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataCategory(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(helper.getCategory, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i=0; i<response.length();i++){
                        try{
                            JSONObject jsonObject = response.getJSONObject(i);
                            _id = jsonObject.getInt("_id");
                            nameCategory = jsonObject.getString("nameCategory");
                            imgCategory = jsonObject.getString("imgCategory");
                            categoryList.add(new Category(_id,nameCategory,imgCategory));

                            categoryAdapterDemo.notifyDataSetChanged();

                        }catch (Exception e){

                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }


}