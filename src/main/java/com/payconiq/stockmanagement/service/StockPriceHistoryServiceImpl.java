package com.payconiq.stockmanagement.service;

import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;
import com.payconiq.stockmanagement.repository.StockPriceHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockPriceHistoryServiceImpl implements StockPriceHistoryService{

    @Autowired
    private StockPriceHistoryRepository stockPriceHistoryRepository;

    @Override
    public StockPriceHistory saveToStockPriceHistory(Stock stock) {
        return stockPriceHistoryRepository.save(new StockPriceHistory(stock.getCurrentPrice(),stock));
    }
}
