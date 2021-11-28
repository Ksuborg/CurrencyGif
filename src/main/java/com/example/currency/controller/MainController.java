package com.example.currency.controller;

import com.example.currency.Constants;
import com.example.currency.Utils;
import com.example.currency.client.CurrencyService;
import com.example.currency.client.GifService;
import com.example.currency.response.Currency;
import com.example.currency.response.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
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

    @GetMapping()
    public ResponseEntity<?> getInfo() {
        String urlGif = getURLGif(Constants.ISO_RUB);
        return ResponseEntity.ok(urlGif);
    }

    @GetMapping("/{symbols}")
    public ResponseEntity<?> getInfo(@PathVariable("symbols") String symbols) {
        if (Utils.chekSymbols(symbols)) {
            return ResponseEntity.badRequest().body("invalid currency code");
        }
        String urlGif = getURLGif(symbols);
        return urlGif.equals("invalid currency code") ? ResponseEntity.badRequest().body(urlGif) : ResponseEntity.ok(urlGif);
    }

    public String getURLGif(String symbols) {
        Currency currencyUSDToday = currencyService.checkDollarToday(symbols);
        Currency currencyUSDYesterday = currencyService.checkDollarYesterday(LocalDate.now().minusDays(1).toString(), symbols);
        if (currencyUSDToday.getRates().size() == 0 && currencyUSDYesterday.getRates().size() == 0) {
            return "invalid currency code";
        }
        Double dollarToday = currencyUSDToday.getRates().get(symbols);
        Double dollarYesterday = currencyUSDYesterday.getRates().get(symbols);

        Gif gif = (dollarToday < dollarYesterday) ? gifService.getGif("rich") : gifService.getGif("broke");
        Random random = new Random();
        int gifId = random.nextInt(Integer.parseInt(Constants.LIMIT_GIF));
        return gif.getData()[gifId].getUrl();
    }
}