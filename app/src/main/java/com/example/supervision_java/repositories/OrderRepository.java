package com.example.supervision_java.repositories;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.supervision_java.api.ApiService;
import com.example.supervision_java.models.CancelOrder;
import com.example.supervision_java.models.ConfirmOrder;
import com.example.supervision_java.models.EditOrderResponse;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.models.ShowOrder;
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

    public MutableLiveData<EditOrderResponse> updateOrder(String token, String orderId, String type, int menuId, int quantity) {
        final MutableLiveData<EditOrderResponse> updateOrderResult = new MutableLiveData<>();

        ApiService.endPoint().updateOrder(token, orderId, type, menuId, quantity).enqueue(new Callback<EditOrderResponse>() {
            @Override
            public void onResponse(Call<EditOrderResponse> call, Response<EditOrderResponse> response) {
                if (response.isSuccessful()) {
                    updateOrderResult.setValue(response.body());
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
            public void onFailure(Call<EditOrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return updateOrderResult;
    }

    public MutableLiveData<ConfirmOrder> confirmOrder(String token, String orderId) {
        final MutableLiveData<ConfirmOrder> confirmOrderResult = new MutableLiveData<>();

        ApiService.endPoint().confirmOrder(token, orderId).enqueue(new Callback<ConfirmOrder>() {
            @Override
            public void onResponse(Call<ConfirmOrder> call, Response<ConfirmOrder> response) {
                if (response.isSuccessful()) {
                    confirmOrderResult.setValue(response.body());
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
            public void onFailure(Call<ConfirmOrder> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return confirmOrderResult;
    }

    public MutableLiveData<CancelOrder> cancelOrder(String token, String orderId) {
        final MutableLiveData<CancelOrder> cancelOrderResult = new MutableLiveData<>();

        ApiService.endPoint().cancelOrder(token, orderId).enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {
                if (response.isSuccessful()) {
                    cancelOrderResult.setValue(response.body());
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
            public void onFailure(Call<CancelOrder> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return cancelOrderResult;
    }
}
