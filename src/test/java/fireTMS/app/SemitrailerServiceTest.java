package fireTMS.app;


import fireTMS.app.model.Semitrailer;
import fireTMS.app.repository.SemitrailerRepository;
import fireTMS.app.service.SemitrailerService;
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
public class SemitrailerServiceTest {

    @Mock
    SemitrailerRepository semitrailerRepository;

    @InjectMocks
    SemitrailerService semitrailerService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnSemitrailer() {
        Semitrailer semitrailer = new Semitrailer("PO41389", 1200d, 1500d);
        Mockito.when(semitrailerRepository.findByRegistrationNumber("PO41389")).thenReturn(Optional.of(semitrailer));

        Semitrailer result = semitrailerService.getSemitrailer("PO41389");
        Assertions.assertEquals("PO41389", result.getRegistrationNumber());
    }

    @Test
    public void shouldCreateSemitrailer() {
        Semitrailer semitrailer = new Semitrailer("PO41389", 1200d, 1500d);
        Mockito.when(semitrailerRepository.save(semitrailer)).thenReturn(semitrailer);

        Semitrailer result = semitrailerService.createSemitrailer(semitrailer);
        Assertions.assertEquals("PO41389", result.getRegistrationNumber());
    }

}
