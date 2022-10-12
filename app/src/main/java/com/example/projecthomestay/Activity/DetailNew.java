package com.example.projecthomestay.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import Model.New;
import Model.product;

public class DetailNew extends AppCompatActivity {
    private Button btnBack;
    private TextView nameNew,descNew,authorNew;
    private ImageView imgNew;
    private New obj;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_new);
        mapping();
        bandle();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void mapping(){
        btnBack = findViewById(R.id.back_news);
        nameNew = findViewById(R.id.nameNew);
        descNew = findViewById(R.id.descNew);
        authorNew = findViewById(R.id.authorNew);
        imgNew = findViewById(R.id.imgNew);
    }

    public void bandle(){
        obj = (New)getIntent().getSerializableExtra("object");
        nameNew.setText(obj.getNameView());
        descNew.setText("Description :" + obj.getDescriptionNew());
        authorNew.setText("Author of new : " + obj.getAuthorNew());

        Picasso.with(context).load(obj.getImgNew())
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .error(R.drawable.ic_baseline_wifi_24)
                .into(imgNew);
    }
}