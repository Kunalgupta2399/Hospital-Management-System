package com.Kunal.Hospital.service;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    Patient admitPatient(Patient patient);
    List<Patient> getAllPatients() throws IOException;
    Patient retrievePatient(PatientSearchRequest patientSearchRequest);
    List<Patient> fetchAllAdmittedPatients();
    Patient dischargePatient(Patient patient);
}
