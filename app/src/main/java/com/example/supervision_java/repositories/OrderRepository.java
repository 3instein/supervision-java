package com.example.supervision_java.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.api.ApiService;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.models.ShowOrder;
import com.example.supervision_java.views.activities.MainActivity;
import com.example.supervision_java.views.fragments.OrderFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    private static OrderRepository repository;

    private OrderRepository() {
    }

    public static OrderRepository getInstance() {
        if (repository == null) {
            repository = new OrderRepository();
        }

        return repository;
    }

    private List<Order.Orders> ordersList = new ArrayList<>();

    public MutableLiveData<List<Order.Orders>> getAllOrders(String token) {
        final MutableLiveData<List<Order.Orders>> allOrdersResult = new MutableLiveData<>();

        ApiService.endPoint().getALlOrders(token).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    ordersList.addAll(response.body().getOrders());
                    allOrdersResult.setValue(ordersList);
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
            public void onFailure(Call<Order> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return allOrdersResult;
    }

    public MutableLiveData<ShowOrder> showOrder(String token, String orderId) {
        final MutableLiveData<ShowOrder> orderResult = new MutableLiveData<>();

        ApiService.endPoint().showOrder(token, orderId).enqueue(new Callback<ShowOrder>() {
            @Override
            public void onResponse(Call<ShowOrder> call, Response<ShowOrder> response) {
                if (response.isSuccessful()) {
                    orderResult.setValue(response.body());
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
            public void onFailure(Call<ShowOrder> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return orderResult;
    }
}
