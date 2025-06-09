package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShowRegistrationRequest {
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("fecha")
    private String date;
    @JsonProperty("horario")
    private String hour;
    @JsonProperty("tipo_show")
    private String type;
    @JsonProperty("duracion")
    private Integer duration;
    @JsonProperty("artistas")
    private List<String> artists = new ArrayList<>();
    @JsonProperty("sala")
    private String hall;
    @JsonProperty("precio")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
