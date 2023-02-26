package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        // Arrange ...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("ABC Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 456");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        String customerAsString = mapper.writeValueAsString(customer);

        // Act ...
        mockMvc.perform(post("/customers")
                        .content(customerAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        // Arrange ...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("ABC Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 456");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        String customerAsString = mapper.writeValueAsString(customer);

        // Act ...
        mockMvc.perform(put("/customers")
                        .content(customerAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetCustomerById() throws Exception {
        // Arrange ...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("ABC Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 456");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        customerRepository.save(customer);

        // Act ...
        mockMvc.perform(get("/customers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCustomersByState() throws Exception {
        List<Customer> customers = new ArrayList<>();

        // Arrange ...
        Customer customer = new Customer();
        customer.setFirstName("Moroni");
        customer.setLastName("Sam");
        customer.setEmail("moroni.sam@antinephilehi.com");
        customer.setCompany("Zion Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("765 Galaxy St");
        customer.setAddress2(null);
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customers.add(customer);

        given(customerRepository.findAllByState("California")).willReturn(customers);

        // Act ...
        mockMvc.perform(get("/customers/state/California"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }
}