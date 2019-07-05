package com.findlabour.saad.localservices.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.findlabour.saad.localservices.Interface.ItemClickListener;
import com.findlabour.saad.localservices.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textItemName,textItemPrice,textItemQuantity,textItemDiscount;
    public ImageView imageItem;


    private ItemClickListener itemClickListener;


    public ItemsViewHolder(@NonNull View itemView) {
        super(itemView);

        textItemDiscount= itemView.findViewById(R.id.item_discount);
        textItemName = itemView.findViewById(R.id.item_name);
        textItemPrice = itemView.findViewById(R.id.item_price);
        textItemQuantity= itemView.findViewById(R.id.item_quantity);
        imageItem = itemView.findViewById(R.id.item_image);
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