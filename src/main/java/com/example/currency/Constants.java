package com.example.currency;

public class Constants {
    public static final String ISO_RUB = "RUB";
    public static final String LIMIT_GIF = "25";

    public static final String URL_USD_TODAY = "/latest.json?app_id=${currency.token}&base=${base.currency}&symbols={symbols}";
    public static final String URL_USD_YESTERDAY = "/historical/{date}.json?app_id=${currency.token}&base=${base.currency}&symbols={symbols}";
    public static final String URL_GIFS_GOOD = "/search?api_key=${gif.token}&q=rich&limit=" + LIMIT_GIF;
    public static final String URL_GIFS_BAD = "/search?api_key=${gif.token}&q=broke&limit=" + LIMIT_GIF;
}