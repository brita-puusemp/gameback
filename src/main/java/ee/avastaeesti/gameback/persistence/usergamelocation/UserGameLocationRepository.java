package ee.avastaeesti.gameback.persistence.usergamelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserGameLocationRepository extends JpaRepository<UserGameLocation, Integer> {
    @Query("select u from UserGameLocation u where u.userGame.game.id = :gameId and u.state = :state ORDER BY u.id ASC")
    Optional<UserGameLocation> findNextLocationBy(Integer gameId, String state);


}