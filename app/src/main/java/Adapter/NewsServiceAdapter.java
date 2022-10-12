package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecthomestay.Activity.updateNews;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Helper.helper;
import Model.New;

public class NewsServiceAdapter extends RecyclerView.Adapter<NewsServiceAdapter.NewHolderView> implements Filterable {
    private List<New> news;
    private List<New> newsSearch;
    public Context context;

    public NewsServiceAdapter(Context context){
        this.context = context;
    }
    public void setData(List<New> news){
        this.news = news;
        this.newsSearch = news;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_update_delete,parent,false);
        return new NewHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolderView holder, @SuppressLint("RecyclerView") int position) {
        New new1 = news.get(position);
        holder.authorNew.setText(new1.getAuthorNew());
        holder.nameNew.setText(new1.getNameView());
        Picasso.with(context).load(new1.getImgNew())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgNew);
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), updateNews.class);
                intent.putExtra("object", news.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.removeNews, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success")){
                            Toast.makeText(context.getApplicationContext(),"Delete News Success" + response,Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                        }
                        if(response.equals("Failure")){
                            Toast.makeText(context.getApplicationContext(), "Fail" + response,Toast.LENGTH_LONG).show();
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
                        HashMap<String,String> hashMap = new HashMap<String,String>();
                        hashMap.put("idNew",String.valueOf(new1.getIdNew()));
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
                news.remove(new1);
                notifyDataSetChanged();
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.news != null) return news.size();
        return 0;
    }

    public class NewHolderView extends RecyclerView.ViewHolder{
        private TextView btnDelete,btnUpdate;
        private ImageView imgNew;
        private TextView nameNew,authorNew;
        public NewHolderView(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.deleteNewsServices);
            btnUpdate = itemView.findViewById(R.id.updateNewsServices);
            imgNew = itemView.findViewById(R.id.img_news_services);
            nameNew = itemView.findViewById(R.id.nameNewsServices);
            authorNew = itemView.findViewById(R.id.authorNewsServices);
        }
    }

    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    news = newsSearch;
                }else {
                    List<New> list = new ArrayList<>();
                    for (New New: news){
                        if(New.getNameView().toLowerCase().contains(search.toLowerCase())){
                            list.add(New);
                        }
                    }
                    news = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = news;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                news = (List<New>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
