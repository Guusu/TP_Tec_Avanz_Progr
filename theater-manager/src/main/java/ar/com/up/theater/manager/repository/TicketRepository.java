package ar.com.up.theater.manager.repository;

import ar.com.up.theater.manager.model.Ticket;
import ar.com.up.theater.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUser(User user);

}
