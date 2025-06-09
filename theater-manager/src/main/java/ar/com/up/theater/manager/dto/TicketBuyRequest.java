package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketBuyRequest {
    @JsonProperty("tipo_entrada")
    private String type;
    @JsonProperty("espectaculo")
    private String show;
    @JsonProperty("cantidad")
    private Integer quantity;

    public TicketBuyRequest(String type, String show, Integer quantity) {
        this.type = type;
        this.show = show;
        this.quantity = quantity;
    }

    public TicketBuyRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
