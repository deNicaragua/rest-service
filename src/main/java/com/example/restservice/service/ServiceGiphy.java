package com.example.restservice.service;

import com.example.restservice.client.GiphyClient;
import com.example.restservice.model.GiphyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceGiphy {
    @Value("${giphy.api_key}")
    private String api;
    @Value("${giphy.rich}")
    private String upper;
    @Value("${giphy.broke}")
    private String broke;

    @Autowired
    private GiphyClient giphy;

    public GiphyModel getRich() {
        return giphy.raise(api, upper);
    }

    public GiphyModel getBroke() {
        return giphy.lower(api, broke);
    }

}
