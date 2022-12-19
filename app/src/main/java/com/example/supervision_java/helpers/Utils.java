package com.example.supervision_java.helpers;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static String convertToIdr(int amount) {
        Locale locale = new Locale("id", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String newAmount = formatter.format(amount);
        return newAmount;
    }
}
