package com.findlabour.saad.localservices.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.findlabour.saad.localservices.Interface.ItemClickListener;
import com.findlabour.saad.localservices.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textCategoryName;
    public ImageView imageCategory;
    public TextView category_description;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder( View itemView) {
        super(itemView);

        textCategoryName = itemView.findViewById(R.id.category_name);
        imageCategory = itemView.findViewById(R.id.category_image);
        //category_description = itemView.findViewById(R.id.category_description);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}