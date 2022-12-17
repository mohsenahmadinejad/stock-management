package com.payconiq.stockmanagement.service;

import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;

public interface StockPriceHistoryService {
    StockPriceHistory saveToStockPriceHistory(Stock stock);
}
