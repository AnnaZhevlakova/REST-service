package com.example.REST.service.Controllers;

import com.example.REST.service.Models.ConfirmRequest;
import com.example.REST.service.Models.TransferRequest;
import com.example.REST.service.Models.TransferResponse;
import com.example.REST.service.Services.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private TransferService service;

    public TransferController(TransferService service){
        this.service = service;

    }
    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferRequest request) {
       return service.transfer(request);
    }

    @PostMapping("/confirmOperation")
    public TransferResponse confirmOperation(@RequestBody ConfirmRequest request) {
        return  service.confirmOperation(request);
    }
}
