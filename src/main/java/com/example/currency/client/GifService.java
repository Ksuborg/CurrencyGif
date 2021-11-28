package com.example.currency.client;

import com.example.currency.Constants;
import com.example.currency.response.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="gif", url= "${gif.url}")
public interface GifService {

    @GetMapping(Constants.URL_GIFS_SEARCH)
    Gif getGif(@PathVariable("q") String q);
}