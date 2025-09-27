package com.example.REST.service.controllers;

import com.example.REST.service.models.ConfirmRequest;
import com.example.REST.service.models.TransferLog;
import com.example.REST.service.models.TransferRequest;
import com.example.REST.service.models.TransferResponse;
import com.example.REST.service.services.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    private TransferService service;
    private static final Logger logger = LogManager.getLogger(TransferController.class);

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferRequest request) throws Exception {
        var result = service.transfer(request);
        var lodTransfer = new TransferLog(
                request.getCardFromNumber(),
                request.getCardToNumber(),
                request.getAmount(),
                "",
                result.getOperationId()
        );
        logger.info(lodTransfer.getLogText());
        return result;
    }

    @PostMapping("/confirmOperation")
    public TransferResponse confirmOperation(@RequestBody ConfirmRequest request) {
        var result = service.confirmOperation(request);
        logger.info(String.format("Операци прошла успешно. %s", result.getOperationId()));
        return result;
    }
}
