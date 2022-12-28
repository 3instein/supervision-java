package com.example.supervision_java.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.api.ApiService;
import com.example.supervision_java.models.Menu;
import com.example.supervision_java.views.fragments.OrderFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuRepository {
    private static MenuRepository repository;

    private MenuRepository() {
    }

    public static MenuRepository getInstance() {
        if (repository == null) {
            repository = new MenuRepository();
        }

        return repository;
    }

    private List<Menu.Menus> menusList = new ArrayList<>();

    public MutableLiveData<List<Menu.Menus>> getAllMenus(String token) {
        final MutableLiveData<List<Menu.Menus>> allMenusResult = new MutableLiveData<>();

        ApiService.endPoint().getAllMenus(token).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if (response.isSuccessful()) {
                    menusList.addAll(response.body().getMenus());
                    allMenusResult.setValue(menusList);
                } else {
                    try {
                        JSONObject error = new JSONObject(response.errorBody().string());
                        Toast.makeText(OrderFragment.context, error.getString("status_message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return allMenusResult;
    }
}
