package fireTMS.app;


import fireTMS.app.model.Vehicle;
import fireTMS.app.repository.VehicleRepository;
import fireTMS.app.service.VehicleService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleService vehicleService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnVehicle() {
        Vehicle vehicle = new Vehicle("PO41389", "yellow", 1500d);
        Mockito.when(vehicleRepository.findByRegistrationNumber("PO41389")).thenReturn(Optional.of(vehicle));

        Vehicle result = vehicleService.getVehicle("PO41389");
        Assertions.assertEquals("PO41389", result.getRegistrationNumber());
    }

    @Test
    public void shouldCreateVehicle() {
        Vehicle vehicle = new Vehicle("PO41389", "yellow", 1500d);
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle result = vehicleService.createVehicle(vehicle);
        Assertions.assertEquals("PO41389", result.getRegistrationNumber());
    }

}
