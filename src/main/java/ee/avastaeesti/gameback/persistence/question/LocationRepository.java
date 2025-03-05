package ee.avastaeesti.gameback.persistence.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Question, Integer> {
    @Query("select q from Question q where q.locationName = :locationName")
    Optional<Question> findLocationBy(String locationName);

}