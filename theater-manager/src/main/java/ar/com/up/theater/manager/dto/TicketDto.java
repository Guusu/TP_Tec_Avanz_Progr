package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TicketDto {
    @JsonProperty("tipo")
    String type;
    @JsonProperty("precio")
    BigDecimal price;
    @JsonProperty("espectaculo")
    ShowDto show;
    @JsonProperty("usuario")
    String user;

    public TicketDto() {

    }

    public TicketDto(String type, BigDecimal price, ShowDto show, String user) {
        this.type = type;
        this.price = price;
        this.show = show;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShowDto getShow() {
        return show;
    }

    public void setShow(ShowDto show) {
        this.show = show;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
