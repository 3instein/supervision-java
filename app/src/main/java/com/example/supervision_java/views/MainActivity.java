package com.example.supervision_java.views;

import android.content.Context;
import android.os.Bundle;

import com.example.supervision_java.R;
import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.viewmodels.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
