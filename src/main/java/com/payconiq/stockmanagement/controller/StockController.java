package com.payconiq.stockmanagement.controller;


import com.fasterxml.jackson.databind.JsonSerializer;
import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "Returns the stock by id ",response = Stock.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Stock not found") })
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }
    @ApiOperation(value = "Returns the stock by name ",response = Stock.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Stock not found") })
    @GetMapping("/name/{name}")
    public ResponseEntity<Stock> getStockByName(@PathVariable String name) {
        return ResponseEntity.ok(stockService.getStockByName(name));
    }

    @ApiOperation(value = "Returns all stocks with pagination ",response = Page.class)
    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Stock>> getAllStocks(@PathVariable int pageNumber,
                                                    @PathVariable int pageSize) {
        return ResponseEntity.ok(stockService.getAllStocksWithPagination(pageNumber,pageSize));
    }

    @ApiOperation(value = "Add a Stock", response = Long.class)
    @PostMapping
    public ResponseEntity<Long> addStock(@RequestBody ReqStockDto reqStockDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.addStock(reqStockDto).getId());
    }

    @ApiOperation(value = "Update current stock price by id", response = ResStockDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Not positive amount"),
            @ApiResponse(code = 404, message = "Stock not found") })
    @PatchMapping("/{id}")
    public ResponseEntity<ResStockDto> updateStockPrice(@PathVariable Long id,
                                                        @RequestBody ReqStockDto stockDto) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.updateStockPrice(id,stockDto));
    }

    @ApiOperation(value = "Delete the stock by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStock(@PathVariable Long id) {
        stockService.deleteStockByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}