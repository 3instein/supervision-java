package com.example.supervision_java.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.DeleteMenuAdapter;
import com.example.supervision_java.adapters.ShowOrderAdapter;
import com.example.supervision_java.helpers.Utils;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.viewmodels.OrderViewModel;

public class DeleteMenuActivity extends AppCompatActivity {

    private ActionBar actionBar;
    public static RecyclerView deleteMenuRV;
    private Intent intent;
    public static OrderViewModel orderViewModel;
    public static DeleteMenuAdapter adapter;
    public static Context context;
    public static String menuId;
    public static LifecycleOwner lifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_menu);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Hapus Menu");

        intent = getIntent();
        lifecycleOwner = DeleteMenuActivity.this;
        menuId = intent.getExtras().get("order_id").toString();
        context = getBaseContext();
        adapter = new DeleteMenuAdapter(context);
        deleteMenuRV = findViewById(R.id.deleteMenuRV);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), intent.getExtras().get("order_id").toString());
        orderViewModel.getShowOrderDetail().observe(DeleteMenuActivity.this, showOrder);
    }

    public static Observer<ShowOrder> showOrder = new Observer<ShowOrder>() {
        @Override
        public void onChanged(ShowOrder showOrder) {
            deleteMenuRV.setLayoutManager(new LinearLayoutManager(context));
            adapter.setMenusList(showOrder.getOrder().getMenus());
            deleteMenuRV.setAdapter(adapter);
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