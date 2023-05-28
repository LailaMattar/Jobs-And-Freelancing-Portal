package com.example.jopy.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{
    List<Category> categories;
    List<ConstraintLayout> constraintLayouts = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();
    Context context;
    Listener listener;

    public CategoriesAdapter(List<Category> categories, Context context,Listener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.categoryName.setText(category.getTitle());
        constraintLayouts.add(holder.constraintLayout);
        textViews.add(holder.categoryName);
        for(int i = 0 ; i < constraintLayouts.size() ; i++){
            if (categories.get(i).isClicked()){
                constraintLayouts.get(i).setBackground(ContextCompat.getDrawable(context,R.drawable.clicked_category));
                textViews.get(i).setTextColor(context.getResources().getColor(R.color.whiteColor));
            }
            else {
                constraintLayouts.get(i).setBackground(ContextCompat.getDrawable(context,R.drawable.card_post));
                textViews.get(i).setTextColor(context.getResources().getColor(R.color.darkGrayColor));
            }
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ConstraintLayout constraintLayout : constraintLayouts){
                    constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.card_post));
                }
                for(TextView textView : textViews){
                    textView.setTextColor(context.getResources().getColor(R.color.darkGrayColor));
                }
                for(int i = 0 ; i < categories.size() ; i ++){
                    categories.get(i).setClicked(false);
                }
                category.setClicked(true);
                holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.clicked_category));
                holder.categoryName.setTextColor(context.getResources().getColor(R.color.whiteColor));
                listener.setOnCategoryClickedListener();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoryName;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName     = itemView.findViewById(R.id.category_name);
            constraintLayout = itemView.findViewById(R.id.category_constraint);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: pressed category");
            Log.e("TAG", "onClick: test category title : "+categoryName.getText().toString());
        }
    }
    public interface Listener{
        void setOnCategoryClickedListener();
    }
}
