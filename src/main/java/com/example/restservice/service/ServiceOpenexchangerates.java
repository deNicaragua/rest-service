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
    @Value("${giphy.rich}")
    private String rich;
    @Value("${giphy.broke}")
    private String broke;
    private final LocalDate data = LocalDate.parse("2021-04-29");
    private final LocalDate yesterday = LocalDate.now().minusDays(1);


    @Autowired
    private OpenexchangeratesClient client;

    public String getLatestChanges() {
        try {
            return getRate(client.getLatest(appId, base, rates))
                    > getRate(client.getHistorical(yesterday, appId, base, rates))
                    ? rich : broke;
        } catch (NullPointerException ex) {
            return ex.getMessage();
        }
    }

    public String getHistoricalChanges() {
        try {
            return getRate(client.getLatest(appId, base, rates))
                    > getRate(client.getHistorical(data, appId, base, rates))
                    ? rich : broke;
        } catch (NullPointerException ex) {
            return ex.getMessage();
        }
    }

    public Double getRate(OpenexchangeModel object) {
        return object.getRates();
    }
}