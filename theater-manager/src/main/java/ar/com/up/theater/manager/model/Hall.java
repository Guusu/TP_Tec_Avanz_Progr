package ar.com.up.theater.manager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_sala",discriminatorType = DiscriminatorType.STRING)
public abstract class Hall {
    @Id
    @Column(name = "id_sala")
    private Integer hallId;

    @Column(name = "nombre")
    private String name;

    @Column(name = "capacidad")
    private Integer capacity;
    @Column(name = "tipo_sala", insertable = false, updatable = false)
    private String type;
    @JsonManagedReference
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Show> shows = new HashSet<>();

    public Hall() {
    }

    public Hall(Integer hallId, String name, Integer capacity, String type) {
        this.hallId = hallId;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public abstract BigDecimal calculateTicketPrice(String ticketType, BigDecimal basePrice);

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }
}
