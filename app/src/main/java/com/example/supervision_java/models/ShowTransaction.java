package com.example.supervision_java.models;

import com.google.gson.Gson;

import java.util.List;

public class ShowTransaction {

    private String message;
    private Transaction transaction;
    private int status_code;

    public static ShowTransaction objectFromData(String str) {

        return new Gson().fromJson(str, ShowTransaction.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public static class Transaction {
        private int transaction_id;
        private String order_date;
        private String table_number;
        private String customer_name;
        private List<Menus> menus;
        private int subtotal;
        private int discount;
        private int tax;
        private int total;
        private String payment_method;

        public static Transaction objectFromData(String str) {

            return new Gson().fromJson(str, Transaction.class);
        }

        public int getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(int transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
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

        public List<Menus> getMenus() {
            return menus;
        }

        public void setMenus(List<Menus> menus) {
            this.menus = menus;
        }

        public int getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(int subtotal) {
            this.subtotal = subtotal;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getTax() {
            return tax;
        }

        public void setTax(int tax) {
            this.tax = tax;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public static class Menus {
            private String name;
            private int price;
            private int quantity;

            public static Menus objectFromData(String str) {

                return new Gson().fromJson(str, Menus.class);
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }
    }
}
