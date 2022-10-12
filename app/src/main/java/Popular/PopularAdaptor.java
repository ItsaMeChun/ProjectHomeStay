package Popular;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthomestay.Activity.ShowDetailHomeStay;
import com.example.projecthomestay.R;

import java.util.List;


public class PopularAdaptor extends  RecyclerView.Adapter<PopularAdaptor.PopularViewHolder>{

    private List<Popular> populars;

    public  void setData(List<Popular> populars){
        this.populars = populars;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        return  new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Popular popular = populars.get(position);
        if(popular == null) return;
        holder.imgPopular.setImageResource(popular.getResourceId());
        holder.titlePopular.setText(popular.getTitle());
        holder.descPopular.setText(popular.getDesc());

        holder.btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailHomeStay.class);
                intent.putExtra("object",populars.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(populars != null) return populars.size();
        return 0;
    }

    public class  PopularViewHolder extends RecyclerView.ViewHolder{
        TextView titlePopular;
        TextView descPopular;
        ImageView imgPopular;
        Button btnShowDetail;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            titlePopular = itemView.findViewById(R.id.title_popular);
            descPopular = itemView.findViewById(R.id.location_popular);
            imgPopular = itemView.findViewById(R.id.img_popular);
            btnShowDetail = itemView.findViewById(R.id.showDetail1);
        }
    }
}
