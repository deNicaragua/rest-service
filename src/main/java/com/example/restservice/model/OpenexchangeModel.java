package com.example.restservice.model;

import java.util.Map;


public class OpenexchangeModel {

    private Integer timestamp;

    private String base;

    private Map<String, Double> rates;

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Double getRates() {
        return rates.get("RUB");
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

}


