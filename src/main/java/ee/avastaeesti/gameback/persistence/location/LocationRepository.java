package ee.avastaeesti.gameback.persistence.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("select (count(l) > 0) from Location l where l.name = :locationName")
    boolean locationExistsBy(String locationName);

    @Query("SELECT q.clue FROM Location q WHERE q.id = :questionId")
    String findClueByQuestionId(int questionId);

}