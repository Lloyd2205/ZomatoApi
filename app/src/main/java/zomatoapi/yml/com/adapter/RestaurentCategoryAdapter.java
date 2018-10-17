package zomatoapi.yml.com.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zomatoapi.yml.com.models.RestaurentCategory;
import zomatoapi.yml.com.zomatoapi.R;

public class RestaurentCategoryAdapter extends RecyclerView.Adapter<RestaurentCategoryAdapter.RestaurentCategoryViewHolder> {
    private RestaurentCategory restaurentCategory;

    public RestaurentCategoryAdapter(RestaurentCategory restaurentCategory) {
        this.restaurentCategory = restaurentCategory;
    }


    @NonNull
    @Override
    public RestaurentCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new RestaurentCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurentCategoryViewHolder holder, int position) {
        holder.categoryNameTxt.setText(restaurentCategory.getCategories().get(position).getCategories().getName());
        holder.categoryIdTxt.setText(Integer.toString(restaurentCategory.getCategories().get(position).getCategories().getId()));
    }

    @Override
    public int getItemCount() {
        return restaurentCategory.getCategories().size();
    }


    public class RestaurentCategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryIdTxt;
        private TextView categoryNameTxt;

        public RestaurentCategoryViewHolder(View itemView) {
            super(itemView);
            categoryIdTxt = itemView.findViewById(R.id.category_id);
            categoryNameTxt = itemView.findViewById(R.id.category_name);
        }
    }
}
