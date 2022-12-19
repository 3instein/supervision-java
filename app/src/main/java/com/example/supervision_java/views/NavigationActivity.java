package com.example.supervision_java.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.supervision_java.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    public static BottomNavigationView mainBottomNavigation;
    private NavHostFragment mainFragmentContainer;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mainBottomNavigation = findViewById(R.id.mainBottomNavigation);
        mainFragmentContainer = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);
        navController = mainFragmentContainer.getNavController();
        NavigationUI.setupWithNavController(mainBottomNavigation, navController);
    }
}