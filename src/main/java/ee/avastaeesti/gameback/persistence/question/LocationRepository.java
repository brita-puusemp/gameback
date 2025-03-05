package ee.avastaeesti.gameback.persistence.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Question, Integer> {
    @Query("select (count(q) > 0) from Question q where q.locationName = :locationName")
    boolean locationExistsBy(String locationName);

}