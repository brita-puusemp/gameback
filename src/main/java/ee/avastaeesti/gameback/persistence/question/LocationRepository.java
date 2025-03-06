package ee.avastaeesti.gameback.persistence.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Question, Integer> {
    @Query("select (count(q) > 0) from Question q where q.locationName = :locationName")
    boolean locationExistsBy(String locationName);

    @Query("select q from Question q where q.id = :id")
    Optional<Question> findLocationByQuestionId(Integer id);

}