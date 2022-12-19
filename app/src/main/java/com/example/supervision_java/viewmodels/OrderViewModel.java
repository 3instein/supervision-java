package com.example.supervision_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.models.Order;
import com.example.supervision_java.repositories.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository repository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = OrderRepository.getInstance();
    }

    private MutableLiveData<List<Order.Orders>> getAllOrdersResult = new MutableLiveData<>();

    public void getAllOrders(String token) {
        getAllOrdersResult = repository.getAllOrders(token);
    }

    public LiveData<List<Order.Orders>> getAllOrdersDetail() {
        return getAllOrdersResult;
    }
}
