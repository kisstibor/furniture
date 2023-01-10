package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.StockRepository;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAllProducts(long l) {
        return this.stockRepository.findAll();
    }

    public Stock findProductById(final Long id){
        return this.stockRepository.findProductById(id);
    }

    public Stock create(Stock stock){
        return this.stockRepository.saveAndFlush(stock);
    }

    public Stock update(Stock stock){
        return this.stockRepository.saveAndFlush(stock);
    }

    public void delete(Long id){
        this.stockRepository.deleteById(id);
    }

}
