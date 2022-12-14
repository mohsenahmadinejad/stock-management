package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;


public interface StockService {
    Stock getStockById(Long id);
    Long addStock(ReqStockDto stockDto);

    ResStockDto updateStockPrice(Long id ,ReqStockDto reqStockDto);

}
