package ee.avastaeesti.gameback.persistence.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("select g from Game g where g.user.id = :userId and g.status = :status order by g.name")
    List<Game> findGamesBy(Integer userId, String status);
}