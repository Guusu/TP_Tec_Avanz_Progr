package ar.com.up.theater.manager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

import static ar.com.up.theater.manager.constant.ConstantValues.Hall.CLOSED_HALL;
import static ar.com.up.theater.manager.constant.ConstantValues.Ticket.TYPE_A;

@Entity
@DiscriminatorValue(CLOSED_HALL)
public class ClosedHall extends Hall {

    public ClosedHall() {
    }

    public ClosedHall(Integer hallId, String name, Integer capacity, String type) {
        super(hallId, name, capacity, type);
    }

    @Override
    public BigDecimal calculateTicketPrice(String ticketType, BigDecimal basePrice) {
        if (TYPE_A.equalsIgnoreCase(ticketType)) {
            return basePrice.multiply(BigDecimal.valueOf(2));
        }else{
            return basePrice;
        }
    }
}