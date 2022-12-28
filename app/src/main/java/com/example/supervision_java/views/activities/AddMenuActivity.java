package com.example.supervision_java.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.MenuAdapter;
import com.example.supervision_java.models.Menu;
import com.example.supervision_java.viewmodels.MenuViewModel;

import java.util.List;

public class AddMenuActivity extends AppCompatActivity {

    private RecyclerView addMenuRV;
    private ActionBar actionBar;
    private MenuViewModel menuViewModel;
    private MenuAdapter adapter;
    private Intent intent;
    public static String orderId;
    public static LifecycleOwner lifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tambah Menu");

        adapter = new MenuAdapter(getBaseContext());
        intent = getIntent();
        lifecycleOwner = AddMenuActivity.this;
        orderId = intent.getExtras().get("order_id").toString();
        addMenuRV = findViewById(R.id.addMenuRV);
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        menuViewModel.getAllMenus("Bearer " + MainActivity.user.getToken());
        menuViewModel.getAllMenusDetail().observe(AddMenuActivity.this, showAllMenus);
    }

    private Observer<List<Menu.Menus>> showAllMenus = new Observer<List<Menu.Menus>>() {
        @Override
        public void onChanged(List<Menu.Menus> menus) {
            addMenuRV.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            adapter.setMenusList(menus);
            addMenuRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MenuAdapter.menusList.clear();
    }
}