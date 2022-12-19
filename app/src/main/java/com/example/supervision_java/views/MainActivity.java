package com.example.supervision_java.views;

import android.os.Bundle;

import com.example.supervision_java.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.supervision_java.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout loginEmailInput, loginPasswordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
