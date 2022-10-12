package com.example.projecthomestay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import Category.ListCategoryAdapter;
import Category.categoryA;
import Model.Cart;
import Model.User;
import Popular.Popular;
import Product.Product;
import Product.ProductAdapter;
import homestay.homestay;


public class AppActivity extends AppCompatActivity  {

    public static ArrayList<Cart> cart;
    public static boolean isLogging ;
    public static User userlogin ;
    private RecyclerView recyclerViewCategoryList,recyclerViewProductList;
    private ListCategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private Button btnShowDetail;

    private BottomNavigationView navigationView;

    public void mapping(){
        navigationView = findViewById(R.id.navigationBottom);
        navigationView.setSelectedItemId(R.id.homepage);
        if(cart != null){

        }else {
            cart = new ArrayList<>();
        }
        if(isLogging == true){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        mapping();


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomePage()).commit();


        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.homepage:
                        selectedFragment = new HomePage();
                        navigationView.setBackgroundResource(R.drawable.bg_pink);
                        break;
                    case R.id.productpage:
                        selectedFragment = new ProductPage();
                        navigationView.setBackgroundResource(R.drawable.bg_pink);
                        break;
                    case R.id.searchpage:
                        selectedFragment = new searchPage();
                        navigationView.setBackgroundResource(R.drawable.bg_pink);
                        break;
                    case R.id.customerpage:
                        selectedFragment = new CustomerPage();
                        navigationView.setBackgroundResource(R.drawable.bg_pink);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,
                        selectedFragment).commit();

                return true;
            }
        });



    }


}

