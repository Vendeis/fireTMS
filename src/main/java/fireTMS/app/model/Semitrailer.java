package fireTMS.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Semitrailer {
    @Id
    @NotBlank
    private String registrationNumber;
    private Double height;
    private Double weight;

    @OneToMany(mappedBy = "semitrailer")
    List<Assignment> assignments;

    public Semitrailer(String registrationNumber, Double height, Double weight) {
        this.registrationNumber = registrationNumber;
        this.height = height;
        this.weight = weight;
    }

    public Semitrailer() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
