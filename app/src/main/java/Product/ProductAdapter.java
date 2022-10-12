package Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.R;

import java.util.List;


import Popular.PopularAdaptor;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> listProduct;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> listProduct){
        this.listProduct = listProduct;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pupular_list,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = listProduct.get(position);
        if(product==null) return;
        holder.title_product.setText(product.getTitle_popularList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcvProductList.setLayoutManager(linearLayoutManager);

        PopularAdaptor popularAdaptor = new PopularAdaptor();
        popularAdaptor.setData(product.getList_popular());
        holder.rcvProductList.setAdapter(popularAdaptor);
    }

    @Override
    public int getItemCount() {
        if(listProduct != null) return listProduct.size();
        return 0;
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
