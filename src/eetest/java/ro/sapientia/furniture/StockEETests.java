package ro.sapientia.furniture;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ro.sapientia.furniture.mock.DatabaseMock;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Schedule;
import ro.sapientia.furniture.model.Stock;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class StockEETests {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestEntityManager entityManager;

    //private Set<Stock> stocks = new HashSet<>();

    @BeforeEach
    private void addElement() {
        Stock stock = new Stock();
        //stocks.add(stock);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("TestManufacturer");
        entityManager.persist(entityManager.merge(manufacturer));
        ManufacturerLocation manufacturerLocation = new ManufacturerLocation();
        manufacturerLocation.setId(1L);
        manufacturerLocation.setName("TestPlace1");
        manufacturerLocation.setAddress("TestAdress");
        manufacturerLocation.setManufacturer(manufacturer);
        entityManager.persist(entityManager.merge(manufacturerLocation));
        stock.setCount(130);
        stock.setManufacturerLocation(manufacturerLocation);
        stock.setProduct("desk");
        stock.setId(1L);
        //System.out.println(stock);
        entityManager.persist(entityManager.merge(stock));
        Stock stock1 = new Stock();
        // stocks.add(stock1);
        stock1.setCount(500);
        stock1.setManufacturerLocation(manufacturerLocation);
        stock1.setProduct("chair");
        stock1.setId(2L);
        entityManager.persist(entityManager.merge(stock1));
        entityManager.flush();
        System.out.println(stock1);


    }

    @Test
    void stockAll() throws Exception{
        addElement();
        mvc.perform(get("/stock/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].count", is(130)));
    }

    @Test
    public void testCreateStockSucceed() throws Exception {

        mvc.perform(post("/stock/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DatabaseMock.stockWithOneProduct.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product", is(DatabaseMock.stockWithOneProduct.get(0).getProduct())))
                .andExpect(jsonPath("$.count", is(DatabaseMock.stockWithOneProduct.get(0).getCount())));
    }

    @Test
    public void testDeleteFromStockByIdSucceed() throws Exception {
        addElement();
        mvc.perform(delete("/stock/delete/{id}", 2L))
                .andExpect(status().isOk());
    }
}
