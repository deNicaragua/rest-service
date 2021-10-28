package com.example.restservice.service;

import com.example.restservice.client.GiphyClient;
import com.example.restservice.model.GiphyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceGiphy {
    @Value("${giphy.api_key}")
    private String api;
    @Value("${giphy.rich}")
    private String upper;
    @Value("${giphy.broke}")
    private String lower;

    @Autowired
    private GiphyClient giphy;

//узнать ссылку запроса
    public String getRich() {
        try {
            return parseLink(giphy.raise(api, upper));
        } catch (NullPointerException ex) {
            return "Service not available. Try again :)";
        }
    }

    public String getBroke() {
        try {
            return parseLink(giphy.lower(api, lower));
        } catch (NullPointerException ex) {
            return "Service not available. Try again :)";
        }
    }

    public String parseLink(GiphyModel response) {
        return response.getData().getId();
    }

    public Integer getStatus(GiphyModel response) {
        return response.getMeta().getStatus();
    }

}
