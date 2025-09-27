package com.example.REST.service;

import com.example.REST.service.controllers.TransferController;
import com.example.REST.service.models.Amount;
import com.example.REST.service.models.ConfirmRequest;
import com.example.REST.service.models.TransferRequest;
import com.example.REST.service.models.TransferResponse;
import com.example.REST.service.services.TransferService;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @Mock
    private Logger logger;

    @InjectMocks
    private TransferController transferController;

    private MockMvc mockMvc;
    private TransferRequest transferRequest;
    private ConfirmRequest confirmRequest;
    private TransferResponse transferResponse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transferController).build();

        transferRequest = new TransferRequest();
        transferRequest.setCardFromNumber("1234567812345678");
        transferRequest.setCardToNumber("8765432187654321");
        transferRequest.setAmount(new Amount(100, "RUR"));

        confirmRequest = new ConfirmRequest();
        confirmRequest.setOperationId("test-operation-id");
        confirmRequest.setCode("1234");

        transferResponse = new TransferResponse();
        transferResponse.setOperationId("test-operation-id");

        lenient().doNothing().when(logger).info(anyString());
    }

    @Test
    void testMockInjection() {
        assertNotNull(transferController);
        assertNotNull(transferService);
        assertTrue(Mockito.mockingDetails(transferService).isMock(), "TransferService should be a mock");
    }

    @Test
    void transfer_ShouldThrowException_WhenServiceFails() {

        when(transferService.transfer(transferRequest)).thenThrow(new RuntimeException("Transfer failed"));

        Exception exception = assertThrows(Exception.class, () -> {
            transferController.transfer(transferRequest);
        });
        assertEquals("Transfer failed", exception.getMessage());
        verify(transferService, times(1)).transfer(any(TransferRequest.class));
    }

    @Test
    void confirmOperation_ShouldReturnResponse_WhenServiceReturnsSuccess() {

        when(transferService.confirmOperation(confirmRequest)).thenReturn(transferResponse);


        TransferResponse result = transferController.confirmOperation(confirmRequest);

        assertNotNull(result);
        assertEquals("test-operation-id", result.getOperationId());
        verify(transferService, times(1)).confirmOperation(confirmRequest);
    }

    @Test
    void confirmOperation_ShouldThrowException_WhenServiceFails() {

        when(transferService.confirmOperation(any(ConfirmRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid operation ID"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transferController.confirmOperation(confirmRequest);
        });
        assertEquals("Invalid operation ID", exception.getMessage());
        verify(transferService, times(1)).confirmOperation(any(ConfirmRequest.class));
    }

    @Test
    void confirmOperation_Success_WithMockMvc() throws Exception {

        when(transferService.confirmOperation(any(ConfirmRequest.class))).thenReturn(transferResponse);


        mockMvc.perform(post("/transfer/confirmOperation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operationId\":\"test-operation-id\",\"code\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationId").value("test-operation-id"));

        verify(transferService, times(1)).confirmOperation(any(ConfirmRequest.class));
    }
}