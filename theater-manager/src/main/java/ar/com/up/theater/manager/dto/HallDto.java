package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HallDto {

    private int id;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("capacidad")
    private int capacity;
    @JsonProperty("tipo")
    private String type;
    @JsonProperty("espectaculos")
    private List<ShowDto> shows = new ArrayList<>();

    public HallDto() {
    }

    public HallDto(int id, String name, int capacity, String type) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ShowDto> getShows() {
        return shows;
    }

    public void setShows(List<ShowDto> shows) {
        this.shows = shows;
    }
}
