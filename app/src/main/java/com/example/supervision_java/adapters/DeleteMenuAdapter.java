package com.example.supervision_java.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supervision_java.R;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.views.activities.DeleteMenuActivity;
import com.example.supervision_java.views.activities.MainActivity;

import java.util.List;

public class DeleteMenuAdapter extends RecyclerView.Adapter<DeleteMenuAdapter.DeleteViewHolder> {
    private Context context;
    public static List<ShowOrder.Order.Menus> menusList;

    public DeleteMenuAdapter(Context context) {
        this.context = context;
    }

    private List<ShowOrder.Order.Menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<ShowOrder.Order.Menus> menusList) {
        this.menusList = menusList;
    }

    @NonNull
    @Override
    public DeleteMenuAdapter.DeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_delete_menu, parent, false);
        return new DeleteMenuAdapter.DeleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteMenuAdapter.DeleteViewHolder holder, int position) {
        final ShowOrder.Order.Menus menus = getMenusList().get(position);
        Glide.with(context).load(menus.getImage()).into(holder.cardDeleteImage);
        holder.cardDeleteName.setText(menus.getQuantity() + "x " + menus.getName());
        holder.cardDeletePrice.setText(Utils.convertToIdr(menus.getPrice()));

        holder.cardDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteMenuActivity.orderViewModel.updateOrder("Bearer " + MainActivity.user.getToken(), DeleteMenuActivity.menuId, "remove", menus.getId(), 0);
                DeleteMenuActivity.orderViewModel.getShowOrderDetail().observe(DeleteMenuActivity.lifecycleOwner, new Observer<ShowOrder>() {
                    @Override
                    public void onChanged(ShowOrder showOrder) {
                        if (showOrder.getStatus_code() == 200) {
                            DeleteMenuActivity.orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), DeleteMenuActivity.menuId);
                            DeleteMenuActivity.orderViewModel.getShowOrderDetail().observe(DeleteMenuActivity.lifecycleOwner, DeleteMenuActivity.showOrder);
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMenusList().size();
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {
        ImageView cardDeleteBtn, cardDeleteImage;
        TextView cardDeleteName, cardDeletePrice;

        public DeleteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardDeleteName = itemView.findViewById(R.id.cardDeleteName);
            cardDeletePrice = itemView.findViewById(R.id.cardDeletePrice);
            cardDeleteBtn = itemView.findViewById(R.id.cardDeleteBtn);
            cardDeleteImage = itemView.findViewById(R.id.cardDeleteImage);
        }
    }
}
