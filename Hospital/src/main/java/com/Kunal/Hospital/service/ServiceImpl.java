package com.Kunal.Hospital.service;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;
import com.Kunal.Hospital.repository.PatientDataSource;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ServiceImpl implements PatientService{

    private final PatientDataSource dataSource;

    public ServiceImpl(PatientDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Patient admitPatient(Patient patient) {
        return dataSource.addPatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() throws IOException {
        return dataSource.retrieveAllPatients();
    }

    @Override
    public Patient retrievePatient(PatientSearchRequest patientSearchRequest) {
        return dataSource.retrievePatient(patientSearchRequest);
    }

    @Override
    public List<Patient> fetchAllAdmittedPatients() {
        return dataSource.fetchAllAdmittedPatients();
    }

    @Override
    public Patient dischargePatient(Patient patient) {
        return dataSource.updatePatientDetails(patient);
    }

}
