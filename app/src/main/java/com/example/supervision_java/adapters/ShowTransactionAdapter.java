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
import com.example.supervision_java.models.ShowTransaction;

import java.util.List;

public class ShowTransactionAdapter extends RecyclerView.Adapter<ShowTransactionAdapter.ShowTransactionViewHolder> {
    private Context context;
    public static List<ShowTransaction.Transaction.Menus> menusList;

    public ShowTransactionAdapter(Context context) {
        this.context = context;
    }

    private List<ShowTransaction.Transaction.Menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<ShowTransaction.Transaction.Menus> menusList) {
        this.menusList = menusList;
    }

    @NonNull
    @Override
    public ShowTransactionAdapter.ShowTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_show_order_list, parent, false);
        return new ShowTransactionAdapter.ShowTransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowTransactionAdapter.ShowTransactionViewHolder holder, int position) {
        final ShowTransaction.Transaction.Menus menuList = getMenusList().get(position);
        holder.menuQuantity.setText(Integer.toString(menuList.getQuantity()));
        holder.menuName.setText(menuList.getName());
        holder.menuSubtotal.setText(Utils.convertToIdr(menuList.getPrice() * menuList.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return getMenusList().size();
    }

    public class ShowTransactionViewHolder extends RecyclerView.ViewHolder {
        TextView menuQuantity, menuName, menuSubtotal;

        public ShowTransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            menuQuantity = itemView.findViewById(R.id.menuQuantity);
            menuName = itemView.findViewById(R.id.menuName);
            menuSubtotal = itemView.findViewById(R.id.menuSubtotal);
        }
    }
}
