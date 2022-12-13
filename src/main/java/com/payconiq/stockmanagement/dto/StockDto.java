package com.payconiq.stockmanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
 public class StockDto {

    private Long id;
    private String name;
    private BigDecimal currentPrice;

}
