package com.example.supervision_java.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.ShowOrderAdapter;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.CancelOrder;
import com.example.supervision_java.models.ConfirmOrder;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.viewmodels.OrderViewModel;
import com.example.supervision_java.views.NavigationActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView transactionNumber, transactionTime, customerName, tableNumber, subtotalPrice, taxPrice, discountPrice, totalPrice;
    private RecyclerView orderRV;
    private Button confirmButton, cancelButton;
    private ImageButton optionButton;
    private OrderViewModel orderViewModel;
    private Intent intent;
    public static Context context;
    private String[] actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detil Transaksi");

        actions = new String[]{"Tambah Menu", "Hapus Menu"};
        intent = getIntent();
        context = getBaseContext();
        transactionNumber = findViewById(R.id.transactionNumber);
        transactionTime = findViewById(R.id.transactionTimestamp);
        customerName = findViewById(R.id.customerName);
        tableNumber = findViewById(R.id.tableNumber);
        subtotalPrice = findViewById(R.id.subtotalPrice);
        taxPrice = findViewById(R.id.taxPrice);
        discountPrice = findViewById(R.id.discountPrice);
        totalPrice = findViewById(R.id.totalPrice);
        orderRV = findViewById(R.id.orderRV);
        confirmButton = findViewById(R.id.confirmButton);
        cancelButton = findViewById(R.id.cancelButton);
        optionButton = findViewById(R.id.optionButton);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("order_id").toString());
        orderViewModel.getShowOrderDetail().observe(OrderDetailActivity.this, showOrder);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderViewModel.confirmOrder("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("order_id").toString());
                orderViewModel.getConfirmOrderDetail().observe(OrderDetailActivity.this, confirmOrder);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderViewModel.cancelOrder("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("order_id").toString());
                orderViewModel.getCancelOrderDetail().observe(OrderDetailActivity.this, cancelOrder);
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
                builder.setItems(actions, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        Toast.makeText(OrderDetailActivity.this, "Ini maklo", Toast.LENGTH_SHORT).show();
                                    case 1:
                                        Toast.makeText(OrderDetailActivity.this, "Bukan maklo", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
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

    private Observer<ConfirmOrder> confirmOrder = new Observer<ConfirmOrder>() {
        @Override
        public void onChanged(ConfirmOrder confirmOrder) {
            if (confirmOrder.getStatus_code() == 200) {
                Toast.makeText(OrderDetailActivity.this, "Confirmed order id: " + intent.getExtras().get("order_id").toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetailActivity.this, NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    };

    private Observer<CancelOrder> cancelOrder = new Observer<CancelOrder>() {
        @Override
        public void onChanged(CancelOrder cancelOrder) {
            if (cancelOrder.getStatus_code() == 200) {
                Toast.makeText(OrderDetailActivity.this, "Cancelled order id: " + intent.getExtras().get("order_id").toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetailActivity.this, NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
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