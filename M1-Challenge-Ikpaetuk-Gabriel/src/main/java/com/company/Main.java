package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"},
            new String[]{"2", "Daily Planet", "-7500", "01-10-2022"},
            new String[]{"1", "Wayne Enterprises", "18000", "12-22-2021"},
            new String[]{"3", "Ace Chemical", "-48000", "01-10-2022"},
            new String[]{"3", "Ace Chemical", "-95000", "12-15-2021"},
            new String[]{"1", "Wayne Enterprises", "175000", "01-01-2022"},
            new String[]{"1", "Wayne Enterprises", "-35000", "12-09-2021"},
            new String[]{"1", "Wayne Enterprises", "-64000", "01-17-2022"},
            new String[]{"3", "Ace Chemical", "70000", "12-29-2022"},
            new String[]{"2", "Daily Planet", "56000", "12-13-2022"},
            new String[]{"2", "Daily Planet", "-33000", "01-07-2022"},
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"},
            new String[]{"2", "Daily Planet", "33000", "01-17-2022"},
            new String[]{"3", "Ace Chemical", "140000", "01-25-2022"},
            new String[]{"2", "Daily Planet", "5000", "12-12-2022"},
            new String[]{"3", "Ace Chemical", "-82000", "01-03-2022"},
            new String[]{"1", "Wayne Enterprises", "10000", "12-01-2021"}
    );

    public static void main(String[] args) {
        //Update this

        // Uses map to keep track of accounts,
        // makes it easier to track accounts that have been created already
        Map<Integer, Customer> customersMap = new HashMap<>();
        customerData.forEach(data -> customersMap
                // Adds the customer to the Map if the key/customer does not exist
                .computeIfAbsent(Integer.valueOf(data[0]), key -> makeCustomer(Integer.parseInt(data[0]), data[1]))
                .addCharge(makeAccount(Integer.parseInt(data[2]), data[3]))
        );

        // Filters out the customer accounts with positive values
        List<Customer> positiveAccounts = getPositiveCustomerAccounts(customersMap.values());

        // Filters out the customer accounts with negative values
        List<Customer> negativeAccounts = getNegativeCustomerAccounts(customersMap.values());

        // Display the Positive customer accounts
        System.out.println("Positive accounts:");
        positiveAccounts.forEach(System.out::println);

        // Display the negative customer accounts
        System.out.println("\n");
        System.out.println("Negative accounts:");
        negativeAccounts.forEach(System.out::println);
    }

    // Utility method for creating a Customer
    private static Customer makeCustomer(int id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        return customer;
    }

    // Utility method for creating a AccountRecord
    private static AccountRecord makeAccount(int charge, String chargeDate) {
        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setCharge(charge);
        accountRecord.setChargeDate(chargeDate);
        return accountRecord;
    }

    // Utility method for getting the list of Customers with negative balance accounts
    private static List<Customer> getNegativeCustomerAccounts(Collection<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getBalance() < 0)
                .collect(Collectors.toList());
    }

    // Utility method for getting the list of Customers with positive balance accounts
    private static List<Customer> getPositiveCustomerAccounts(Collection<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getBalance() >= 0)
                .collect(Collectors.toList());
    }
}
