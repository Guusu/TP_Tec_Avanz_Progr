package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ShowDto;
import ar.com.up.theater.manager.dto.TicketBuyRequest;
import ar.com.up.theater.manager.dto.TicketDto;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.*;
import ar.com.up.theater.manager.repository.TicketRepository;
import ar.com.up.theater.manager.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private UserService userService;
    @Mock
    private ShowService showService;
    @Spy
    private TheaterMapper theaterMapper;
    @InjectMocks
    private TicketServiceImpl ticketService;


    private String username = "guusu";
    private User user = new User();
    @BeforeEach
    void setUp() {

        user.setUsername(username);
        user.setName("Gustavo");
        when(userService.getUserByUsername(username)).thenReturn(user);

    }

    @Test
    @DisplayName("getTicketFromUSer -> Devuelve Entradas del usuario")
    void GET_TicketByUser_success() {

        Ticket ticket1 = new Ticket();
        ticket1.setUser(user);
        ticket1.setType("A");
        ticket1.setShow(new Show());
        ticket1.getShow().setName("show1");

        Ticket ticket2 = new Ticket();
        ticket2.setUser(user);
        ticket2.setType("B");
        ticket2.setShow(new Show());
        ticket2.getShow().setName("show2");

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);

        ShowDto showDto = new ShowDto();
        showDto.setId(1);
        showDto.setName("Gustavo");
        ShowDto showDto2 = new ShowDto();
        showDto2.setId(2);
        showDto2.setName("Gustavo");
        when(theaterMapper.showToDto(any())).thenReturn(showDto);
        when(theaterMapper.showToDto(ticket2.getShow())).thenReturn(showDto2);
        when(ticketRepository.findAllByUser(any())).thenReturn(tickets);

        ApiResponse<List<TicketDto>> ticketsByUser = ticketService.getTicketsByUser(username);

        assertNotNull(ticketsByUser);
        assertFalse(CollectionUtils.isEmpty(ticketsByUser.getData()));
        assertEquals(tickets.size(), ticketsByUser.getData().size());
        assertEquals("Gustavo",ticketsByUser.getData().getFirst().getUser());

        verify(ticketRepository).findAllByUser(any());
        verify(userService).getUserByUsername(username);
        verify(theaterMapper,times(2)).showToDto(any());

    }

    @Test
    @DisplayName("BuyTicketAmphitheater () -> 201 Ticket Calculado y mostrado")
    void buyTicketAmphitheater() {
        TicketBuyRequest buyTicketRequest = getBuyTicketRequest();
        Show show = new Show();
        show.setName("bossiShow");
        Amphitheater hall = new Amphitheater();
        show.setHall(hall);
        show.setTicketDefaultPrice(BigDecimal.valueOf(100L));
        when(showService.getShowByName(anyString())).thenReturn(show);


        ApiResponse<List<TicketDto>> ticketBought = ticketService.buyTicket(buyTicketRequest,username);

        ArgumentCaptor<Ticket> cap = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository, times(2)).save(cap.capture());
        List<Ticket> savedTickets = cap.getAllValues();
        assertEquals(2, savedTickets.size());
        Ticket first  = savedTickets.get(0);
        Ticket second = savedTickets.get(1);
        assertEquals(buyTicketRequest.getShow(), first.getShow().getName());
        assertEquals(buyTicketRequest.getShow(), second.getShow().getName());
        assertEquals(show.getTicketDefaultPrice(),first.getTicketPrice());

        assertNotNull(ticketBought);
        assertFalse(CollectionUtils.isEmpty(ticketBought.getData()));
        assertEquals(ticketBought.getData().size(), 2);
        assertEquals("Gustavo",ticketBought.getData().getFirst().getUser());


        verify(userService).getUserByUsername(username);
    }
    @Test
    @DisplayName("BuyTicketClosedHall () -> 201 Ticket Calculado y mostrado")
    void buyTicketClosedHallTypeA() {
        TicketBuyRequest buyTicketRequest = getBuyTicketRequest();
        buyTicketRequest.setQuantity(1);
        Show show = new Show();
        show.setName("bossiShow");
        ClosedHall hall = new ClosedHall();
        show.setHall(hall);
        show.setTicketDefaultPrice(BigDecimal.valueOf(100L));
        when(showService.getShowByName(anyString())).thenReturn(show);


        ApiResponse<List<TicketDto>> ticketBought = ticketService.buyTicket(buyTicketRequest,username);

        ArgumentCaptor<Ticket> cap = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository, times(1)).save(cap.capture());
        List<Ticket> savedTickets = cap.getAllValues();
        assertEquals(1, savedTickets.size());
        Ticket first  = savedTickets.getFirst();
        assertEquals(buyTicketRequest.getShow(), first.getShow().getName());
        assertEquals(show.getTicketDefaultPrice().multiply(BigDecimal.valueOf(2)),first.getTicketPrice());

        assertNotNull(ticketBought);
        assertFalse(CollectionUtils.isEmpty(ticketBought.getData()));
        assertEquals(ticketBought.getData().size(), 1);
        assertEquals("Gustavo",ticketBought.getData().getFirst().getUser());


        verify(userService).getUserByUsername(username);
    }
    private TicketBuyRequest getBuyTicketRequest() {
        TicketBuyRequest ticketBuyRequest = new TicketBuyRequest();
        ticketBuyRequest.setType("A");
        ticketBuyRequest.setShow("bossiShow");
        ticketBuyRequest.setQuantity(2);

        return ticketBuyRequest;
    }
}