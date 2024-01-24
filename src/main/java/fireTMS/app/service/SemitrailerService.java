package fireTMS.app.service;


import fireTMS.app.exception.SemitrailerAlreadyExistsException;
import fireTMS.app.exception.SemitrailerNotFoundException;
import fireTMS.app.model.Semitrailer;
import fireTMS.app.repository.SemitrailerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SemitrailerService {

    @Autowired
    SemitrailerRepository semitrailerRepository;

    public Semitrailer getSemitrailer(String registrationNumber) {
        return semitrailerRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new SemitrailerNotFoundException(registrationNumber));
    }

    public Semitrailer createSemitrailer(Semitrailer semitrailer) {
        boolean exists = semitrailerRepository.existsByRegistrationNumber(semitrailer.getRegistrationNumber());
        if (exists)
            throw new SemitrailerAlreadyExistsException(semitrailer.getRegistrationNumber());
        return semitrailerRepository.save(semitrailer);
    }

    public Semitrailer updateSemitrailer(String registrationNumber, Semitrailer semitrailer) {
        Semitrailer updatedSemitrailer = getSemitrailer(registrationNumber);
        updatedSemitrailer.setHeight(semitrailer.getHeight());
        updatedSemitrailer.setWeight(semitrailer.getWeight());
        semitrailerRepository.save(updatedSemitrailer);

        return updatedSemitrailer;
    }

    public int deleteSemitrailer(String registrationNumber) {
        boolean exists = semitrailerRepository.existsByRegistrationNumber(registrationNumber);
        if (!exists)
            throw new SemitrailerNotFoundException(registrationNumber);
        return semitrailerRepository.deleteByRegistrationNumber(registrationNumber);
    }
}
