package com.example.supervision_java.api;

import com.example.supervision_java.models.CancelOrder;
import com.example.supervision_java.models.ConfirmOrder;
import com.example.supervision_java.models.EditOrderResponse;
import com.example.supervision_java.models.LoginResponse;
import com.example.supervision_java.models.Menu;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.models.ShowTransaction;
import com.example.supervision_java.models.Transaction;
import com.example.supervision_java.models.ShowOrder;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @PUT("orders/{order_id}")
    Call<EditOrderResponse> updateOrder(
            @Header("Authorization") String token,
            @Path(value = "order_id", encoded = true) String orderId,
            @Query("type") String type,
            @Query("menu_id") int menuId,
            @Query("quantity") int quantity
    );

    @GET("orders/{order_id}/confirm")
    Call<ConfirmOrder> confirmOrder(
            @Header("Authorization") String token,
            @Path(value = "order_id") String orderId
    );

    @GET("orders/{order_id}/cancel")
    Call<CancelOrder> cancelOrder(
            @Header("Authorization") String token,
            @Path(value = "order_id") String orderId
    );

    @GET("transactions")
    Call<Transaction> getAllTransactions(
            @Header("Authorization") String token
    );

    @GET("transactions/{transaction_id}")
    Call<ShowTransaction> showTransaction(
            @Header("Authorization") String token,
            @Path(value = "transaction_id", encoded = true) String transactionId
    );

    @GET("menus-api")
    Call<Menu> getAllMenus(
            @Header("Authorization") String token
    );
}
