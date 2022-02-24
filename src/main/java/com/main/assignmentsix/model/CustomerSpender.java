package com.main.assignmentsix.model;


public class CustomerSpender {
    private int customerId;
    private double invoiceTotal;

    public CustomerSpender(int customerId, double invoiceTotal) {
        this.customerId = customerId;
        invoiceTotal = Math.round(invoiceTotal*100) ;
        this.invoiceTotal = invoiceTotal/100;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }
}
