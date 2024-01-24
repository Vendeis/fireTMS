package fireTMS.app.repository;

import fireTMS.app.model.Semitrailer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemitrailerRepository extends JpaRepository<Semitrailer, Long> {
    Optional<Semitrailer> findByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumber(String registrationNumber);

    int deleteByRegistrationNumber(String registrationNumber);
}
