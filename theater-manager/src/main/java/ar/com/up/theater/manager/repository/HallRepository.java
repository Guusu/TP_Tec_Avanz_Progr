package ar.com.up.theater.manager.repository;

import ar.com.up.theater.manager.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {

    Optional<Hall> findByTypeIgnoreCase(String name);
}
