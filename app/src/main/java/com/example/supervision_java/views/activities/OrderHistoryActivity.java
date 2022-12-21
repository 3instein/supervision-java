package com.example.supervision_java.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.supervision_java.R;

public class OrderHistoryActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView transactionNumber, transactionTimestamp, cashierName, customerName, tableNumber, subtotalNumber, taxNumber, discountNumber, totalNumber;
    private RecyclerView orderDetailRecycler;
    private Button cetakStrukButton;
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
        orderDetailRecycler = findViewById(R.id.orderDetailRecycler);
        cetakStrukButton = findViewById(R.id.cetakStrukButton);
    }

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