package com.example.restservice;

import com.example.restservice.service.ServiceGiphy;
import com.example.restservice.service.ServiceOpenexchangerates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class Controller {

    @Autowired
    ServiceOpenexchangerates serviceOpen;

    @Autowired
    ServiceGiphy serviceGiphy;

    @GetMapping(path = "/get")
    public String getRes() throws URISyntaxException {
        String gif = "";
        switch (serviceOpen.getd()) {
            case 1:
                gif = serviceGiphy.getRich();
                break;
            case -1:
                gif = serviceGiphy.getBroke();
                break;
        }
        return "<img src=\"" + gif + "\" style=\"width:600px;height:400px\" alt=\"The Logo\">";
    }
}
