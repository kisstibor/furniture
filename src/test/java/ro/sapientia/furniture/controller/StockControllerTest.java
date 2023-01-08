package ro.sapientia.furniture.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.model.Stock;
import ro.sapientia.furniture.service.StockService;

@WebMvcTest(controllers = StockController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(StockService.class)
    private StockService stockService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        final Stock stock = new Stock();
        stock.setCount(333);
        when(stockService.findAllProducts()).thenReturn(List.of(stock));

        this.mockMvc.perform(get("/stock/all")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].count", is(333)));
    }

}
