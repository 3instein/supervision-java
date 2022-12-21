package com.example.supervision_java.models;

import com.google.gson.Gson;

import java.util.List;

public class Transaction {
    private String message;
    private List<Transactions> transactions;
    private int status_code;

    public static Transaction objectFromData(String str) {

        return new Gson().fromJson(str, Transaction.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public static class Transactions {
        private int transaction_id;
        private String table_number;
        private String customer_name;
        private int total;
        private String status;

        public static Transactions objectFromData(String str) {

            return new Gson().fromJson(str, Transactions.class);
        }

        public int getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(int transaction_id) {
            this.transaction_id = transaction_id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
