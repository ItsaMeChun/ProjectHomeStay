package com.example.projecthomestay.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import Model.Cart;
import Model.product;


public class ShowDetailHomeStay extends AppCompatActivity {

    private TextView txtName;
    private TextView location;
    private ImageView imgView;
    private TextView desc;
    private TextView categoryName;
    private TextView price;
    private Button backHome;
    private Button addToCart;
    private product object;
    Context context;

    int _idProduct;
    int priceProduct;
    String name;
    String imgProduct;
    String nameProduct;
    String locationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_home_stay);
        mappingView();
        bandle();

    }

    public void mappingView(){
        txtName = findViewById(R.id.title_detail);
        location = findViewById(R.id.location_detail);
        imgView = findViewById(R.id.img_product_detailt);
        desc = findViewById(R.id.desc_detail);
        categoryName = findViewById(R.id.categoriesID_detail);
        price = findViewById(R.id.price_detail);
        backHome = findViewById(R.id.ButtonBackHome);
        addToCart = findViewById(R.id.addCartDetail);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object = (product)getIntent().getSerializableExtra("object");
                Boolean isSucces = false;
                if(AppActivity.cart.size() >0){
                    for(int i = 0;i < AppActivity.cart.size();i++){
                        if(AppActivity.cart.get(i).get_idProduct() == object.get_id()){
                            AppActivity.cart.get(i).setNumber(AppActivity.cart.get(i).getNumber() + 1);
                            AppActivity.cart.get(i).setPrice(AppActivity.cart.get(i).getNumber()*AppActivity.cart.get(i).getPrice());
                            isSucces = true;
                        }

                    }
                    if(isSucces == false){
                        _idProduct = object.get_id();
                        priceProduct = object.getPrice();
                        imgProduct = object.getImgProduct();
                        nameProduct = object.getNameProduct();
                        locationString = object.getLocation();
                        AppActivity.cart.add(new Cart(_idProduct,nameProduct,priceProduct,imgProduct,1,locationString));
                    }
                }else{
                    _idProduct = object.get_id();
                    priceProduct = object.getPrice();
                    imgProduct = object.getImgProduct();
                    nameProduct = object.getNameProduct();
                    locationString = object.getLocation();
                    AppActivity.cart.add(new Cart(_idProduct,nameProduct,priceProduct,imgProduct,1,locationString));
                }

                Toast.makeText(ShowDetailHomeStay.this,"Add product Success",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void bandle(){
        object = (product)getIntent().getSerializableExtra("object");
        Picasso.with(context).load(object.getImgProduct())
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .error(R.drawable.ic_baseline_wifi_24)
                .into(imgView);

        desc.setText(object.getDescription());
        txtName.setText(object.getNameProduct());
        location.setText(object.getLocation());
        price.setText("$"+ object.getPrice());
        categoryName.setText(object.getNameProduct());
    }

}