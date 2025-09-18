package com.example.REST.service.entities;

public class TransverEntity {
    private long id;
    private String operationId;
    private long sourceCardId;
    private long destinationCardId;
    private long amount;
    private long currencyId;
    private long codeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public long getSourceCardId() {
        return sourceCardId;
    }

    public void setSourceCardId(long sourceCardId) {
        this.sourceCardId = sourceCardId;
    }

    public long getDestinationCardId() {
        return destinationCardId;
    }

    public void setDestinationCardId(long destinationCardId) {
        this.destinationCardId = destinationCardId;
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

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }
}
