package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.R;

import java.util.ArrayList;
import java.util.List;

import Model.ListProduct;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductViewHolder> implements Filterable {
    private List<ListProduct> listProducts;
    private List<ListProduct> listProductsSearch;
    private Context context;

    public void setData(List<ListProduct> listProducts){
        this.listProducts = listProducts;
        this.listProductsSearch = listProductsSearch;
        notifyDataSetChanged();
    }

    public  ListProductAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pupular_list,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            ListProduct listProduct = listProducts.get(position);
            if(listProduct == null) return;
            holder.title_product.setText(listProduct.getTitle());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
            holder.rcvProductList.setLayoutManager(linearLayoutManager);
            ProductAdapter productAdapter = new ProductAdapter(context);
            productAdapter.setData(listProduct.getListProducts());
            holder.rcvProductList.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        if(listProducts != null) return listProducts.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
    }

    public  class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView title_product;
        private RecyclerView rcvProductList;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title_product = itemView.findViewById(R.id.title_popularList);
            rcvProductList = itemView.findViewById(R.id.rcv_popularList);
        }
    }
}
