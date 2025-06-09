package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.dto.*;
import ar.com.up.theater.manager.exception.TheaterException;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Show;
import ar.com.up.theater.manager.model.Ticket;
import ar.com.up.theater.manager.model.User;
import ar.com.up.theater.manager.repository.TicketRepository;
import ar.com.up.theater.manager.service.ShowService;
import ar.com.up.theater.manager.service.TicketService;
import ar.com.up.theater.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static ar.com.up.theater.manager.constant.errorCodes.*;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final ShowService showService;
    private final TheaterMapper theaterMapper;
    Logger  logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService, ShowService showService, TheaterMapper theaterMapper) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.showService = showService;
        this.theaterMapper = theaterMapper;
    }

    @Override
    public ApiResponse<List<TicketDto>> getTicketsByUser(String username) {
        User currentUser = userService.getUserByUsername(username);

        List<TicketDto> tickets = ticketRepository.findAllByUser(currentUser).stream()
                .map(this::toDto)
                .toList();

        return ApiResponse.success(tickets);
    }

    private TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setType(ticket.getType());
        ticketDto.setPrice(ticket.getTicketPrice());
        ticketDto.setUser(ticket.getUser().getName());
        ticketDto.setShow(theaterMapper.showToDto(ticket.getShow()));
        return ticketDto;
    }

    @Override
    public ApiResponse<List<TicketDto>> buyTicket(TicketBuyRequest request, String username) {

        List<TicketDto> tickets = new ArrayList<>();
        try {
            User currentUser = userService.getUserByUsername(username);

            Show showSelected = showService.getShowByName(request.getShow());
            if (showSelected == null)
                throw new TheaterException(SHOW_NOT_EXISTS.getCode(), SHOW_NOT_EXISTS.getMessage());

            for (int x = 0; x < request.getQuantity(); x++) {
                Ticket newTicket = new Ticket();
                newTicket.setShow(showSelected);
                newTicket.setUser(currentUser);
                newTicket.setType(request.getType());
                newTicket.setTicketPrice(showSelected.getHall().calculateTicketPrice(request.getType(), showSelected.getTicketDefaultPrice()));
                ticketRepository.save(newTicket);
                tickets.add(toDto(newTicket));
            }
        }catch (TheaterException e){
        logger.error(e.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(e.getCode());
        errorDto.setMessage(e.getMessage());
        return ApiResponse.failure(List.of(errorDto));
    }catch (Exception e){
        logger.error(e.getMessage());
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(TICKET_BUY_ERROR.getCode());
        errorDto.setMessage(e.getMessage());
        return ApiResponse.failure(List.of(errorDto));

    }

        return ApiResponse.success(tickets);
    }
}
