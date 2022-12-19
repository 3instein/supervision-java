package com.example.supervision_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.repositories.AuthRepository;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository repository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = AuthRepository.getInstance();
    }

    private MutableLiveData<LoginResponse> authenticateResult = new MutableLiveData<>();

    public void authenticate(String username, String password) {
        authenticateResult = repository.authenticate(username, password);
    }

    public LiveData<LoginResponse> getAuthenticateDetails() {
        return authenticateResult;
    }
}
