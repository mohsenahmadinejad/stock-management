package com.payconiq.stockmanagement.controller;


import com.payconiq.stockmanagement.dto.StockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }
    @PostMapping
    public ResponseEntity<Long> addStock(@RequestBody StockDto stockDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.addStock(stockDto));
    }
}