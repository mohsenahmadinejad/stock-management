package com.payconiq.stockmanagement.repository;


import com.payconiq.stockmanagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    public Stock findByName(String name);

}