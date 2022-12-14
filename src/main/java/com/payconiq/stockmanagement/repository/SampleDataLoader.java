package com.payconiq.stockmanagement.repository;

import com.github.javafaker.Faker;
import com.payconiq.stockmanagement.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading Sample Data...");

//        List<Stock> stockList= IntStream.rangeClosed(1,100)
//                .mapToObj(i-> new Stock(faker.stock().nsdqSymbol(),new BigDecimal(new Random().nextInt(1000)))
//                ).collect(Collectors.toList());
//        stockRepository.saveAll(stockList);
    }
}
