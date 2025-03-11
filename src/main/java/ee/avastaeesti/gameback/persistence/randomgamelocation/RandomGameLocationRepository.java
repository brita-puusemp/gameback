package ee.avastaeesti.gameback.persistence.randomgamelocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RandomGameLocationRepository extends JpaRepository<RandomGameLocation, Integer> {

    @Query("select r from RandomGameLocation r where r.randomGame.id = :randomGameId and r.state = :state ORDER BY r.id ASC")
    Optional<RandomGameLocation> findRandomGameLocationBy(Integer randomGameId, String state);


    // todo find first line (enotity object) by Integer randomGameId, String state, wrap in optional

    Optional<RandomGameLocation> findFirstByRandomGameIdAndStateOrderByIdAsc(Integer randomGameId, String state);

    @Query("select (count(r) > 0) from RandomGameLocation r where r.randomGame.id = :randomGameId and r.state = :state")

    boolean randomGameLocationExistsBy( Integer randomGameId, String state);


}