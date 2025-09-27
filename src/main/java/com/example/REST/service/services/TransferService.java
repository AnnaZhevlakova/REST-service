package com.example.REST.service.services;

import com.example.REST.service.Data;
import com.example.REST.service.entities.TransverEntity;
import com.example.REST.service.enums.OperationState;
import com.example.REST.service.exceptions.UserException;
import com.example.REST.service.models.ConfirmRequest;
import com.example.REST.service.models.TransferRequest;
import com.example.REST.service.models.TransferResponse;
import org.springframework.stereotype.Service;


@Service
public class TransferService {
    public TransferResponse transfer(TransferRequest request) {
        var newTransfer = new TransverEntity();
        newTransfer.setId(Data.getTransferNextId());
        newTransfer.setAmount(request.getAmount());
        newTransfer.setState(OperationState.Waiting);
        newTransfer.setCardFromNumber(request.getCardFromNumber());
        newTransfer.setCardFromCVV(request.getCardFromCVV());
        newTransfer.setCardToNumber(request.getCardToNumber());
        newTransfer.setCardFromValidTill(request.getCardFromValidTill());
        newTransfer.setConfirmCode("0000");

        Data.getTransfers().add(newTransfer);
        return new TransferResponse(String.valueOf(newTransfer.getId()));
    }

    public TransferResponse confirmOperation(ConfirmRequest request) {
        var operation = Data.getTransfers().stream()
                .filter(x -> request.getOperationId().equals(String.valueOf(x.getId())))
                .findFirst();

        if (operation.isEmpty()) {
            throw new UserException("Операция не найдена.");
        }

        var op = operation.get();
        if (!op.getConfirmCode().equals(request.getCode())) {
            throw new UserException("Введен не верный код подтверждения.");
        }

        op.setState(OperationState.Success);
        return new TransferResponse(String.valueOf(operation.get().getId()));
    }
}
