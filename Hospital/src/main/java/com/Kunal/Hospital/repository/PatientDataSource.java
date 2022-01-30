package com.Kunal.Hospital.repository;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PatientDataSource {
    List<Patient> retrieveAllPatients() throws IOException;
    Patient retrievePatient(PatientSearchRequest patientSearchRequest);
    List<Patient> fetchAllAdmittedPatients();
    Patient updatePatientDetails(Patient patient);

    Patient addPatient(Patient patient);
}
