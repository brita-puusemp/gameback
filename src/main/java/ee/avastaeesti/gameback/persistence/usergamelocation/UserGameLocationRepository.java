package ee.avastaeesti.gameback.persistence.usergamelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserGameLocationRepository extends JpaRepository<UserGameLocation, Integer> {


    @Query("select u from UserGameLocation u where u.userGame.id = :userGameId and u.state = :state")
    Optional<UserGameLocation> findNextLocationBy(Integer userGameId, String state);


}