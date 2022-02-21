package com.main.assignmentsix.models;

public class CustomerSpender {
    private int customerId;
    private double invoiceTotal;

    public CustomerSpender(int customerId, double invoiceTotal) {
        this.customerId = customerId;
        this.invoiceTotal = invoiceTotal;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }
}
