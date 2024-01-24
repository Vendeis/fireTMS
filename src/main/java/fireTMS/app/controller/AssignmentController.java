package fireTMS.app.controller;

import fireTMS.app.model.Assignment;
import fireTMS.app.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @GetMapping("/{vehicleId}/{semitrailerId}/assign")
    public Assignment assign(@PathVariable String vehicleId, @PathVariable String semitrailerId,
                             @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
                             @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate) {

        return assignmentService.assign(vehicleId, semitrailerId, startDate, endDate, false);

    }

    @GetMapping("/{vehicleId}/{semitrailerId}/assign-admin")
    public Assignment assignAdmin(@PathVariable String vehicleId, @PathVariable String semitrailerId,
                                  @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startDate,
                                  @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endDate) {

        return assignmentService.assign(vehicleId, semitrailerId, startDate, endDate, true);
    }

    @GetMapping("/list")
    public List<Assignment> getAssignments(){
        return assignmentService.getAssignments();
    }

}
