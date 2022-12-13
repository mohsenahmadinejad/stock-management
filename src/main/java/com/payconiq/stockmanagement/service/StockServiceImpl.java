package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.StockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).get();
    }

    @Override
    public Long addStock(StockDto stockDto) {
        Stock stock=new Stock();
        stock= modelMapper.map(stockDto,Stock.class);
        log.info("Stock added : "+  stock.toString());

        return stockRepository.save(stock).getId();
    }
}
