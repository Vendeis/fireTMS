package fireTMS.app.controller;

import fireTMS.app.model.Vehicle;
import fireTMS.app.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/{registrationNumber}")
    public Vehicle getVehicle(@PathVariable String registrationNumber) {
        return vehicleService.getVehicle(registrationNumber);
    }

    @PostMapping("/")
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{registrationNumber}")
    public Vehicle updateVehicle(@PathVariable String registrationNumber, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(registrationNumber, vehicle);
    }

    @DeleteMapping("{registrationNumber}")
    public int deleteVehicle(@PathVariable String registrationNumber) {
        return vehicleService.deleteVehicle(registrationNumber);
    }
}
