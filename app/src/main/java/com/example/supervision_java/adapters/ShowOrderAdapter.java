package com.example.supervision_java.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supervision_java.R;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.ShowOrder;

import java.util.List;

public class ShowOrderAdapter extends RecyclerView.Adapter<ShowOrderAdapter.ShowOrderViewHolder> {
    private Context context;
    public static List<ShowOrder.Order.Menus> menusList;

    public ShowOrderAdapter(Context context) {
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

    public ShowOrderAdapter.ShowOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_show_order_list, parent, false);
        return new ShowOrderAdapter.ShowOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowOrderAdapter.ShowOrderViewHolder holder, int position) {
        final ShowOrder.Order.Menus menusList = getMenusList().get(position);
        holder.menuQuantity.setText(Integer.toString(menusList.getQuantity()));
        holder.menuName.setText(menusList.getName());
        holder.menuSubtotal.setText(Utils.convertToIdr(menusList.getPrice() * menusList.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return getMenusList().size();
    }

    public class ShowOrderViewHolder extends RecyclerView.ViewHolder {
        TextView menuQuantity, menuName, menuSubtotal;

        public ShowOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            menuQuantity = itemView.findViewById(R.id.menuQuantity);
            menuName = itemView.findViewById(R.id.menuName);
            menuSubtotal = itemView.findViewById(R.id.menuSubtotal);
        }
    }
}
