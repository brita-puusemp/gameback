package ee.avastaeesti.gameback.persistence.leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Integer> {
  @Query("select l from LeaderBoard l where l.game.id = :gameId")
  Optional<LeaderBoard> findUserScoreBy(Integer gameId);

  @Query("select l from LeaderBoard l where l.game.id = :gameId order by l.totalScore DESC LIMIT 1")
  Optional<LeaderBoard> findTopScoreBy(Integer gameId);


}