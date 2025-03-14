package ee.avastaeesti.gameback.persistence.gamelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameLocationRepository extends JpaRepository<GameLocation, Integer> {
    @Query("select g from GameLocation g where g.game.id = :gameId order by g.id")
    List<GameLocation> findGameLocationsBy(Integer gameId);

}