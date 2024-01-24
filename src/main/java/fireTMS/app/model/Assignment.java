package fireTMS.app.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "semitrailer_id")
    Semitrailer semitrailer;

    Instant startDate;
    Instant endDate;

    public Assignment(Vehicle vehicle, Semitrailer semitrailer, Instant startDate, Instant endDate) {
        this.vehicle = vehicle;
        this.semitrailer = semitrailer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Assignment() {
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Semitrailer getSemitrailer() {
        return semitrailer;
    }

    public void setSemitrailer(Semitrailer semitrailer) {
        this.semitrailer = semitrailer;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
}
