package com.example.REST.service.Models;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TransferLog {
    private String date;
    private String time;
    private String sourceCard;
    private String distinationCard;
    private Amount amount;
    private String commission;
    private String operationId;

    public TransferLog(String sourceCard, String distinationCard, Amount amount, String commission, String operationId) {
        var dateTime = ZonedDateTime.now(ZoneOffset.UTC);
        date = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.sourceCard = sourceCard;
        this.distinationCard = distinationCard;
        this.amount = amount;
        this.commission = commission;
        this.operationId = operationId;
    }

    public String getLogText() {
        String logEntry = String.format(
                "Дата: %s | Время: %s | Карта, с которой было списание : %s | Карта зачисления: %s " +
                        "| Сумма: %d %s | Комиссия: %.2f | Результат операции: %s",
                date,
                time,
                sourceCard,
                distinationCard,
                amount.getValue(),
                commission,
                operationId
        );

        return logEntry;

    }

}
