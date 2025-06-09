package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.TicketBuyRequest;
import ar.com.up.theater.manager.dto.TicketDto;

import java.util.List;

public interface TicketService {

    ApiResponse<List<TicketDto>> getTicketsByUser(String username);

    ApiResponse<List<TicketDto>> buyTicket(TicketBuyRequest request, String username);
}
