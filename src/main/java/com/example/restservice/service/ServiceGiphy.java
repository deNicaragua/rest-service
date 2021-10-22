package com.example.restservice.service;

import com.example.restservice.client.GiphyClient;
import com.example.restservice.model.GiphyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServiceGiphy {
    @Value("${giphy.api_key}")
    private String api;
    @Value("${giphy.upper}")
    private String upper;
    @Value("${giphy.lower}")
    private String lower;

    @Autowired
    GiphyClient giphy;

//узнать ссылку запроса
    public String getRich() {
        return parseLink(giphy.raise(api, upper));
    }

    public String getBroke() {
        return parseLink(giphy.lower(api, lower));
    }

    public String parseLink(GiphyModel response) {
        return response.getData().getId();
    }

}
