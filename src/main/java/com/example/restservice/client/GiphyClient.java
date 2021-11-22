package com.example.restservice.client;

import com.example.restservice.model.GiphyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client", url = "${giphy.url}")
public interface GiphyClient {

     @GetMapping(consumes = "application/json")
     GiphyModel raise(@RequestParam("api_key") String api, @RequestParam("tag") String tag);

     @GetMapping(consumes = "application/json")
     GiphyModel lower(@RequestParam("api_key") String api, @RequestParam("tag") String tag);

}
