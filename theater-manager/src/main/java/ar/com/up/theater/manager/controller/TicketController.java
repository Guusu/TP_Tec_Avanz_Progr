package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.*;
import ar.com.up.theater.manager.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("theater/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TicketDto>>> getAll(@AuthenticationPrincipal Jwt jwt) {

        String username = jwt.getSubject();
        ApiResponse<List<TicketDto>> apiResponse = ticketService.getTicketsByUser(username);

        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse,BAD_REQUEST);

        if (CollectionUtils.isEmpty(apiResponse.getData()))
            return new ResponseEntity<>(apiResponse,NOT_FOUND);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/buy")
    public ResponseEntity<ApiResponse<List<TicketDto>>> buyTicket(@RequestBody TicketBuyRequest ticketBuyRequest,
                                                                 @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        ApiResponse<List<TicketDto>> apiResponse = ticketService.buyTicket(ticketBuyRequest,username);
        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse,BAD_REQUEST);

        return new ResponseEntity<>(apiResponse,CREATED);

    }

}
