package com.example.supervision_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.models.Menu;
import com.example.supervision_java.repositories.MenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    private MenuRepository repository;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        repository = MenuRepository.getInstance();
    }

    private MutableLiveData<List<Menu.Menus>> getAllMenusResult = new MutableLiveData<>();

    public void getAllMenus(String token) {
        getAllMenusResult = repository.getAllMenus(token);
    }

    public LiveData<List<Menu.Menus>> getAllMenusDetail() {
        return getAllMenusResult;
    }
}
