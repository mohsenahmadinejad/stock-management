package com.payconiq.stockmanagement.service;

import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;
import com.payconiq.stockmanagement.exception.StockNotFoundException;
import com.payconiq.stockmanagement.repository.StockPriceHistoryRepository;
import com.payconiq.stockmanagement.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatcher.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockPriceHistoryService stockPriceHistoryService;

    @Mock
    private ModelMapper modelMapper;


    private Stock stock1;

    @BeforeEach
    void setUp(){
        stock1=Stock.builder().
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();

    }

    @Test
    void whenAddNewStock_thenReturnNewStock(){
         stock1= Stock.builder().
                 name("Stock1").
                 currentPrice(new BigDecimal(101)).
                 build();
         ReqStockDto stockDto=new ReqStockDto("Stock1",new BigDecimal(101));

         when(stockRepository.save(any(Stock.class))).thenReturn(stock1);
         when(stockPriceHistoryService.saveToStockPriceHistory(any(Stock.class))).thenReturn(null);
         when(modelMapper.map(stockDto,Stock.class)).thenReturn(stock1);

         Stock newStock=stockService.addStock(stockDto);

         assertNotNull(newStock);
         assertEquals(newStock.getName(),"Stock1");

 }
    @Test
    @DisplayName("By pass stockId should return the saved stock")
    void getStockById(){
        stock1= Stock.builder().
                id(1L).
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock1));
        Stock savedStock=stockService.getStockById(1L);

        assertNotNull(savedStock);
        assertEquals(savedStock.getId(),1L);
    }


    @Test
    @DisplayName("By pass stockId that is not in database should return 'StockNotFoundException' ")
    void getStockByIdWithException(){
        assertThrows(StockNotFoundException.class,()->{
            stockService.getStockById(2L);
        });
    }

    @Test
    @DisplayName("By pass stockId and stockPrice should return the updated stock with new price")
    void updateStockPrice(){
         stock1= Stock.builder().
                 id(1L).
                 name("Stock1").
                 currentPrice(new BigDecimal(101)).
                 build();

        Stock  Updatedstock1= Stock.builder().
                id(1L).
                name("Stock1").
                currentPrice(new BigDecimal(200)).
                build();



        ReqStockDto reqStockDto=new ReqStockDto("Stock1",new BigDecimal(200));
        ResStockDto newResStockDto=new ResStockDto("Stock1",new BigDecimal(200));

        when(stockRepository.findById(any(Long.class))).thenReturn(Optional.of(stock1));
         when(stockRepository.save(any(Stock.class))).thenReturn(stock1);
         when(stockPriceHistoryService.saveToStockPriceHistory(any(Stock.class))).thenReturn(null);

         when(modelMapper.map(Updatedstock1,ResStockDto.class)).thenReturn(newResStockDto);


        ResStockDto resStockDto=stockService.updateStockPrice(1L,reqStockDto);

         assertNotNull(resStockDto);
         assertEquals(resStockDto.getCurrentPrice(),new BigDecimal(200));

 }

    @Test
    @DisplayName("By pass stockId should delete the saved stock")
    void deleteStock(){
        stock1= Stock.builder().
                id(1L).
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();
        doNothing().when(stockRepository).deleteById(anyLong());

        stockService.deleteStockByID(1L);
        verify(stockRepository, times(1)).deleteById(1L);

    }


}