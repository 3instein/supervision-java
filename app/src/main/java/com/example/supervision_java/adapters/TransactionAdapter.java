package com.example.supervision_java.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supervision_java.R;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.Transaction;
import com.example.supervision_java.views.activities.OrderDetailActivity;
import com.example.supervision_java.views.activities.OrderHistoryActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{
    private Context context;
    public static List<Transaction.Transactions> transactionsList;

    public TransactionAdapter(Context context) {
        this.context = context;
    }

    private List<Transaction.Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction.Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return new TransactionAdapter.TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position) {
        final Transaction.Transactions transactionResult = getTransactionsList().get(position);
        holder.cardTransactionCustomerName.setText(transactionResult.getCustomer_name());
        holder.cardTransactionId.setText("#" + Integer.toString(transactionResult.getTransaction_id()));
        holder.cardTransactionTable.setText("Meja " + transactionResult.getTable_number());
        holder.cardTransactionTotal.setText(Utils.convertToIdr(transactionResult.getTotal()));

        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderHistoryActivity.class);
                intent.putExtra("transaction_id", transactionResult.getTransaction_id());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTransactionsList().size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView cardTransactionCustomerName, cardTransactionId, cardTransactionTable, cardTransactionTotal;
        CardView cardOrder;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTransactionCustomerName = itemView.findViewById(R.id.cardOrderCustomerName);
            cardTransactionId = itemView.findViewById(R.id.cardOrderId);
            cardTransactionTable = itemView.findViewById(R.id.cardOrderTable);
            cardTransactionTotal = itemView.findViewById(R.id.cardOrderTotal);
            cardOrder = itemView.findViewById(R.id.cardOrder);
        }
    }
}
