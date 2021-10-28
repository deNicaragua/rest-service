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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GifyServiceTest {

    @Autowired
    private ServiceGiphy service;
    @MockBean
    private GiphyClient client;
    private final GiphyModel gif = new GiphyModel();
    private final GiphyModel broke =new GiphyModel();

    @BeforeEach
    public void init() {
        GiphyModel.Meta giphyMeta = new GiphyModel.Meta();
        GiphyModel.Meta giphyMetaTwo = new GiphyModel.Meta();
        giphyMeta.setMsg("OK");
        giphyMeta.setStatus(200);
        giphyMetaTwo.setMsg("NOT OK");
        giphyMetaTwo.setStatus(400);

        GiphyModel.Data giphyData = new GiphyModel.Data();
        GiphyModel.Data giphyDataTwo = new GiphyModel.Data();
        giphyData.setType("JSON");
        giphyData.setId("Sgrinlegr4DGGs");
        giphyDataTwo.setId(null);
        giphyDataTwo.setType(null);

        this.gif.setMeta(giphyMeta);
        this.gif.setData(giphyData);
        this.broke.setMeta(giphyMetaTwo);
        this.broke.setData(giphyDataTwo);
    }

    @Test
    public void whenRich() {
        when(client.raise(anyString(), anyString())).thenReturn(this.gif);
        Integer resultRich = service.getStatus(this.gif);
        String link = service.getRich();
        assertTrue(link.startsWith("https://media0.giphy"));
        assertTrue(link.endsWith("/giphy.gif"));
        assertEquals(200, resultRich);
    }

    @Test
    public void whenBroke() {
        when(client.lower(anyString(), anyString())).thenReturn(this.gif);
        Integer resultBroke = service.getStatus(this.gif);
        String link = service.getBroke();
        assertTrue(link.startsWith("https://media0.giphy"));
        assertTrue(link.endsWith("/giphy.gif"));
        assertEquals(200, resultBroke);
    }

    @Test
    public void whenNullReturn() {
        when(client.raise(anyString(), anyString())).thenReturn(null);
        when(client.lower(anyString(), anyString())).thenReturn(null);
        Integer statusRich = service.getStatus(this.broke);
        Integer statusBroke = service.getStatus(this.broke);
        String resultRich = service.getRich();
        String resultBroke = service.getBroke();
        assertEquals(400, statusRich);
        assertEquals(400, statusBroke);
        assertEquals("Service not available. Try again :)", resultRich);
        assertEquals("Service not available. Try again :)", resultBroke);
    }
}
