package com.payconiq.stockmanagement.service;



import com.payconiq.stockmanagement.dto.ReqStockDto;
import com.payconiq.stockmanagement.dto.ResStockDto;
import com.payconiq.stockmanagement.entity.Stock;
import com.payconiq.stockmanagement.entity.StockPriceHistory;
import com.payconiq.stockmanagement.exception.StockNotFoundException;
import com.payconiq.stockmanagement.repository.StockPriceHistoryRepository;
import com.payconiq.stockmanagement.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockPriceHistoryService stockPriceHistoryService ;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElseThrow(()->new StockNotFoundException(
                String.format("Can not find Stock by id %s",id)
        ));
    }

    @Override
    public Page<Stock> getAllStocksWithPagination(int pageNumber , int pageSize) {
        return stockRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Stock addStock(ReqStockDto reqStockDto) {
        Stock stock=new Stock();
        stock= modelMapper.map(reqStockDto,Stock.class);
        log.info("Stock added : "+  stock.toString());

        stockRepository.save(stock).getId();
        stockPriceHistoryService.saveToStockPriceHistory(stock);
        return stock;
    }

    @Override
    public ResStockDto updateStockPrice(Long id ,ReqStockDto reqStockDto) {

        Stock savedStock =stockRepository.findById(id)
                .orElseThrow(()->new StockNotFoundException(
                        String.format("Can not find Stock by id %s",reqStockDto.getId())
                ));

        log.info("The price of stock by id: {}  updated from: {} to: {} ",
                savedStock.getId(),
                savedStock.getCurrentPrice(),
                reqStockDto.getCurrentPrice());

        savedStock.setCurrentPrice(reqStockDto.getCurrentPrice());
        stockPriceHistoryService.saveToStockPriceHistory(savedStock);
        return modelMapper.map(stockRepository.save(savedStock),ResStockDto.class);
    }

    @Override
    public void deleteStockByID(Long id) {
        log.info("Stock by id: {} deleted...",id);
        stockRepository.deleteById(id);
    }

    @Override
    public Stock getStockByName(String name) {
        return stockRepository.findByName(name);
    }



}
