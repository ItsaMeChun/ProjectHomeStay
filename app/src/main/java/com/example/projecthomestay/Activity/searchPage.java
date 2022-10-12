package com.example.projecthomestay.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import Adapter.NewAdapter;
import Helper.PaginationScrollListener;
import Helper.helper;
import Model.New;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView recyclerView;
    private ArrayList<New> news = new ArrayList<>();
    private NewAdapter newAdapter;
    private Button addProduct,addCategory,addNew,aboutUs;
    private TextView next,userName;
    private EditText search;
    private int page = 1;
    private int idNew = 0;
    private String nameNew = "";
    private String descriptionNew = "";
    private String authorNew = "";
    private String imgNew = "";

    private  boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 2;
    public searchPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchPage.
     */
    // TODO: Rename and change types and number of parameters
    public static searchPage newInstance(String param1, String param2) {
        searchPage fragment = new searchPage();
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
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);
        Context thiscontext = container.getContext();

        getDataNews(page);
        recyclerView = view.findViewById(R.id.new_rec);
        search = view.findViewById(R.id.search_news_by_id);
        newAdapter = new NewAdapter(thiscontext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(thiscontext, RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        newAdapter.setData(news);
        recyclerView.setAdapter(newAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                page++;
                currentPage++;
                //loadNextPage(page);
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

        searchNews();

        return view;
    }
    public ArrayList<New> getDataNews(int page){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
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
                        news.add(new New(idNew,nameNew,descriptionNew,authorNew,imgNew));
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
       return news;
    }
    public  void loadNextPage(int page){
        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                news.addAll(getDataNews(page));
                newAdapter.notifyDataSetChanged();
                isLoading = false;
                Toast.makeText(getContext().getApplicationContext(),"LoadData",Toast.LENGTH_LONG).show();
                if(currentPage == totalPage){
                    isLastPage = true;
                }
            }
        },2000);
    }


    public  void searchNews(){
        search.addTextChangedListener(new TextWatcher() {
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

}