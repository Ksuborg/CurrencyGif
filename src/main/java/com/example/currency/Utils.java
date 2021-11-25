package com.example.currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getValidCurrencies() {
        List<String> validCurrencies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ValidCurrency.txt"))) {
            while (reader.ready()) {
                validCurrencies.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validCurrencies;
    }

    public static boolean chekSymbols(String symbols) {
        return (!getValidCurrencies().contains(symbols));
    }
}