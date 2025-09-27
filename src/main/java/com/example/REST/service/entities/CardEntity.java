package com.example.REST.service.entities;


import java.time.LocalDateTime;

public class CardEntity {
    private long id;
    private String cardName;
    private String cvv;
    private long amount;
    private long currencyId;
    private LocalDateTime cardValidTill;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public LocalDateTime getCardValidTill() {
        return cardValidTill;
    }

    public void setCardValidTill(LocalDateTime cardValidTill) {
        this.cardValidTill = cardValidTill;
    }
}
