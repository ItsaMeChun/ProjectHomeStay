package com.example.projecthomestay.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.projecthomestay.R;

import java.util.ArrayList;

import Adapter.NewsServiceAdapter;
import Model.Category;
import Model.New;

public class editCategory extends AppCompatActivity {
    private Button btnBack;
    private RecyclerView recyclerView;
    private ArrayList<Category> newsList = new ArrayList<>();
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
        setContentView(R.layout.activity_edit_category);
    }
}