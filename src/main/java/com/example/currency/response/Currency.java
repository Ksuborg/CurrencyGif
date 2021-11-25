package com.example.currency.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    private Map<String, Double> rates = new HashMap<String, Double>();

    public Map<String, Double> getRates() {
        return this.rates;
    }

    public void setRates(String key, Double value) {
        this.rates.put(key, value);
    }
}