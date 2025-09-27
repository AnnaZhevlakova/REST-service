package com.example.REST.service;

import com.example.REST.service.entities.CardEntity;
import com.example.REST.service.entities.OperationCodeEntity;
import com.example.REST.service.entities.TransverEntity;

import java.util.concurrent.CopyOnWriteArrayList;

public class Data {
    private static CopyOnWriteArrayList<OperationCodeEntity> _operations = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<TransverEntity> _transfers = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<CardEntity> _cards = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<OperationCodeEntity> getOperations() {
        return _operations;
    }

    public static CopyOnWriteArrayList<TransverEntity> getTransfers() {
        return _transfers;
    }

    public static CopyOnWriteArrayList<CardEntity> getCards() {
        return _cards;
    }

    public static long getOperationCodeNextId() {
        return _operations.stream().count() + 1;
    }

    public static long getTransferNextId() {
        return _transfers.stream().count() + 1;
    }

    public static long getCardsNextId() {
        return _cards.stream().count() + 1;
    }
}
