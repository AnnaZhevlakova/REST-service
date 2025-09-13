package com.example.REST.service.Models;

public class Amount {
    private long value;
    private String currency;

    public Amount() {

    }

    public Amount(long value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
