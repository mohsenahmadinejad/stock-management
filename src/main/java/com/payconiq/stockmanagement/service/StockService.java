package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.StockDto;
import com.payconiq.stockmanagement.entity.Stock;


public interface StockService {
    Stock getStockById(Long id);
    Long addStock(StockDto stockDto);
}
