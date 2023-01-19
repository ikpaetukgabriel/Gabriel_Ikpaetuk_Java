package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        //update this
        return this.charges
                .stream()
                .mapToInt(AccountRecord::getCharge)
                .reduce(0, Integer::sum);
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    public void addCharge(AccountRecord accountRecord) {
        this.charges.add(accountRecord);
    }

    @Override
    public String toString() {
        //update this
        return String.format(
                "Customer ID: %s \n" +
                "Customer name: %s \n" +
                "Customer balance: %d",
                this.id, this.name, this.getBalance());
    }
}