package com.example.currency.controller;

import com.example.currency.Constants;
import com.example.currency.feign.CurrencyService;
import com.example.currency.feign.GifService;
import com.example.currency.response.Currency;
import com.example.currency.response.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Random;

@Controller
@RequestMapping("/currency")
public class MainController {
    CurrencyService currencyService;
    GifService gifService;

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setGifService(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping
    public ResponseEntity<?> getInfo() {
        Currency currencyUSDToday = currencyService.checkDollarToday();
        Currency currencyUSDYesterday = currencyService.checkDollarYesterday(LocalDate.now().minusDays(1).toString());
        Double dollarToday = currencyUSDToday.getRates().get(Constants.ISO_RUB);
        Double dollarYesterday = currencyUSDYesterday.getRates().get(Constants.ISO_RUB);
        Gif gif = (dollarToday > dollarYesterday) ? gifService.getGoodGif() : gifService.getBadGif();
        Random random = new Random();
        int gifId = random.nextInt(Integer.parseInt(Constants.LIMIT_GIF));
        String url = gif.getData()[gifId].getUrl();
        return ResponseEntity.ok(url);
    }
}