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

    public static Customer makeCustomer(int id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        return customer;
    }

    public static void main(String[] args) {
        //Update this
        Map<Integer, Customer> customersMap = new HashMap<>();
        int id;
        String name;
        int charge;
        String chargeDate;

        for (String[] customerData : customerData) {
            id = Integer.parseInt(customerData[0]);
            name = customerData[1];
            charge = Integer.parseInt(customerData[2]);
            chargeDate = customerData[3];

            customersMap.putIfAbsent(id, makeCustomer(id, name));

            AccountRecord accountRecord = new AccountRecord();
            accountRecord.setCharge(charge);
            accountRecord.setChargeDate(chargeDate);
            customersMap.get(id).addCharge(accountRecord);
        }

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

    private static List<Customer> getNegativeCustomerAccounts(Collection<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getBalance() < 0)
                .collect(Collectors.toList());
    }

    private static List<Customer> getPositiveCustomerAccounts(Collection<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getBalance() >= 0)
                .collect(Collectors.toList());
    }
}
