package com.example.supervision_java.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.models.CancelOrder;
import com.example.supervision_java.models.ConfirmOrder;
import com.example.supervision_java.models.EditOrderResponse;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.models.ShowOrder;
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

    private MutableLiveData<ShowOrder> showOrderResult = new MutableLiveData<>();

    public void showOrder(String token, String orderId) {
        showOrderResult = repository.showOrder(token, orderId);
    }

    public LiveData<ShowOrder> getShowOrderDetail() {
        return showOrderResult;
    }

    private MutableLiveData<EditOrderResponse> updateOrderResult = new MutableLiveData<>();

    public void updateOrder(String token, String orderId, String type, int menuId, int quantity) {
        updateOrderResult = repository.updateOrder(token, orderId, type, menuId, quantity);
    }

    public LiveData<EditOrderResponse> getUpdateOrderDetail() {
        return updateOrderResult;
    }

    private MutableLiveData<ConfirmOrder> confirmOrderResult = new MutableLiveData<>();

    public void confirmOrder(String token, String orderId) {
        confirmOrderResult = repository.confirmOrder(token, orderId);
    }

    public LiveData<ConfirmOrder> getConfirmOrderDetail() {
        return confirmOrderResult;
    }

    private MutableLiveData<CancelOrder> cancelOrderResult = new MutableLiveData<>();

    public void cancelOrder(String token, String orderId) {
        cancelOrderResult = repository.cancelOrder(token, orderId);
    }

    public LiveData<CancelOrder> getCancelOrderDetail() {
        return cancelOrderResult;
    }
}
