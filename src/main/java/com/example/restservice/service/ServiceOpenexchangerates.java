package com.example.restservice.service;

import com.example.restservice.client.OpenexchangeratesClient;
import com.example.restservice.model.OpenexchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ServiceOpenexchangerates {
    @Value("${openexchangerates.id}")
    private String appId;
    @Value("${openexchangerates.base}")
    private String base;
    @Value("${openexchangerates.rates}")
    private String rates;
    private final LocalDate data = LocalDate.now().minusDays(1);

    @Autowired
    private OpenexchangeratesClient client;

    //реализовать механизм сравнения вчерашнего и сегодняшнего курса
    public int getd() {
        return getRate(client.getLatest(appId, base, rates))
                > getRate(client.getYesterday(data, appId, base, rates))
                ? -1 : 1;
    }

    public int getResult() {
        System.out.println(getRate(client.getLatest(appId, base, rates)));
        return getRate(client.getLatest(appId, base, rates))
               > getRate(client.getHistorical(appId, base, rates))
               ? -1 : 1;
    }

    public Double getRate(OpenexchangeModel object) {
        return object.getRates();
    }
}
