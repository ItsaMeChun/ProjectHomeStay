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
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Helper.helper;
import Model.Category;
import Model.New;

public class CategoryServiceApdapter extends RecyclerView.Adapter<CategoryServiceApdapter.CategoryHolderView> implements Filterable {

    private List<Category> cat;
    private List<Category> catSearch;
    public Context context;

    public CategoryServiceApdapter(Context context){
        this.context = context;
    }
    public void setData(List<Category> cat){
        this.cat = cat;
        this.catSearch = cat;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_update_delete,parent,false);
        return new CategoryHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolderView holder, @SuppressLint("RecyclerView") int position) {
        Category category = cat.get(position);
        holder.nameCat.setText(category.getNameCategory());
        Picasso.with(context).load(category.getImgCategory())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgCat);
//        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context.getApplicationContext(), updateNews.class);
//                intent.putExtra("object", cat.get(position));
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });

//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.removeCategory, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.equals("success")){
//                            Toast.makeText(context.getApplicationContext(),"Delete News Success" + response,Toast.LENGTH_LONG).show();
//                            notifyDataSetChanged();
//                        }
//                        if(response.equals("Failure")){
//                            Toast.makeText(context.getApplicationContext(), "Fail" + response,Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//                    @Nullable
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        HashMap<String,String> hashMap = new HashMap<String,String>();
//                        hashMap.put("_id",String.valueOf(category.get_id()));
//                        return hashMap;
//                    }
//                };
//                requestQueue.add(stringRequest);
//                cat.remove(category);
//                notifyDataSetChanged();
//                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
//            }
//        });
    }


    @Override
    public int getItemCount() {
        if(this.cat != null) return cat.size();
        return 0;
    }



    public class CategoryHolderView extends RecyclerView.ViewHolder{
        private TextView btnDelete,btnUpdate;
        private ImageView imgCat;
        private TextView nameCat;
        public CategoryHolderView(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.deleteCategoryServices);
            btnUpdate = itemView.findViewById(R.id.updateCategoryServices);
            imgCat = itemView.findViewById(R.id.img_cat_services);
            nameCat = itemView.findViewById(R.id.nameCatServices);
        }
    }


    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    cat = catSearch;
                }else {
                    List<Category> list = new ArrayList<>();
                    for (Category category: cat){
                        if(category.getNameCategory().toLowerCase().contains(search.toLowerCase())){
                            list.add(category);
                        }
                    }
                    cat = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = cat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cat = (List<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
