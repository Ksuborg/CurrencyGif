package com.example.currency;

public class Constants {
    public static final String API_TOKEN_CURRENCY = "e6a3010a2c8c4e09ae3948c90180745a";
    public static final String API_TOKEN_GIF = "U8ZmwQH7SLzlk7SwgXao6gV0LiAY2wDb";
    public static final String ISO_RUB = "RUB";
    public static final String ISO_USD = "USD";
    public static final String LIMIT_GIF = "25";

    public static final String URL_USD_TODAY = "/latest.json?app_id=" + API_TOKEN_CURRENCY + "&base=" + ISO_USD + "&symbols=" + ISO_RUB;
    public static final String URL_USD_YESTERDAY = "/historical/{date}.json?app_id=" + API_TOKEN_CURRENCY + "&base=" + ISO_USD + "&symbols=" + ISO_RUB;
    public static final String URL_GIFS_GOOD = "/search?api_key=" + API_TOKEN_GIF + "&q=rich&limit=" + LIMIT_GIF;
    public static final String URL_GIFS_BAD = "/search?api_key=" + API_TOKEN_GIF +"&q=broke&limit=" + LIMIT_GIF;
}