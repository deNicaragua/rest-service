package com.example.restservice;

import com.example.restservice.model.GiphyModel;
import com.example.restservice.service.ServiceGiphy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestController.class)
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestController controller;
    @MockBean
    private ServiceGiphy gifService;

    @Test
    public void whenRich() throws Exception {
        GiphyModel gif = new GiphyModel(new GiphyModel.Meta());
        gif.getMeta().setMsg("rich");
        ResponseEntity<GiphyModel> responseEntity = new ResponseEntity<>(gif, HttpStatus.OK);
        when(gifService.getRich()).thenReturn(gif);
        when(controller.getResult()).thenReturn(responseEntity);
        mockMvc.perform(get("/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meta.msg").value("rich"));
    }

    @Test
    public void whenBroke() throws Exception {
        GiphyModel gif = new GiphyModel(new GiphyModel.Meta());
        gif.getMeta().setMsg("broke");
        ResponseEntity<GiphyModel> responseEntity = new ResponseEntity<>(gif, HttpStatus.OK);
        when(gifService.getRich()).thenReturn(gif);
        when(controller.getResult()).thenReturn(responseEntity);
        mockMvc.perform(get("/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meta.msg").value("broke"));
    }

    @Test
    public void whenNull() throws Exception {
        GiphyModel gif = new GiphyModel(new GiphyModel.Meta());
        gif.getMeta().setMsg("error");
        ResponseEntity<GiphyModel> responseEntity = new ResponseEntity<>(gif, HttpStatus.BAD_REQUEST);
        when(gifService.getRich()).thenReturn(gif);
        when(controller.getResult()).thenReturn(responseEntity);
        mockMvc.perform(get("/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.meta.msg").value("error"));
    }
}
