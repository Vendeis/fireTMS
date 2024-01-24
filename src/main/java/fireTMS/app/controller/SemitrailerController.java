package fireTMS.app.controller;


import fireTMS.app.model.Semitrailer;
import fireTMS.app.service.SemitrailerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semitrailer")
public class SemitrailerController {

    @Autowired
    SemitrailerService semitrailerService;

    @GetMapping("/{registrationNumber}")
    public Semitrailer getSemitrailer(@PathVariable String registrationNumber) {
        return semitrailerService.getSemitrailer(registrationNumber);
    }

    @PostMapping("/")
    public Semitrailer createSemitrailer(@Valid @RequestBody Semitrailer semitrailer) {
        return semitrailerService.createSemitrailer(semitrailer);
    }

    @PutMapping("/{registrationNumber}")
    public Semitrailer updateSemitrailer(@PathVariable String registrationNumber, @RequestBody Semitrailer semitrailer) {
        return semitrailerService.updateSemitrailer(registrationNumber, semitrailer);
    }

    @DeleteMapping("{registrationNumber}")
    public int deleteSemitrailer(@PathVariable String registrationNumber) {
        return semitrailerService.deleteSemitrailer(registrationNumber);
    }
}
