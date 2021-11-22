package com.example.restservice;

import com.example.restservice.model.GiphyModel;
import com.example.restservice.service.ServiceGiphy;
import com.example.restservice.service.ServiceOpenexchangerates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    ServiceOpenexchangerates serviceOpen;

    @Autowired
    ServiceGiphy serviceGiphy;


    @GetMapping("get")
    public ResponseEntity<GiphyModel> getResult()  {
        GiphyModel gif = null;
        String tag = serviceOpen.getLatestChanges();
        switch (tag) {
            case "rich":
                gif = serviceGiphy.getRich();
                break;
            case "broke":
                gif = serviceGiphy.getBroke();
                break;
            default:
                break;
        }
        return new ResponseEntity<>(gif, HttpStatus.OK);

    }
}
