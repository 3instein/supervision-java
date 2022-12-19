package com.example.supervision_java.models;

import com.google.gson.Gson;

import java.util.List;

public class Order {

    private String message;
    private List<Orders> orders;
    private int status_code;

    public static Order objectFromData(String str) {

        return new Gson().fromJson(str, Order.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public static class Orders {
        private int order_id;
        private String table_number;
        private String customer_name;
        private int total;

        public static Orders objectFromData(String str) {

            return new Gson().fromJson(str, Orders.class);
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getTable_number() {
            return table_number;
        }

        public void setTable_number(String table_number) {
            this.table_number = table_number;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
