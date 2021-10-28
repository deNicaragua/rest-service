package com.example.restservice;

import com.example.restservice.client.OpenexchangeratesClient;
import com.example.restservice.model.OpenexchangeModel;
import com.example.restservice.service.ServiceOpenexchangerates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class OpenexchangeServiceTests {

	@Autowired
	private ServiceOpenexchangerates service;

	@MockBean
	private OpenexchangeratesClient openexchangeratesClient;

	@Value("${openexchangerates.base}")
	private String base;
	@Value("${giphy.rich}")
	private String rich;
	@Value("${giphy.broke}")
	private String broke;
	private OpenexchangeModel openexchangeModel;
	private OpenexchangeModel openexchangeModelTwo;

	@BeforeEach
	public void init() {
		int timestamp = 1407982435;
		Map<String, Double> rates = new HashMap<>();
		rates.put("RUB", 71.9043);
		this.openexchangeModel = new OpenexchangeModel();
		this.openexchangeModel.setTimestamp(timestamp);
		this.openexchangeModel.setBase(base);
		this.openexchangeModel.setRates(rates);

		Map<String, Double> rate = new HashMap<>();
		rate.put("RUB", 79.9043);
		this.openexchangeModelTwo = new OpenexchangeModel();
		this.openexchangeModelTwo.setTimestamp(timestamp);
		this.openexchangeModelTwo.setBase(base);
		this.openexchangeModelTwo.setRates(rate);
	}

	@Test
	public void whenRichTest() {
		when(openexchangeratesClient.getLatest(anyString(), anyString(), anyString()))
				.thenReturn(this.openexchangeModelTwo);
		when(openexchangeratesClient.getHistorical(any(LocalDate.class), anyString(), anyString(), anyString()))
				.thenReturn(this.openexchangeModel);
		String resultLatest = service.getLatestChanges();
		String resultHistorical = service.getHistoricalChanges();
		assertEquals(rich, resultHistorical);
		assertEquals(rich, resultLatest);
	}

	@Test
	public void whenBrokeTest() {
		when(openexchangeratesClient.getLatest(anyString(), anyString(), anyString()))
				.thenReturn(this.openexchangeModel);
		when(openexchangeratesClient.getHistorical(any(LocalDate.class), anyString(), anyString(), anyString()))
				.thenReturn(this.openexchangeModelTwo);
		String resultLatest = service.getLatestChanges();
		String resultHistorical = service.getHistoricalChanges();
		assertEquals(broke, resultHistorical);
		assertEquals(broke, resultLatest);
	}

	@Test
	public void whenNullTest() {
		when(openexchangeratesClient.getLatest(anyString(), anyString(), anyString()))
				.thenReturn(null);
		when(openexchangeratesClient.getHistorical(any(LocalDate.class), anyString(), anyString(), anyString()))
				.thenReturn(null);
		String resultLatest = service.getLatestChanges();
		String resultHistorical = service.getHistoricalChanges();
		assertEquals("Something went wrong. Try again:)", resultHistorical);
		assertEquals("Something went wrong. Try again:)", resultLatest);
	}
}
