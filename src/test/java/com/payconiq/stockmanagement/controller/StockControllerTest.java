package com.payconiq.stockmanagement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;


@WebMvcTest
class StockControllerTest {

    @MockBean
    private StockService stockService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Stock stock1;
    @BeforeEach
    void setUp(){
        stock1=Stock.builder().
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();

    }

    @Test
    void whenAddStock_thenReturnSavedStock() throws Exception {
        stock1= Stock.builder().
                id(1L).
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();

        ReqStockDto stockDto=new ReqStockDto("Stock1",new BigDecimal(101));

        when(stockService.addStock(any(ReqStockDto.class))).thenReturn(stock1);
        this.mockMvc.perform(post("/api/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("By pass stockId should return the saved stock")
    void getStockById() throws Exception {
        stock1= Stock.builder().
                id(1L).
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();
        when(stockService.getStockById(anyLong())).thenReturn(stock1);
        mockMvc.perform(get("/api/stock/{id}",1L))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.name", is(stock1.getName())))
                .andExpect( jsonPath("$.currentPrice",is(stock1.getCurrentPrice().intValue())));
    }

    @Test
    @DisplayName("By pass stockId should delete the saved stock")
    void deleteStockById() throws Exception {

        doNothing().when(stockService).deleteStockByID(anyLong());
        mockMvc.perform(delete("/api/stock/{id}",1L))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("By pass stockId and stockPrice should return the updated stock with new price")
    void updateStockPrice() throws Exception {

        ReqStockDto reqStockDto=new ReqStockDto("Stock1",new BigDecimal(200));
        ResStockDto newResStockDto=new ResStockDto("Stock1",new BigDecimal(200));


        when(stockService.updateStockPrice(anyLong(),any(ReqStockDto.class)))
                .thenReturn(newResStockDto);
        mockMvc.perform(patch("/api/stock/{id}",1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(reqStockDto)))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.currentPrice",is(reqStockDto.getCurrentPrice().intValue())));
    }

}