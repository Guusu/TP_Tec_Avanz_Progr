package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtistDto {
    private int id;
    @JsonProperty("nombre")
    private String name;

    public ArtistDto() {
    }

    public ArtistDto(int id, String name) {
        this.id = id;
        this.name = name;
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
}
