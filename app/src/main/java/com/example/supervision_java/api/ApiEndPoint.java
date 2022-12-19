package com.example.supervision_java.api;

import com.example.supervision_java.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @FormUrlEncoded
    @POST("authenticate")
    Call<LoginResponse> authenticate(
            @Field("username") String username,
            @Field("password") String password
    );
}