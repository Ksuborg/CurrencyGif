package com.example.currency.client;

import com.example.currency.Constants;
import com.example.currency.response.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="gif", url= "${gif.url}")
public interface GifService {

    @GetMapping(Constants.URL_GIFS_GOOD)
    Gif getGoodGif();

    @GetMapping(Constants.URL_GIFS_BAD)
    Gif getBadGif();
}