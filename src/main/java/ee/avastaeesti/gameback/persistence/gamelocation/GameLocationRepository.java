package ee.avastaeesti.gameback.persistence.gamelocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLocationRepository extends JpaRepository<GameLocation, Integer> {
}