package ro.sapientia.furniture;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.sapientia.furniture.controller.BillingEntityController;
import ro.sapientia.furniture.model.BillingEntity;
import ro.sapientia.furniture.service.BillingEntityService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BillingEntityController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class BillingEntityControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(BillingEntityService.class)
	private BillingEntityService billingEntityService;
	
	@Test
	public void testGetBillingEntityShouldFail() throws Exception {
		when(billingEntityService.findAllBillingEntities()).thenThrow(new NotFoundException());
		
		mockMvc.perform(get("/billingEntity/all"))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("status", is(400)))
			.andExpect(jsonPath("error", is("RECORD_NOT_FOUND")))
			.andExpect(jsonPath("message", is("BillingEntity table is empty")));
	}
}
