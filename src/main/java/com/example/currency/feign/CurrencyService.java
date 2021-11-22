package com.example.currency.feign;

import com.example.currency.Constants;
import com.example.currency.response.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="USD", url= "https://openexchangerates.org/api")
public interface CurrencyService {

    @GetMapping(Constants.URL_USD_TODAY)
    public Currency checkDollarToday();

    @GetMapping(Constants.URL_USD_YESTERDAY)
    public Currency checkDollarYesterday(@PathVariable("date") String date);
}