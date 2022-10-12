package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.projecthomestay.Activity.AppActivity;
import com.example.projecthomestay.Activity.CustomerPage;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private ArrayList<Cart> carts;
    public Context context;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    public CartAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<Cart> carts){
        this.carts = carts;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = carts.get(position);
        holder.priceItemCart.setText("$" + cart.getPrice());
        holder.nameItemCart.setText(cart.getNameProduct());
        holder.SL.setText("SL : " + cart.getNumber() + "=");
        Picasso.with(context).load(cart.getImgProduct())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgViewCart);
        holder.location.setText(cart.getPosition());
        viewBinderHelper.bind(holder.swipeRevealLayout,String.valueOf(cart.get_idProduct()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carts.remove(holder.getAbsoluteAdapterPosition());
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
                CustomerPage.setTongTien();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(carts != null) return carts.size();
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewCart;
        TextView nameItemCart;
        TextView SL;
        TextView priceItemCart;
        SwipeRevealLayout swipeRevealLayout;
        View delete;
        TextView location;
        public CartViewHolder(@NonNull View itemView) {

            super(itemView);
            imgViewCart = itemView.findViewById(R.id.img_cart_item);
            nameItemCart = itemView.findViewById(R.id.title_item_cart);
            SL = itemView.findViewById(R.id.sl);
            priceItemCart = itemView.findViewById(R.id.price_cart);
            swipeRevealLayout = itemView.findViewById(R.id.swipe);
            delete = itemView.findViewById(R.id.delete_cart_item);
            location = itemView.findViewById(R.id.location_popular);
        }
    }
}
