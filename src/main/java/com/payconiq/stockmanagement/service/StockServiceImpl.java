package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
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
    public Long addStock(ReqStockDto reqStockDto) {
        Stock stock=new Stock();
        stock= modelMapper.map(reqStockDto,Stock.class);
        log.info("Stock added : "+  stock.toString());

        return stockRepository.save(stock).getId();
    }

    @Override
    public ResStockDto updateStockPrice(Long id ,ReqStockDto reqStockDto) {
        Stock savedStock =stockRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        String.format("Can not find Stock by id %s",reqStockDto.getId())
                ));
        log.info("The price of stock by id: {}  updated from: {} to: {} ",
                savedStock.getId(),
                savedStock.getCurrentPrice(),
                reqStockDto.getCurrentPrice());
        savedStock.setCurrentPrice(reqStockDto.getCurrentPrice());
        return modelMapper.map(stockRepository.save(savedStock),ResStockDto.class);
    }
}
