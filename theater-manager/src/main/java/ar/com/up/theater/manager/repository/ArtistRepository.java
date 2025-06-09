package ar.com.up.theater.manager.repository;

import ar.com.up.theater.manager.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Optional<Artist> findByNameIgnoreCase(String name);
}
