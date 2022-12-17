package com.payconiq.stockmanagement.repository;

import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StockPriceHistoryRepositoryTest {

    @Autowired
    private StockPriceHistoryRepository stockPriceHistoryRepository;

    @Autowired
    private StockRepository stockRepository;

    private Stock stock1;

    @BeforeEach
    void setUp(){
        stock1=Stock.builder().
                name("Stock1").
                currentPrice(new BigDecimal(100)).
                build();
    }

    @Test
    void whenSaveStock_thenReturnSavedStock(){


        StockPriceHistory stockPriceHistory=StockPriceHistory.builder().
                stock(stock1).
                price(stock1.getCurrentPrice()).
                build();
        StockPriceHistory savedStockPriceHistory= stockPriceHistoryRepository.save(stockPriceHistory);
        assertNotNull(savedStockPriceHistory);
        assertThat(savedStockPriceHistory.getId()).isNotEqualTo(null);

    }

}