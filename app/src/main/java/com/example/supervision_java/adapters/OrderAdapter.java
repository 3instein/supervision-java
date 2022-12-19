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
import com.example.supervision_java.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    public static List<Order.Orders> ordersList;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    private List<Order.Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order.Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        final Order.Orders orderResult = getOrdersList().get(position);
        holder.cardOrderCustomerName.setText(orderResult.getCustomer_name());
        holder.cardOrderId.setText("#" + Integer.toString(orderResult.getOrder_id()));
        holder.cardOrderTable.setText("Meja " + orderResult.getTable_number());
        holder.cardOrderTotal.setText(Utils.convertToIdr(orderResult.getTotal()));
    }

    @Override
    public int getItemCount() {
        return getOrdersList().size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView cardOrderCustomerName, cardOrderId, cardOrderTable, cardOrderTotal;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrderCustomerName = itemView.findViewById(R.id.cardOrderCustomerName);
            cardOrderId = itemView.findViewById(R.id.cardOrderId);
            cardOrderTable = itemView.findViewById(R.id.cardOrderTable);
            cardOrderTotal = itemView.findViewById(R.id.cardOrderTotal);
        }
    }
}