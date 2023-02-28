package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldAddCustomer() {
        // Arrange...
        Customer expectedCustomer = buildCustomer();

        // Act...
        customerRepository.save(expectedCustomer);
        Optional<Customer> actualCustomer = customerRepository.findById(expectedCustomer.getId());

        // Assert...
        assertEquals(expectedCustomer, actualCustomer.orElse(null));

    }


    @Test
    public void shouldUpdateCustomer() throws JsonProcessingException {
        // Arrange...
        Customer customerInitial = buildCustomer();
        customerRepository.save(customerInitial);

        // Clone customer, not to point to the same object
        ObjectMapper mapper = new ObjectMapper();
        String jsonCustomer = mapper.writeValueAsString(customerInitial);

        // Act...
        Customer customerClone = mapper.readValue(jsonCustomer, Customer.class);
        customerClone.setFirstName("Johnathan");
        customerClone.setEmail("Jonathan.Doe@testmail.com");
        customerRepository.save(customerClone);

        // Assert...
        assertNotEquals(customerInitial, customerRepository
                .findById(customerInitial.getId())
                .orElse(null));

        assertEquals(customerClone, customerRepository
                .findById(customerInitial.getId())
                .orElse(null));
    }

    @Test
    public void shouldGetCustomerById() {
        // Arrange...
        Customer customerExpected = buildCustomer();
        customerRepository.save(customerExpected);

        // Act...
        Customer customerActual = customerRepository
                .findById(customerExpected.getId())
                .orElse(null);

        // Assert...
        assertEquals(customerExpected, customerActual);
    }


    @Test
    public void shouldGetCustomersByState() {
        //Arrange...
        Customer customer1 = buildCustomer();
        customer1.setState("California");
        customerRepository.save(customer1);

        Customer customer2 = buildCustomer();
        customer2.setState("California");
        customerRepository.save(customer2);

        Customer customer3 = buildCustomer();
        customer3.setState("Utah");
        customerRepository.save(customer3);

        //Act...
        List<Customer> customerList = customerRepository.findAllByState("California");

        //Assert...
        assertEquals(customerList.size(), 2);
    }


    @Test
    public void shouldDeleteCustomer() {
        //Arrange...
        Customer customer = buildCustomer();
        customerRepository.save(customer);

        //Act...
        customerRepository.deleteById(customer.getId());
        Optional<Customer> savedCustomer = customerRepository.findById(customer.getId());

        //Assert...
        assertFalse(savedCustomer.isPresent());

    }


    private static Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("ABC Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 456");
        customer.setCity("Los Gatos");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");
        return customer;
    }

}