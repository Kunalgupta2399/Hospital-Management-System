package com.Kunal.Hospital.repository.Implementations;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;
import com.Kunal.Hospital.repository.PatientDataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class MockPatientDatasource implements PatientDataSource {

    List<Patient> patientList = new ArrayList<>();

    public MockPatientDatasource() {
        this.patientList.add(new Patient("kunal", 22, 245, "Deepak Aggarwal", "22-12-2021", 7000.00, "DISCHARGED"));
        this.patientList.add(new Patient("Sahil", 24, 246, "Ashok Aggarwal", "31-01-2021", 100000.00, "ADMITTED"));
        this.patientList.add(new Patient("Kanishk", 25, 247, "Hola Aggarwal", "04-09-2021", 50000.00, "ADMITTED"));
        this.patientList.add(new Patient("Prateek", 19, 248, "Sameer Aggarwal", "10-10-2021", 23493.00, "DISCHARGED"));
    }

    @Override
    public List<Patient> retrieveAllPatients() {
        return this.patientList;
    }

    @Override
    public Patient retrievePatient(PatientSearchRequest patientRequest) {
        for (Patient patient : patientList) {
            if (patient.getName().equalsIgnoreCase(patientRequest.getName()) &&
                    patient.getAge() == patientRequest.getAge() &&
                    patient.getDoctorName().equalsIgnoreCase(patientRequest.getDoctorName())) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public List<Patient> fetchAllAdmittedPatients() {
        List<Patient> admittedPatients = new ArrayList<>();

        for (Patient patient : patientList) {
            if(patient.getStatus().equals("ADMITTED")) {
                admittedPatients.add(patient);
            }
        }

        return admittedPatients;
    }

    @Override
    public Patient updatePatientDetails(Patient patient) {
        if(patient.getStatus().equals("ADMITTED")) {
            patient.setStatus("DISCHARGED");
            return patient;
        }

        return null;
    }

    @Override
    public Patient addPatient(Patient patient) {
        this.patientList.add(patient);

        return patient;
    }
}
