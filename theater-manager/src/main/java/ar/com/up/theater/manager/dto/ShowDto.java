package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class ShowDto {
    private int id;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("fecha")
    private String date;
    @JsonProperty("duracion")
    private Integer duration;
    @JsonProperty("sala")
    private HallSummaryDto hall;
    @JsonProperty("precio_base")
    private BigDecimal price;
    @JsonProperty("artistas")
    private List<ArtistDto> artists;

    public ShowDto(int id, String name, String date, Integer duration, HallSummaryDto hall, BigDecimal price, List<ArtistDto> artists) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.hall = hall;
        this.price = price;
        this.artists = artists;
    }

    public ShowDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public HallSummaryDto getHall() {
        return hall;
    }

    public void setHall(HallSummaryDto hall) {
        this.hall = hall;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ArtistDto> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDto> artists) {
        this.artists = artists;
    }
}
