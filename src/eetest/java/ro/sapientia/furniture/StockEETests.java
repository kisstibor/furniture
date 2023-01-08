package ro.sapientia.furniture;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Stock;

import java.util.HashSet;
import java.util.Set;

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

//    @Test
//    public void testCreateStockSucceed() throws Exception {
//        mvc.perform((RequestBuilder) post("/stock/create")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(stocks.))
//                .andExpect(jsonPath("$.address", is(locations.get(0).getAddress())));
//    }
    @Test
    public void testDeleteFromStockByIdSucceed() throws Exception {
        addElement();
        mvc.perform(delete("/stock/delete/{id}", 2L))
                .andExpect(status().isOk());
    }
}
