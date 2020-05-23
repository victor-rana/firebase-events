package com.test.firebaseevents.viewholder;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.firebaseevents.R;
import com.test.firebaseevents.listener.ItemClickListener;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public AppCompatTextView txtMenuName, custNumber, tvEventType, tvEventDesc;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = itemView.findViewById(R.id.menu_name);
        custNumber = itemView.findViewById(R.id.custNumber);
        tvEventType = itemView.findViewById(R.id.tvEventType);
        tvEventDesc = itemView.findViewById(R.id.tvEventDesc);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
