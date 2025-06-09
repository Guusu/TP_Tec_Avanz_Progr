package ar.com.up.theater.manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "espectaculos")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espectaculo")
    private Integer showId;

    @Column(name = "nombre")
    private String name;

    @Column(nullable = false,name ="fecha")
    private LocalDateTime date;

    @Column(nullable = false, name = "duracion_minutos")
    private Integer minutes;

    @Column(nullable = false,name = "precio_base_entrada")
    private BigDecimal ticketDefaultPrice;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sala", nullable = false)
    private Hall hall;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_show", nullable = false)
    private ShowType showType;

    @ManyToMany
    @JoinTable(
            name = "espectaculo_artista",
            joinColumns = @JoinColumn(name = "id_espectaculo"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    private Set<Artist> artistsSet = new HashSet<>();

    public Show() {
    }

    public Show(Integer showId, String name, LocalDateTime date, Integer minutes, BigDecimal ticketDefaultPrice, Hall hall, ShowType showType, Set<Artist> artistsSet) {
        this.showId = showId;
        this.name = name;
        this.date = date;
        this.minutes = minutes;
        this.ticketDefaultPrice = ticketDefaultPrice;
        this.hall = hall;
        this.showType = showType;
        this.artistsSet = artistsSet;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public BigDecimal getTicketDefaultPrice() {
        return ticketDefaultPrice;
    }

    public void setTicketDefaultPrice(BigDecimal ticketDefaultPrice) {
        this.ticketDefaultPrice = ticketDefaultPrice;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public Set<Artist> getArtistsSet() {
        return artistsSet;
    }

    public void setArtistsSet(Set<Artist> artistsSet) {
        this.artistsSet = artistsSet;
    }
}
