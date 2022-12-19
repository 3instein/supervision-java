package com.example.supervision_java.api;

import com.example.supervision_java.helpers.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public static ApiEndPoint endPoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);
    }
}
