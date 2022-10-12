package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.Activity.ShowDetailHomeStay;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.product;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {
    private List<product> products;
    private List<product> productsSearch;
    Context context;

    public ProductAdapter(Context thiscontext) {
        this.context = thiscontext;
    }

    public void setData(List<product> products){
        this.products = products;
        this.productsSearch = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        product product = products.get(position);
        holder.nameProduct.setText(product.getNameProduct());
        holder.location.setText(product.getLocation());
        holder.price.setText("$"+product.getPrice());
        Picasso.with(context).load(product.getImgProduct())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgProduct);
        holder.btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailHomeStay.class);
                intent.putExtra("object",products.get(position));
                holder.itemView.getContext().startActivity(intent);
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

    public class  ProductViewHolder extends RecyclerView.ViewHolder{
        TextView nameProduct;
        TextView location;
        ImageView imgProduct;
        Button btnShowDetail;
        TextView price;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct = itemView.findViewById(R.id.title_popular);
            location = itemView.findViewById(R.id.location_popular);
            imgProduct = itemView.findViewById(R.id.img_popular);
            btnShowDetail = itemView.findViewById(R.id.showDetail1);
            price = itemView.findViewById(R.id.priceProduct);
        }
    }
}
