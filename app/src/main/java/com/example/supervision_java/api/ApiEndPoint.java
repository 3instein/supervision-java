package com.example.supervision_java.api;

import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.models.ShowOrder;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoint {

    @FormUrlEncoded
    @POST("authenticate")
    Call<LoginResponse> authenticate(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("orders")
    Call<Order> getALlOrders(
            @Header("Authorization") String token
    );

    @GET("orders/{order_id}")
    Call<ShowOrder> showOrder(
            @Header("Authorization") String token,
            @Path(value = "order_id", encoded = true) String orderId
    );
}
