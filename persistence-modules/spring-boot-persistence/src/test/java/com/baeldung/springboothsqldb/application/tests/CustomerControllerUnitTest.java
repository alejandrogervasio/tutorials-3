package com.baeldung.hsqldb.application.tests;

import com.baeldung.hsqldb.application.entities.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerUnitTest {

    private static MediaType MEDIA_TYPE_JSON;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUpJsonMediaType() {
        MEDIA_TYPE_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    }

    @Test
    public void whenPostHttpRequesttoCustomer_thenJSONEntityRepresentation() throws Exception {
        Customer customer = new Customer("John", "john@domain.com");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(customer);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MEDIA_TYPE_JSON)
                        .content(requestJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenGetHttpRequesttogetCustomers_thenJSONContentType() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.content().contentType(MEDIA_TYPE_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
