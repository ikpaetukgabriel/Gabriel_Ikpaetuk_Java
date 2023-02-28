package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        Customer customerExpected = buildCustomer();
        String customerAsString = mapper.writeValueAsString(customerExpected);
        Customer customerActual = buildCustomer();

        // Mock
        doReturn(customerActual).when(customerRepository).save(customerExpected);

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
        Customer customerExpected = buildCustomer();
        customerExpected.setFirstName("Johnathan");
        customerExpected.setEmail("Jonathan.Doe@testmail.com");
        Customer customerActual = buildCustomer();
        customerActual.setFirstName("Johnathan");
        customerActual.setEmail("Jonathan.Doe@testmail.com");

        // Mock
        String customerAsString = mapper.writeValueAsString(customerExpected);
        doReturn(customerActual).when(customerRepository).save(customerExpected);

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
        Customer customerExpected = buildCustomer();
        int expectedCustomerId = customerExpected.getId();
        Customer customerActual = buildCustomer();

        // Mock
        doReturn(Optional.of(customerActual)).when(customerRepository).findById(expectedCustomerId);

        // Act ...
        mockMvc.perform(get("/customers/".concat(String.valueOf(expectedCustomerId))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCustomersByState() throws Exception {
        // Arrange ...
        Customer customerExpected = buildCustomer();
        String customerState = customerExpected.getState();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerExpected);

        // Mock
        doReturn(customerList).when(customerRepository).findAllByState(customerState);

        // Act ...
        mockMvc.perform(get("/customers/state/".concat(customerState)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    private static Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
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
        return customer;
    }
}