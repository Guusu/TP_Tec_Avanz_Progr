package ar.com.up.theater.manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entradas")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private Integer ticketId;

    @Column(name = "tipo")
    private String type;

    @Column(nullable = false,name = "precio")
    private BigDecimal ticketPrice;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_espectaculo", nullable = false)
    private Show show;

    public Ticket() {
    }

    public Ticket(Integer ticketId, String type, BigDecimal ticketPrice, User user, Show show) {
        this.ticketId = ticketId;
        this.type = type;
        this.ticketPrice = ticketPrice;
        this.user = user;
        this.show = show;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
