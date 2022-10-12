package Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import homestay.homestayAdapter;
import com.example.projecthomestay.R;

import java.util.List;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.CategoryViewHolder>{

    private Context context;
    private List<categoryA> listCategory;

    public ListCategoryAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<categoryA> list){
        this.listCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        categoryA category = listCategory.get(position);
        if(category==null) return;
        holder.textViewTitleCategory.setText(category.getNameListCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcvCategoryList.setLayoutManager(linearLayoutManager);

        homestayAdapter categoryAdapter = new homestayAdapter();
        categoryAdapter.setData(category.getCategorys());
        holder.rcvCategoryList.setAdapter(categoryAdapter);
    }

    @Override
    public int getItemCount() {
        if(listCategory != null) return listCategory.size();
        return 0;
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitleCategory;
        private RecyclerView rcvCategoryList;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleCategory = itemView.findViewById(R.id.name_list);
            rcvCategoryList = itemView.findViewById(R.id.rcv_List_category);
        }
    }
}
