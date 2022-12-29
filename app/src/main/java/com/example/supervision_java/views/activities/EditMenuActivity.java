package com.example.supervision_java.views.activities;

import static com.example.supervision_java.views.activities.DeleteMenuActivity.showOrder;

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
import com.example.supervision_java.adapters.EditMenuAdapter;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.viewmodels.OrderViewModel;

public class EditMenuActivity extends AppCompatActivity {

    private ActionBar actionBar;
    public static String orderId;
    public static RecyclerView editMenuRV;
    private Intent intent;
    public static Context context;
    public static OrderViewModel orderViewModel;
    public static EditMenuAdapter adapter;
    public static LifecycleOwner lifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Ganti Menu");

        intent = getIntent();
        orderId = intent.getExtras().get("order_id").toString();
        context = getBaseContext();
        adapter = new EditMenuAdapter(context);
        lifecycleOwner = EditMenuActivity.this;
        editMenuRV = findViewById(R.id.editMenuRV);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.showOrder("Bearer " + MainActivity.user.getToken(), orderId);
        orderViewModel.getShowOrderDetail().observe(EditMenuActivity.this, showOrder);
    }

    public static Observer<ShowOrder> showOrder = new Observer<ShowOrder>() {
        @Override
        public void onChanged(ShowOrder showOrder) {
            editMenuRV.setLayoutManager(new LinearLayoutManager(context));
            adapter.setMenusList(showOrder.getOrder().getMenus());
            editMenuRV.setAdapter(adapter);
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