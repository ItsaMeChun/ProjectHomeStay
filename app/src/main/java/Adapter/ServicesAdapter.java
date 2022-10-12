package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.projecthomestay.Activity.updateProduct;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Helper.helper;
import Model.product;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.holderServices> implements Filterable {
    private List<product> products;
    private List<product> productsSearch;
    Context context;

    public ServicesAdapter(Context thiscontext) {
        this.context = thiscontext;
    }

    public void setData(List<product> products){
        this.products = products;
        this.productsSearch = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public holderServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_update_delete,parent,false);
        return new holderServices(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderServices holder, @SuppressLint("RecyclerView") int position) {
        product product = products.get(position);
        holder.nameService.setText(product.getNameProduct());
        holder.priceService.setText("$"+product.getPrice());
        Picasso.with(context).load(product.getImgProduct())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgService);
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), updateProduct.class);
                intent.putExtra("object", products.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, helper.removeProduct, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success")){
                            Toast.makeText(context.getApplicationContext(),"Delete Success" + response,Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                        }
                        if(response.equals("false")){
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
                        hashMap.put("_id",String.valueOf(product.get_id()));
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
                products.remove(product);
                notifyDataSetChanged();
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(products != null) return products.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    products = productsSearch;
                }else {
                    List<product> list = new ArrayList<>();
                    for (product product: products){
                        if(product.getNameProduct().toLowerCase().contains(search.toLowerCase())){
                            list.add(product);
                        }
                    }
                    products = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = products;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                products = (List<product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class holderServices extends RecyclerView.ViewHolder{
        private TextView btnDelete,btnUpdate;
        private ImageView imgService;
        private TextView nameService,priceService;
        public holderServices(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.deleteProductServices);
            btnUpdate = itemView.findViewById(R.id.updateProductServices);
            imgService = itemView.findViewById(R.id.img_product_services);
            nameService = itemView.findViewById(R.id.nameProductServices);
            priceService = itemView.findViewById(R.id.priceProductServices);
        }
    }
}
