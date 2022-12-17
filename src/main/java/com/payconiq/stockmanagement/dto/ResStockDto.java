package com.payconiq.stockmanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
 public class ResStockDto {

    private Long id;
    private String name;
    private BigDecimal currentPrice;

    public ResStockDto(String name, BigDecimal currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }
}
