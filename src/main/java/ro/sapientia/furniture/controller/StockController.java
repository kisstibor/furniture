package ro.sapientia.furniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllProducts(){
        return new ResponseEntity<>(stockService.findAllProducts(1L), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock){
        return new ResponseEntity<>(stockService.create(stock), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock){
        return new ResponseEntity<>(stockService.update(stock), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductFromStock(@PathVariable("id") Long id){
        stockService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
