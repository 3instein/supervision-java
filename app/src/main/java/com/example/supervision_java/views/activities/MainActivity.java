package com.example.supervision_java.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.supervision_java.R;
import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.viewmodels.AuthViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.supervision_java.views.NavigationActivity;
import com.google.android.material.textfield.TextInputLayout;

import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout loginEmailInput, loginPasswordInput;
    private Button loginBtn;
    public static Context context;
    private AuthViewModel viewModel;
    public static LoginResponse.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getBaseContext();

        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        loginBtn = findViewById(R.id.loginBtn);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginEmailInput.getEditText().getText().toString();
                String password = loginPasswordInput.getEditText().getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    viewModel.authenticate(username, password);
                    viewModel.getAuthenticateDetails().observe(MainActivity.this, new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                            try {
                                if (loginResponse.getStatus_code() == 200) {
                                    MainActivity.user = loginResponse.getUser();
                                    Toast.makeText(context, MainActivity.user.getName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Cannot log in. Wrong credentials!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
