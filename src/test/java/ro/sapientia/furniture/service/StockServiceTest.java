package ro.sapientia.furniture.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.repository.StockRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StockServiceTest {

    private StockRepository repositoryMock;

    private StockService service;

    @BeforeEach
    public void setUp(){
        repositoryMock = mock(StockRepository.class);
        service = new StockService(repositoryMock);
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
}
