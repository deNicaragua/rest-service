package com.example.restservice.exception;

import com.example.restservice.model.GiphyModel;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionGiphy {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<GiphyModel> handleFeignStatusException(FeignException e) {
        GiphyModel.Meta meta = new GiphyModel.Meta();
        meta.setMsg("FeignClient error");
        return new ResponseEntity<>(new GiphyModel(meta), HttpStatus.BAD_REQUEST);
    }
}
