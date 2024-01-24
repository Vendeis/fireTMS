package fireTMS.app.service;

import fireTMS.app.exception.VehicleAlreadyExistsException;
import fireTMS.app.exception.VehicleNotFoundException;
import fireTMS.app.model.Vehicle;
import fireTMS.app.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle getVehicle(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new VehicleNotFoundException(registrationNumber));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        boolean exists = vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber());
        if (exists)
            throw new VehicleAlreadyExistsException(vehicle.getRegistrationNumber());
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(String registrationNumber, Vehicle vehicle) {
        Vehicle updatedVehicle = getVehicle(registrationNumber);
        updatedVehicle.setColor(vehicle.getColor());
        updatedVehicle.setWeight(vehicle.getWeight());
        vehicleRepository.save(updatedVehicle);

        return updatedVehicle;
    }

    public int deleteVehicle(String registrationNumber) {
        boolean exists = vehicleRepository.existsByRegistrationNumber(registrationNumber);
        if (!exists)
            throw new VehicleNotFoundException(registrationNumber);
        return vehicleRepository.deleteByRegistrationNumber(registrationNumber);
    }
}
