package ar.com.up.theater.manager.repository;

import ar.com.up.theater.manager.model.ShowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowTypeRepository extends JpaRepository<ShowType, Integer> {

    Optional<ShowType> findByNameIgnoreCase(String name);
}
