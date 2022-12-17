package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import org.springframework.data.domain.Page;

import java.util.List;


public interface StockService {
    Stock getStockById(Long id);
    Page<Stock> getAllStocksWithPagination(int pageNumber , int pageSize);
    Long addStock(ReqStockDto stockDto);
    ResStockDto updateStockPrice(Long id ,ReqStockDto reqStockDto);
    void deleteBoard(Long id);

    Stock getStockByName(String name);
}
