package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    /**
//     * Retrieves all customers from the database.
//     * This method is only intended for testing purposes and is not intended for external use.
//     *
//     * @return a list of all customers in the database.
//     */
//    @GetMapping("/customers")
//    public List<Customer> getAllCustomersForTesting() {
//        return customerRepository.findAll();
//    }


    /**
     * Adds a new customer to the database.
     *
     * @param customer The customer object to be added.
     * @return The customer object that was added.
     */
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


    /**
     * Updates a customer in the database.
     *
     * @param customer The customer to update.
     * @return The updated customer.
     */
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


    /**
     * Retrieves a customer from the database by ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID, or null if not found.
     */
    @GetMapping("/customers/{id}")
    public Customer getAlbumById(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    /**
     * Retrieves all customers in a specified state.
     *
     * @param state The state to search for.
     * @return A list of all customers in the specified state.
     */
    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return customerRepository.findAllByState(state);
    }


    /**
     * Deletes a customer in the database by ID.
     *
     * @param id the ID of the customer to be deleted
     */
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", ex);
        }
    }
}
