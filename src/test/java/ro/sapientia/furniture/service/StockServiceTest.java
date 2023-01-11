package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.mock.DatabaseMock;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.StockRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static ro.sapientia.furniture.mock.DatabaseMock.manufacturerLocationsWithOneManufacturer;
import static ro.sapientia.furniture.mock.DatabaseMock.stockWithOneProduct;


public class StockServiceTest {

    private StockRepository repositoryMock;
    private StockService service;
    private List<Stock> stockListWithOneProduct = new ArrayList<Stock>(Arrays.asList(
            new Stock(1L, "kilincs", 500, null)
    ));
    private List<Stock> stocks = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        repositoryMock = mock(StockRepository.class);
        service = new StockService(repositoryMock);
        stocks.add(new Stock(
                1L,
                "kilincs",
                500,
                null
        ));
        stocks.add(new Stock(
                2L,
                "fogantyu",
                555,
                null
        ));
    }

    @Test
    public void findAllProducts_emptyListTest(){
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<Stock> stocks = service.findAllProducts(1L);

        assertEquals(0, stocks.size());
    }

    @Test
    public void findAllStocks_oneShouldSucceedTest(){
        when(repositoryMock.findAll()).thenReturn(DatabaseMock.stockWithOneProduct);
        DatabaseMock.stockWithOneProduct = service.findAllProducts(1L);
        Assertions.assertEquals(1,DatabaseMock.stockWithOneProduct.size());
    }

    @Test
    public  void findAllProducts_nullTest(){
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Stock> stocks = service.findAllProducts(1L);

        assertNull(stocks);
    }

//    @Test
//    public void findProductByIdShouldSucceedTest(){
//        when(repositoryMock.findProductById(anyLong())).thenReturn(DatabaseMock.stockWithOneProduct.get(0));
//        Assertions.assertDoesNotThrow(() -> {
//            Stock stock = service.findAllProducts(DatabaseMock.stockWithOneProduct.get(0).getId());
//
//            assertNotNull(stock);
//            Assertions.assertEquals(DatabaseMock.stockWithOneProduct.get(0), stock);
//        });
//    }

    @Test
    public void findProductByIdShouldSuccess(){
        when(repositoryMock.findProductById(anyLong())).thenReturn(null);
        Stock stock = service.findProductById(1L);
        Assertions.assertNull(stock);
    }

    @Test
    public void createStockTest() {
        when(repositoryMock.saveAndFlush(stocks.get(0)))
                .thenReturn(stocks.get(0));

        service.create(stocks.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(stocks.get(0));
    }

    @Test
    public void createStockShouldSucceedTest(){
        when(repositoryMock.saveAndFlush(any(Stock.class))).thenReturn(DatabaseMock.stockWithOneProduct.get(0));
        Stock stock = service.create(DatabaseMock.stockWithOneProduct.get(0));

        assertNotNull(stock.getProduct());
        assertNotNull(stock.getCount());
        assertNotNull(stock.getManufacturerLocation());
    }

    @Test
    public void updateStockShouldSucceedTest(){
        Stock stock = DatabaseMock.stockWithOneProduct.get(0);
        stock.setProduct("stock is modified");

        when(repositoryMock.saveAndFlush(any(Stock.class))).thenReturn(DatabaseMock.stockWithOneProduct.get(0));
        Stock stock1 = service.update(stock);

        Assertions.assertEquals(stock1.getProduct(), stock.getProduct());
    }

    @Test
    public void deleteFromStockShouldSucceedTest(){
        when(repositoryMock.findProductById(anyLong())).thenReturn(DatabaseMock.stockWithOneProduct.get(0));
        Assertions.assertDoesNotThrow(() -> {
            service.delete(stockWithOneProduct.get(0).getId());
        });

        verify(repositoryMock, times(1)).deleteById(stockWithOneProduct.get(0).getId());
    }
}
