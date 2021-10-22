package com.example.restservice.client;

import com.example.restservice.model.OpenexchangeModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;



@FeignClient(name = "restClient", url = "${openexchangerates.url}")
public interface OpenexchangeratesClient {

    @GetMapping(path = "/latest.json",
            consumes = "application/json")
    OpenexchangeModel getLatest(@RequestParam("app_id") String appId, @RequestParam("") String base, @RequestParam("") String symbols);

    @GetMapping(path = "/historical/${data}.json", consumes = "application/json")
    OpenexchangeModel getHistorical(@RequestParam("app_id") String appId, @RequestParam("") String base, @RequestParam("") String symbols);

    @GetMapping(path = "/historical/{yesterday}.json", consumes = "application/json")
    OpenexchangeModel getYesterday(@PathVariable("") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate yesterday, @RequestParam("app_id") String appId, @RequestParam("") String base, @RequestParam("") String symbols);
}
