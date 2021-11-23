package com.example.currency.feign;

import com.example.currency.Constants;
import com.example.currency.response.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="Gif", url= "api.giphy.com/v1/gifs")
public interface GifService {

    @GetMapping(Constants.URL_GIFS_GOOD)
    public Gif getGoodGif();

    @GetMapping(Constants.URL_GIFS_BAD)
    public Gif getBadGif();
}