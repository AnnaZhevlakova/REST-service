package com.example.REST.service.Controllers;

import com.example.REST.service.Models.ConfirmRequest;
import com.example.REST.service.Models.TransferLog;
import com.example.REST.service.Models.TransferRequest;
import com.example.REST.service.Models.TransferResponse;
import com.example.REST.service.Services.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
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
        return result;
    }
}
