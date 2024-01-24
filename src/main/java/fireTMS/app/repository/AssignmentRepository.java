package fireTMS.app.repository;

import fireTMS.app.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findAllBySemitrailerRegistrationNumber(String semitrailerId);

    List<Assignment> findAllByStartDateBetween(Instant minus, Instant now);
}
