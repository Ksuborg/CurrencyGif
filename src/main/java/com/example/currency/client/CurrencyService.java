package com.example.currency.client;

import com.example.currency.Constants;
import com.example.currency.response.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="exchange-rates", url= "${currency.url}")
public interface CurrencyService {

    @GetMapping(Constants.URL_USD_TODAY)
    Currency checkDollarToday(@PathVariable("symbols") String symbols);

    @GetMapping(Constants.URL_USD_YESTERDAY)
    Currency checkDollarYesterday(@PathVariable("date") String date, @PathVariable("symbols") String symbols);
}