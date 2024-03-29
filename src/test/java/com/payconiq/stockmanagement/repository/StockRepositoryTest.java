package com.payconiq.stockmanagement.repository;

import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    void initData(int rowCount){

        stockRepository.deleteAll();
        List<Stock> stockList= IntStream.rangeClosed(1,rowCount)
                .mapToObj(i-> new Stock("Stock"+i,new BigDecimal(new Random().nextInt(1000)))
                ).collect(Collectors.toList());
        stockRepository.saveAll(stockList);
    }

    private Stock stock1;
    private Stock stock2;

    @BeforeEach
    void setUp(){
        stock1=Stock.builder().
                name("Stock1").
                currentPrice(new BigDecimal(101)).
                build();

        stock2=Stock.builder().
                name("Stock2").
                currentPrice(new BigDecimal(102)).
                build();
    }

    @Test
    @DisplayName("It should save the stock into the database")
    void whenSaveStock_thenReturnSavedStock(){

        Stock savedStock= stockRepository.save(stock1);
        assertNotNull(savedStock);
        assertThat(savedStock.getId()).isNotEqualTo(null);

    }

    @Test
    void whenGetAllStocks_thenReturnAllStocks(){
        initData(100);
        List<Stock> stockList=stockRepository.findAll();
        assertNotNull(stockList);
        assertEquals(stockList.size(),100);

    }
    @Test
    void whenGetAllStocksWithPagination_thenReturnAllStocksWithPagination(){
        initData(100);
        Page<Stock> stockPage=stockRepository.findAll(PageRequest.of(2, 25));
        assertNotNull(stockPage);
        assertEquals(stockPage.getTotalElements(),100);
        assertEquals(stockPage.getTotalPages(),4);
        assertEquals(stockPage.getPageable().getPageSize(),25);
    }

    @Test
    void whenGetStocksById_thenReturnTheStock(){

        Stock savedStock1=stockRepository.save(stock1);
        Stock savedStock2=stockRepository.save(stock2);

        Stock foundedStock=stockRepository.findById(savedStock1.getId()).get();
        assertNotNull(foundedStock);
        assertEquals(foundedStock.getId(),savedStock1.getId());

        assertEquals(foundedStock.getName(),savedStock1.getName());
        assertNotEquals(foundedStock.getName(),savedStock2.getName());

        assertEquals(foundedStock.getCurrentPrice(),savedStock1.getCurrentPrice());
        assertNotEquals(foundedStock.getCurrentPrice(),savedStock2.getCurrentPrice());
    }

    @Test
    void whenUpdateStock_thenReturnUpdatedStock(){
        stockRepository.save(stock1);
        Stock savedStock=stockRepository.findById(stock1.getId()).get();
        savedStock.setCurrentPrice(new BigDecimal(200));
        Stock updatedStock= stockRepository.save(savedStock);

        assertEquals(updatedStock.getCurrentPrice(),new BigDecimal(200));

    }

    @Test
    void whenDeleteStock_thenTheStockeShouldBeDeleted(){
        stockRepository.deleteAll();

        stockRepository.save(stock1);
        stockRepository.save(stock2);


        stockRepository.delete(stock1);
        Optional<Stock> deletedStock=stockRepository.findById(stock1.getId());
        List<Stock> stockList=stockRepository.findAll();

        assertThat(deletedStock).isEmpty();
        assertEquals(1,stockList.size());
    }
    @Test
    void whenGetStocksByName_thenReturnTheStock(){

        stockRepository.save(stock1);
        stockRepository.save(stock2);

        Stock foundedStock=stockRepository.findByName(stock1.getName());
        assertNotNull(foundedStock);
        assertEquals(foundedStock.getId(),stock1.getId());

        assertEquals(foundedStock.getName(),stock1.getName());
        assertNotEquals(foundedStock.getName(),stock2.getName());

        assertEquals(foundedStock.getCurrentPrice(),stock1.getCurrentPrice());
        assertNotEquals(foundedStock.getCurrentPrice(),stock2.getCurrentPrice());
    }

}