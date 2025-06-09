package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.*;
import ar.com.up.theater.manager.model.User;
import ar.com.up.theater.manager.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;
    Jwt jwt;
    @BeforeEach
    void setUp() {
        jwt = Jwt.withTokenValue("dummy-token")
                .header("alg", "none")
                .subject("usuario")
                .build();
    }

    @Test
    @DisplayName("getAllTicketFromUSer() -> Devuelve Entradas del Usuario")
    void GET_TicketFromUser_shouldReturn200(){
        TicketDto ticketDto = getTicketDto();
        ApiResponse<List<TicketDto>> apiResponse = ApiResponse.success(List.of(ticketDto));
        when(ticketService.getTicketsByUser(anyString())).thenReturn(apiResponse);

        ResponseEntity<ApiResponse<List<TicketDto>>> response = ticketController.getAll(jwt);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));

        verify(ticketService).getTicketsByUser(anyString());
    }

    private TicketDto getTicketDto() {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setType("A");
        ticketDto.setPrice(BigDecimal.valueOf(100L));
        ticketDto.setShow(new ShowDto());
        ticketDto.setUser("usuario");
        return ticketDto;
    }

    @Test
    @DisplayName("buyTicket() -> devuelve 200 con los datos de la entrada")
    void POST_buyTicket_success() throws Exception {
        TicketBuyRequest request = new TicketBuyRequest();

        TicketDto ticketDto = getTicketDto();

        ApiResponse<List<TicketDto>> ok = ApiResponse.success(Collections.singletonList(ticketDto));
        when(ticketService.buyTicket(any(),anyString())).thenReturn(ok);

        ResponseEntity<ApiResponse<List<TicketDto>>> response = ticketController.buyTicket(request, jwt);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));
        verify(ticketService).buyTicket(any(),anyString());
    }


}