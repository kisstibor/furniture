package ro.sapientia.furniture.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.StockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.elasticsearch.index.store.Store.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


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
        final List<Stock> stocks = service.findAllProducts();

        assertEquals(0, stocks.size());
    }

    @Test
    public  void findAllProducts_nullTest(){
        when(repositoryMock.findAll()).thenReturn(null);
        final List<Stock> stocks = service.findAllProducts();

        assertNull(stocks);
    }

    @Test
    public void createStockTest() {
        when(repositoryMock.saveAndFlush(stocks.get(0)))
                .thenReturn(stocks.get(0));

        service.create(stocks.get(0));

        verify(repositoryMock, times(1)).saveAndFlush(stocks.get(0));
    }


}
