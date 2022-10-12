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

import com.example.projecthomestay.Activity.DetailNew;
import com.example.projecthomestay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.New;
import Model.product;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewHolderView> implements Filterable {
    private List<New> news;
    private List<New> newsSearch;
    public Context context;

    public  NewAdapter (Context context){
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
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new,parent,false);
        return new NewHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolderView holder, @SuppressLint("RecyclerView") int position) {
        New new1 = news.get(position);
        holder.authorNew.setText(new1.getAuthorNew());
        holder.desNew.setText(new1.getDescriptionNew());
        holder.nameNew.setText(new1.getNameView());
        Picasso.with(context).load(new1.getImgNew())
                .error(R.drawable.ic_baseline_wifi_24)
                .placeholder(R.drawable.ic_baseline_wifi_24)
                .into(holder.imgNew);
        holder.btn_newdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailNew.class);
                intent.putExtra("object",news.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.news != null) return news.size();
        return 0;
    }

    public class NewHolderView extends RecyclerView.ViewHolder{
        ImageView imgNew;
        Button btn_newdetail;
        TextView nameNew,desNew,authorNew;
        public NewHolderView(@NonNull View itemView) {
            super(itemView);
            imgNew = itemView.findViewById(R.id.img_new);
            btn_newdetail = itemView.findViewById(R.id.btn_opendetailnew);
            nameNew = itemView.findViewById(R.id.name_new);
            desNew = itemView.findViewById(R.id.desc_new);
            authorNew = itemView.findViewById(R.id.author_new);
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
