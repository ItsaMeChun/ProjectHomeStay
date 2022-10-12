package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.Activity.LoginActivity;
import com.example.projecthomestay.Activity.ProductByIdCategory;
import com.example.projecthomestay.Activity.ProductPage;
import com.example.projecthomestay.Activity.ShowDetailHomeStay;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Model.Category;
import homestay.homestay;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> implements Filterable
{
    private List<Category> categories;
    private List<Category> searchCategories;
    Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Category> categories){
        this.categories = categories;
        this.searchCategories = categories;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_mathparent,parent,false);
        return  new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = categories.get(position);
        if(category == null) return;
        holder.homestayName.setText(category.getNameCategory());
        Picasso.with(context).load(category.getImgCategory())
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .error(R.drawable.ic_baseline_wifi_24)
                .into(holder.homestayImg);
        holder.btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductByIdCategory.class);
                intent.putExtra("_id",categories.get(position).get_id());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }



        @Override
    public int getItemCount() {
        if(categories != null) return  categories.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    categories = searchCategories;
                }else{
                    List<Category> list = new ArrayList<>();
                    for(Category cate : searchCategories){
                        if(cate.getNameCategory().toLowerCase().contains(search.toLowerCase())){
                            list.add(cate);
                        }
                    }
                    categories = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = categories;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                categories =(List<Category>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        TextView homestayName;
        ImageView homestayImg;
        Button btn_right;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            homestayName = itemView.findViewById(R.id.name_category);
            homestayImg = itemView.findViewById(R.id.img_category);
            btn_right = itemView.findViewById(R.id.showDetail);
        }
    }
}
