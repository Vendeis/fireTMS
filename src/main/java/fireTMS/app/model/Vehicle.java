package fireTMS.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @NotBlank
    private String registrationNumber;
    private String color;
    private Double weight;

    @OneToMany(mappedBy = "vehicle")
    List<Assignment> assignments;

    public Vehicle(String registrationNumber, String color, Double weight) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.weight = weight;
    }

    public Vehicle() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
