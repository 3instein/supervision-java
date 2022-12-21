package com.example.supervision_java.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.ShowTransactionAdapter;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.ShowTransaction;
import com.example.supervision_java.viewmodels.TransactionViewModel;

public class OrderHistoryActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView transactionNumber, transactionTimestamp, cashierName, paymentMethod, customerName, tableNumber, paymentStatus, subtotalNumber, taxNumber, discountNumber, totalNumber;
    private RecyclerView orderDetailRecycler;
    private Button cetakStrukButton;
    private TransactionViewModel transactionViewModel;
    private Intent intent;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        context = getBaseContext();
        transactionNumber = findViewById(R.id.transactionNumber);
        transactionTimestamp = findViewById(R.id.transactionTimestamp);
        cashierName = findViewById(R.id.cashierName);
        customerName = findViewById(R.id.customerName);
        tableNumber = findViewById(R.id.tableNumber);
        subtotalNumber = findViewById(R.id.subtotalNumber);
        taxNumber = findViewById(R.id.taxNumber);
        discountNumber = findViewById(R.id.discountNumber);
        totalNumber = findViewById(R.id.totalNumber);
        paymentStatus = findViewById(R.id.paymentStatus);
        paymentMethod = findViewById(R.id.paymentMethod);
        orderDetailRecycler = findViewById(R.id.orderDetailRecycler);
        cetakStrukButton = findViewById(R.id.cetakStrukButton);
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.showTransaction("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("transaction_id").toString());
        transactionViewModel.getShowTransactionResult().observe(OrderHistoryActivity.this, showTransaction);
    }

    private Observer<ShowTransaction> showTransaction = new Observer<ShowTransaction>() {
        @Override
        public void onChanged(ShowTransaction showTransaction) {
            orderDetailRecycler.setLayoutManager(new LinearLayoutManager(context));
            transactionNumber.setText("#" + Integer.toString(showTransaction.getTransaction().getTransaction_id()));
            transactionTimestamp.setText(showTransaction.getTransaction().getOrder_date());
            customerName.setText(showTransaction.getTransaction().getCustomer_name());
            tableNumber.setText(showTransaction.getTransaction().getTable_number());
            subtotalNumber.setText(Utils.convertToIdr(showTransaction.getTransaction().getSubtotal()));
            taxNumber.setText(Utils.convertToIdr(showTransaction.getTransaction().getTax()));
            discountNumber.setText(Utils.convertToIdr(showTransaction.getTransaction().getDiscount()));
            totalNumber.setText(Utils.convertToIdr(showTransaction.getTransaction().getTotal()));
            paymentMethod.setText(showTransaction.getTransaction().getPayment_method());
            paymentStatus.setText("Paid");
            ShowTransactionAdapter adapter = new ShowTransactionAdapter(context);
            adapter.setMenusList(showTransaction.getTransaction().getMenus());
            orderDetailRecycler.setAdapter(adapter);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}