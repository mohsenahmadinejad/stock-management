package com.payconiq.stockmanagement.controller;


import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.service.StockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Stock>> getAllBoards(@PathVariable int pageNumber,
                                                    @PathVariable int pageSize) {
        return ResponseEntity.ok(stockService.getAllStocksWithPagination(pageNumber,pageSize));
    }

    @ApiOperation(value = "Add a Stock", response = Long.class)
    @PostMapping
    public ResponseEntity<Long> addStock(@RequestBody ReqStockDto reqStockDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.addStock(reqStockDto));
    }

    @ApiOperation(value = "Update price of stock", response = Long.class)
    @PatchMapping("/{id}")
    public ResponseEntity<ResStockDto> updateStockPrice(@PathVariable Long id,
                                                        @RequestBody ReqStockDto stockDto) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.updateStockPrice(id,stockDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStock(@PathVariable Long id) {
        stockService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}