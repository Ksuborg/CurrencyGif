package com.example.currency;

import java.util.regex.Pattern;

public class Utils {
    public static boolean chekSymbols(String symbols) {
        return (symbols.length() != 3 || !Pattern.matches("[A-Z]+", symbols));
    }
}