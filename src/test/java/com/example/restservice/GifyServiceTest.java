package com.example.restservice;

import com.example.restservice.client.GiphyClient;
import com.example.restservice.model.GiphyModel;
import com.example.restservice.service.ServiceGiphy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GifyServiceTest {

    @Autowired
    private ServiceGiphy service;
    @MockBean
    private GiphyClient client;
    private final GiphyModel richModel = new GiphyModel();
    private final GiphyModel brokeModel = new GiphyModel();
    private GiphyModel.Data data = new GiphyModel.Data();
    private GiphyModel.Meta meta = new GiphyModel.Meta();

    @BeforeEach
    public void init() {
        this.richModel.setData(data);
        this.brokeModel.setData(data);
        this.richModel.setMeta(meta);
        this.brokeModel.setMeta(meta);
    }

    @Test
    public void whenRich() {
        richModel.getData().setId("spvp4lkfspkwfe4");
        richModel.getMeta().setStatus(200);
        when(client.raise(anyString(), anyString())).thenReturn(richModel);
        GiphyModel result = service.getRich();
        assertEquals(result.getMeta().getStatus(),richModel.getMeta().getStatus());
        assertEquals(result.getData().getId(), richModel.getData().getId());
    }

    @Test
    public void whenBroke() {
        brokeModel.getData().setId("asrl5550903lk");
        brokeModel.getMeta().setStatus(200);
        when(client.lower(anyString(), anyString())).thenReturn(brokeModel);
        GiphyModel result = service.getBroke();
        assertEquals(result.getMeta().getStatus(),brokeModel.getMeta().getStatus());
        assertEquals(result.getData().getId(),brokeModel.getData().getId());
    }

    @Test
    public void whenNullReturn() {
        richModel.getMeta().setMsg("feignError");
        brokeModel.getMeta().setMsg("feignError");
        richModel.getMeta().setStatus(400);
        brokeModel.getMeta().setStatus(400);
        when(client.raise(anyString(), anyString())).thenReturn(richModel);
        when(client.lower(anyString(), anyString())).thenReturn(brokeModel);
        GiphyModel resultRich = service.getRich();
        GiphyModel resultBroke = service.getBroke();
        assertEquals(resultRich.getMeta().getStatus(),richModel.getMeta().getStatus());
        assertEquals(resultBroke.getMeta().getStatus(),brokeModel.getMeta().getStatus());
        assertEquals("feignError", resultRich.getMeta().getMsg());
        assertEquals("feignError", resultBroke.getMeta().getMsg());
    }
}
