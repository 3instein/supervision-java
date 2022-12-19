package com.example.supervision_java.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.api.ApiService;
import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.views.activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static AuthRepository repository;

    private AuthRepository() {
    }

    public static AuthRepository getInstance() {
        if (repository == null) {
            repository = new AuthRepository();
        }

        return repository;
    }

    public MutableLiveData<LoginResponse> authenticate(String username, String password) {
        final MutableLiveData<LoginResponse> authenticateResult = new MutableLiveData<>();

        ApiService.endPoint().authenticate(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    authenticateResult.setValue(response.body());
                } else {
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.context, error.getString("status_message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return authenticateResult;
    }
}
