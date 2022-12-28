package com.example.supervision_java.models;

import com.google.gson.Gson;

import java.util.List;

public class ShowOrder {
    private String message;
    private Order order;
    private int status_code;

    public static ShowOrder objectFromData(String str) {

        return new Gson().fromJson(str, ShowOrder.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public static class Order {
        private int order_id;
        private String order_date;
        private String table_number;
        private String customer_name;
        private List<Menus> menus;
        private int subtotal;
        private int discount;
        private int tax;
        private int total;
        private String payment_method;

        public static Order objectFromData(String str) {

            return new Gson().fromJson(str, Order.class);
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
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
            private int id;
            private String name;
            private String image;
            private int price;
            private int quantity;

            public static Menus objectFromData(String str) {

                return new Gson().fromJson(str, Menus.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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
