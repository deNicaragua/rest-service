package com.example.restservice;

import com.example.restservice.service.ServiceGiphy;
import com.example.restservice.service.ServiceOpenexchangerates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    ServiceOpenexchangerates serviceOpen;

    @Autowired
    ServiceGiphy serviceGiphy;

    @GetMapping(path = "/get")
    public String getResult() throws URISyntaxException {
        String gif = "";
        switch (serviceOpen.getLatestChanges()) {
            case "rich":
                gif = serviceGiphy.getRich();
                break;
            case "broke":
                gif = serviceGiphy.getBroke();
                break;
            default:
                gif = "Nothing";
        }
        return "<img src=\"" + gif + "\" style=\"width:500px;height:400px\" alt=\"The Logo\">";
    }
}
