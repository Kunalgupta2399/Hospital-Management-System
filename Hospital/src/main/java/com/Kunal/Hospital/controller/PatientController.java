package com.Kunal.Hospital.controller;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;
import com.Kunal.Hospital.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping ("api")
@AllArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService service;

    @GetMapping("patients")
    ResponseEntity<List<Patient>> getAllPatients() throws IOException {
        log.info("Fetching all patients details.");
        return ResponseEntity.ok().body(service.getAllPatients());
    }

    @PostMapping("admit")
    ResponseEntity<Patient> admitPatient(@RequestBody Patient patient) {
        log.info("Admitting patient : {}", patient);
        return ResponseEntity.created(URI.create("admitted-successfully")).body(service.admitPatient(patient));
    }

    @GetMapping("admitted-patients")
    ResponseEntity<List<Patient>> fetchAllAdmittedPatient() {
        log.info("Fetching all admitted patients.");
        return ResponseEntity.ok().body(service.fetchAllAdmittedPatients());
    }

    @PatchMapping("discharge")
    ResponseEntity<?> dischargePatient(@RequestBody PatientSearchRequest patientSearchRequest) {
        Patient patient = service.retrievePatient(patientSearchRequest);
        log.info("discharging patient {}", patientSearchRequest);
        if(patient == null) {
            log.error("Patient does not exist in the database.");
            return ResponseEntity.notFound().build();
        }
        log.info("Changing status of patient {} to discharge", patient);
        return ResponseEntity.ok().body(service.dischargePatient(patient));
    }
}
