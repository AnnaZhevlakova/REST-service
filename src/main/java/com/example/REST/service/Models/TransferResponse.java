package com.example.REST.service.Models;

public class TransferResponse {
    private String operationId;

    public  TransferResponse(){

    }

    public TransferResponse(String operationId){
        this.operationId = operationId;
    }

    public String getOperationId(){
        return operationId;
    }

    public void setOperationId(String operationId){
        this.operationId = operationId;
    }
}
