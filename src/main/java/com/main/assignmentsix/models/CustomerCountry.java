package com.main.assignmentsix.models;

public class CustomerCountry {
    private int amountOfCustomers;
    private String country;

    public CustomerCountry(int amountOfCustomers, String country) {
        this.amountOfCustomers = amountOfCustomers;
        this.country = country;
    }

    public int getAmountOfCustomers() {
        return amountOfCustomers;
    }

    public String getCountry() {
        return country;
    }
}
