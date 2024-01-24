package fireTMS.app.service;

import fireTMS.app.exception.InvalidDateException;
import fireTMS.app.exception.OverlappingAssignmentException;
import fireTMS.app.exception.SemitrailerNotFoundException;
import fireTMS.app.exception.VehicleNotFoundException;
import fireTMS.app.model.Assignment;
import fireTMS.app.model.Semitrailer;
import fireTMS.app.model.Vehicle;
import fireTMS.app.repository.AssignmentRepository;
import fireTMS.app.repository.SemitrailerRepository;
import fireTMS.app.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    SemitrailerRepository semitrailerRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    public Assignment assign(String vehicleId, String semitrailerId, Instant startDate, Instant endDate, boolean adminOperation) {
        if (startDate == null)
            startDate = Instant.now();

        if (endDate == null)
            endDate = Instant.MAX;

        if (startDate.isAfter(endDate))
            throw new InvalidDateException();

        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
        Semitrailer semitrailer = semitrailerRepository.findByRegistrationNumber(semitrailerId)
                .orElseThrow(() -> new SemitrailerNotFoundException(semitrailerId));

        if (adminOperation) {
            adjustAssignments(semitrailerId, startDate, endDate, vehicle, semitrailer);
        } else {
            if (checkIfAssignmentsOverlap(semitrailerId, startDate, endDate))
                throw new OverlappingAssignmentException();
        }


        return assignmentRepository.save(new Assignment(vehicle, semitrailer, startDate, endDate));
    }

    private void adjustAssignments(String semitrailerId, Instant startDate, Instant endDate, Vehicle vehicle, Semitrailer semitrailer) {
        List<Assignment> assignments = assignmentRepository.findAllBySemitrailerRegistrationNumber(semitrailerId);

        for (Assignment assignment : assignments) {

            Instant assignmentStartDate = assignment.getStartDate();
            Instant assignmentEndDate = assignment.getEndDate();

            if (!startDate.isAfter(assignmentEndDate) && !endDate.isBefore(assignmentStartDate)) {

                if (startDate.isBefore(assignmentStartDate) && endDate.isAfter(assignmentEndDate)) {
                    assignmentRepository.delete(assignment);

                } else if (startDate.isAfter(assignmentStartDate) && endDate.isBefore(assignmentEndDate)) {
                    assignmentRepository.delete(assignment);
                    assignmentRepository.save(new Assignment(vehicle, semitrailer, assignmentStartDate, startDate));
                    assignmentRepository.save(new Assignment(vehicle, semitrailer, endDate, assignmentEndDate));

                } else if (startDate.isBefore(assignmentStartDate) && endDate.isAfter(assignmentStartDate)) {
                    assignment.setStartDate(endDate);

                } else if (startDate.isBefore(assignmentEndDate) && endDate.isAfter(assignmentEndDate)) {
                    assignment.setEndDate(startDate);
                }
            }

        }
    }


    private boolean checkIfAssignmentsOverlap(String semitrailerId, Instant startDate, Instant endDate) {
        List<Assignment> assignments = assignmentRepository.findAllBySemitrailerRegistrationNumber(semitrailerId);

        for (Assignment assignment : assignments) {
            Instant assignmentStartDate = assignment.getStartDate();
            Instant assignmentEndDate = assignment.getEndDate();

            if (!startDate.isAfter(assignmentEndDate) && !endDate.isBefore(assignmentStartDate)) {
                return true;
            }
        }
        return false;
    }

    public List<Assignment> getAssignments() {
        return assignmentRepository
                .findAllByStartDateBetween(Instant.now().minus(Period.ofDays(30)), Instant.now());
    }


}