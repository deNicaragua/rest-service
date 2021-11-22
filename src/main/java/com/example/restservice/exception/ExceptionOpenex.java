package com.example.restservice.exception;

import com.example.restservice.model.GiphyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionOpenex {

    @ExceptionHandler
    public ResponseEntity<GiphyModel> handleNullPointerException(NullPointerException ex) {
        GiphyModel.Meta meta = new GiphyModel.Meta();
        meta.setMsg("Check the settings openexchangerates request");
        return new ResponseEntity<>(new GiphyModel(meta), HttpStatus.BAD_REQUEST);
    }
}
