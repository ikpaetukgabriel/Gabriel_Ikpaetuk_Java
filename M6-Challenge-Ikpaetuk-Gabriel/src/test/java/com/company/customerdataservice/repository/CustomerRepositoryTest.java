package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
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
        customer.setCountry("US");

        // Act...
        customerRepository.save(customer);
        Optional<Customer> actualCustomer = customerRepository.findById(customer.getId());

        // Assert...
        assertEquals(customer, actualCustomer.orElse(null));

    }

    @Test
    public void shouldUpdateCustomer() {
        // Arrange...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        customer.setCompany("ABC Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2(null);
        customer.setCity("Los Gatos");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("US");

        customerRepository.save(customer);

        customer.setFirstName("Johnathan");
        customer.setLastName("Doe");
        customer.setEmail("johnathandoe@example.com");
        customer.setCompany("XYZ Corp");
        customer.setPhone("321-565-1254");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 456");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customerRepository.save(customer);

        //Act...
        Optional<Customer> actualCustomer = customerRepository.findById(customer.getId());

        //Assert...
        assertEquals(customer, actualCustomer.orElse(null));

    }

    @Test
    public void shouldGetCustomerById() {

        // Arrange...
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("johndoe@example.com");
        customer1.setCompany("ABC Corp");
        customer1.setPhone("321-565-1254");
        customer1.setAddress1("123 Main St");
        customer1.setAddress2("");
        customer1.setCity("Los Angeles");
        customer1.setState("California");
        customer1.setPostalCode("12345");
        customer1.setCountry("US");

        customerRepository.save(customer1);


        Customer customer2 = new Customer();
        customer2.setFirstName("John");
        customer2.setLastName("Doe");
        customer2.setEmail("johndoe@example.com");
        customer2.setCompany("ABC Corp");
        customer2.setPhone("321-565-1254");
        customer2.setAddress1("123 Main St");
        customer2.setAddress2("");
        customer2.setCity("Los Angeles");
        customer2.setState("California");
        customer2.setPostalCode("12345");
        customer2.setCountry("US");

        customerRepository.save(customer2);

        //Act...
        Optional<Customer> actualCustomer = customerRepository.findById(customer1.getId());

        //Assert...
        assertEquals(customer1, actualCustomer.orElse(null));
    }


    @Test
    public void shouldGetCustomersByState() {
        //Arrange...
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("johndoe@example.com");
        customer1.setCompany("ABC Corp");
        customer1.setPhone("321-565-1254");
        customer1.setAddress1("123 Main St");
        customer1.setAddress2("Suite 456");
        customer1.setCity("Los Gatos");
        customer1.setState("California");
        customer1.setPostalCode("12345");
        customer1.setCountry("United States");

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("James");
        customer2.setLastName("Dan");
        customer2.setEmail("jamesDan@example.com");
        customer2.setCompany("XYZ Corp");
        customer2.setPhone("221-575-7888");
        customer2.setAddress1("136 Rodeo St");
        customer2.setAddress2("");
        customer2.setCity("Los Angeles");
        customer2.setState("California");
        customer2.setPostalCode("67378");
        customer2.setCountry("United States");

        customerRepository.save(customer2);


        Customer customer3 = new Customer();
        customer3.setFirstName("Adams");
        customer3.setLastName("Kenneth");
        customer3.setEmail("adamsK@testemail.com");
        customer3.setCompany("Netflix Inc.");
        customer3.setPhone("744-747-7900");
        customer3.setAddress1("345 Safe St");
        customer3.setAddress2("Suite 456");
        customer3.setCity("Salt Lake City");
        customer3.setState("Utah");
        customer3.setPostalCode("67537");
        customer3.setCountry("United States");

        customerRepository.save(customer3);

        //Act...
        List<Customer> customerList = customerRepository.findAllByState("California");

        //Assert...
        assertEquals(customerList.size(), 2);
    }


    @Test
    public void shouldDeleteCustomer() {
        //Arrange...
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
        customer.setCountry("US");

        customerRepository.save(customer);

        Optional<Customer> savedCustomer = customerRepository.findById(customer.getId());
        assertEquals(customer, savedCustomer.orElse(null));

        //Act...
        customerRepository.deleteById(customer.getId());

        savedCustomer = customerRepository.findById(customer.getId());

        //Assert...
        assertFalse(savedCustomer.isPresent());

    }

}