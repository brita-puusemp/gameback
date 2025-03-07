package ee.avastaeesti.gameback.persistence.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Question, Integer> {
    @Query("select (count(q) > 0) from Question q where q.locationName = :locationName")
    boolean locationExistsBy(String locationName);

//    todo / pean ju otsima 1 Id jargi, mitte list jne. kas ei tohi muuta nimetust findById?
    @Query("select q from Question q where q.id = :id")
    Question findLocationByQuestionId(Integer id);


}