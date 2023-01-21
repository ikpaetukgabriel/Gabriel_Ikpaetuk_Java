package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    //    MethodName_StateUnderTest_ExpectedBehavior
    @Test
    public void test_getBalance_populatedCharges_expectSummedCharges() {
        // Create customer
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Janeth Doe");

        // Populate customer charges
        AccountRecord first_accountRecord = new AccountRecord();
        first_accountRecord.setCharge(2000);
        first_accountRecord.setChargeDate("01-03-2022");
        customer.addCharge(first_accountRecord);

        AccountRecord second_accountRecord = new AccountRecord();
        second_accountRecord.setCharge(-1200);
        second_accountRecord.setChargeDate("11-05-2022");
        customer.addCharge(second_accountRecord);

        AccountRecord third_accountRecord = new AccountRecord();
        third_accountRecord.setCharge(50);
        third_accountRecord.setChargeDate("12-06-2022");
        customer.addCharge(third_accountRecord);

        int actualBalance = customer.getBalance();
        int expectedBalance = 850;

        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void test_getBalance_emptyCustomerCharges_expect0() {
        // Create customer
        Customer customer = new Customer();
        customer.setId(2);
        customer.setName("John Doe");

        int actualBalance = customer.getBalance();
        int expectedBalance = 0;

        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void test_toString_populatedCharges_string() {
        // Create customer
        Customer customer = new Customer();
        customer.setId(3);
        customer.setName("Johnson Doe");

        // Populate customer charges
        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setCharge(800);
        accountRecord.setChargeDate("01-08-2022");
        customer.addCharge(accountRecord);

        String actualValue = customer.toString();
        String expectedValue = "Customer ID: 3 \n" +
                "Customer name: Johnson Doe \n" +
                "Customer balance: 800";

        Assert.assertEquals(expectedValue, actualValue);
    }


    @Test
    public void test_toString_emptyCustomerCharges_stringWithZeroBalance() {
        // Create customer
        Customer customer = new Customer();
        customer.setId(4);
        customer.setName("Jonathan Doe");

        String actualValue = customer.toString();
        String expectedValue = "Customer ID: 4 \n" +
                "Customer name: Jonathan Doe \n" +
                "Customer balance: 0";

        Assert.assertEquals(expectedValue, actualValue);
    }
}