package ar.com.up.theater.manager.repository;

import ar.com.up.theater.manager.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    Optional<Show> findByNameIgnoreCase(String name);
}
