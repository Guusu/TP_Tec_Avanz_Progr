package ar.com.up.theater.manager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

import static ar.com.up.theater.manager.constant.ConstantValues.Hall.AMPHITHEATER;

@Entity
@DiscriminatorValue(AMPHITHEATER)
public class Amphitheater extends Hall {

    public Amphitheater() {
    }

    public Amphitheater(Integer hallId, String name, Integer capacity, String type) {
        super(hallId, name, capacity, type);
    }

    @Override
    public BigDecimal calculateTicketPrice(String ticketType, BigDecimal basePrice) {

        return basePrice;
    }
}