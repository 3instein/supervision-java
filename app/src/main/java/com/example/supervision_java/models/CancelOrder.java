package com.example.supervision_java.models;

import com.google.gson.Gson;

public class CancelOrder {

    private String message;
    private int status_code;

    public static CancelOrder objectFromData(String str) {

        return new Gson().fromJson(str, CancelOrder.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
