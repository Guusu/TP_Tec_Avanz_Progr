package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HallSummaryDto {
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("capacidad")
    private int capacity;
    @JsonProperty("tipo")
    private String type;

    public HallSummaryDto(String name, int capacity, String type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
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
}
