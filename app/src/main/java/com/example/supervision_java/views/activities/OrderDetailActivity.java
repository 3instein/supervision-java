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
import com.example.supervision_java.adapters.ShowOrderAdapter;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.viewmodels.OrderViewModel;

public class OrderDetailActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private String orderId;
    private TextView transactionNumber, transactionTime, customerName, tableNumber, subtotalPrice, taxPrice, discountPrice, totalPrice;
    private RecyclerView orderRV;
    private Button confirmButton;
    private OrderViewModel orderViewModel;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        context = getBaseContext();
        transactionNumber = findViewById(R.id.transactionNumber);
        transactionTime = findViewById(R.id.transactionTimestamp);
        customerName = findViewById(R.id.customerRole);
        tableNumber = findViewById(R.id.tableNumber);
        subtotalPrice = findViewById(R.id.subtotalPrice);
        taxPrice = findViewById(R.id.taxPrice);
        discountPrice = findViewById(R.id.discountPrice);
        totalPrice = findViewById(R.id.totalPrice);
        orderRV = findViewById(R.id.orderRV);
        confirmButton = findViewById(R.id.confirmButton);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("order_id").toString());
        orderViewModel.getShowOrderDetail().observe(this, showOrder);
    }

    private Observer<ShowOrder> showOrder = new Observer<ShowOrder>() {
        @Override
        public void onChanged(ShowOrder showOrder) {
            orderRV.setLayoutManager(new LinearLayoutManager(context));
            transactionNumber.setText("#" + Integer.toString(showOrder.getOrder().getOrder_id()));
            transactionTime.setText(showOrder.getOrder().getOrder_date());
            customerName.setText(showOrder.getOrder().getCustomer_name());
            tableNumber.setText(showOrder.getOrder().getTable_number());
            subtotalPrice.setText(Utils.convertToIdr(showOrder.getOrder().getSubtotal()));
            taxPrice.setText(Utils.convertToIdr(showOrder.getOrder().getTax()));
            discountPrice.setText(Utils.convertToIdr(showOrder.getOrder().getDiscount()));
            totalPrice.setText(Utils.convertToIdr(showOrder.getOrder().getTotal()));
            ShowOrderAdapter adapter = new ShowOrderAdapter(context);
            adapter.setMenusList(showOrder.getOrder().getMenus());
            orderRV.setAdapter(adapter);
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