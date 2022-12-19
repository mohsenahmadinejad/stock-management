package com.payconiq.stockmanagement.integration;

import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private StockRepository stockRepository;

    @BeforeAll
    public static void init(){
        restTemplate=new RestTemplate();
    }


    @BeforeEach
    public void beforeSetup(){
        baseUrl =baseUrl+":"+port+"/api/stock";
    }


    @Test
    void addStock_getSock_deleteStock() {
        Stock stock1 = Stock.builder().
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();
        Stock stock2 = Stock.builder().
                name("Stock2").
                currentPrice(new BigDecimal(102)).
                build();
        ReqStockDto reqStockDto1 = new ReqStockDto("Stock1", new BigDecimal(101));
        ReqStockDto reqStockDto2 = new ReqStockDto("Stock2", new BigDecimal(102));

        Long id1 = restTemplate.postForObject(baseUrl, reqStockDto1, Long.class);
        Long id2 = restTemplate.postForObject(baseUrl, reqStockDto2, Long.class);
        assertNotNull(id1);
        assertNotNull(id2);


        Stock stock=  restTemplate.getForObject(baseUrl+"/"+id1,Stock.class);
        assertNotNull(stock);
        assertEquals("Stock1",stock.getName());

        int rowCountBeforeDelete=stockRepository.findAll().size();
        restTemplate.delete(baseUrl+"/"+id1);
        int rowCountAfterDelete=stockRepository.findAll().size();
        assertEquals(1,rowCountBeforeDelete-rowCountAfterDelete);

        rowCountBeforeDelete=stockRepository.findAll().size();
        restTemplate.delete(baseUrl+"/"+id2);
        rowCountAfterDelete=stockRepository.findAll().size();
        assertEquals(1,rowCountBeforeDelete-rowCountAfterDelete);

    }

}
