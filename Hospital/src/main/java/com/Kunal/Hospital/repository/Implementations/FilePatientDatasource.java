package com.Kunal.Hospital.repository.Implementations;

import com.Kunal.Hospital.model.Patient;
import com.Kunal.Hospital.model.PatientSearchRequest;
import com.Kunal.Hospital.repository.PatientDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class FilePatientDatasource implements PatientDataSource {

    private final File patientData = new File("PatientData.txt");

    @Override
    public List<Patient> retrieveAllPatients() throws IOException {
        List<Patient> patientList = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(patientData);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        objectInputStream.reset();
        try {
            while (true) {
                Patient patient = (Patient) objectInputStream.readObject();
                patientList.add(patient);
            }
        } catch (EOFException e) {
            log.info("File data ended.");
        } catch (ClassNotFoundException e) {
            log.error("Class not found in patient data. Check PatientData.txt");
            e.printStackTrace();
        }
        return patientList;
    }

    @Override
    public Patient retrievePatient(PatientSearchRequest patientRequest) {
        try {
            FileInputStream fileInputStream = new FileInputStream(patientData);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream.reset();
            while (true) {
                Patient patient = (Patient) objectInputStream.readObject();
                if (patient.getName().equalsIgnoreCase(patientRequest.getName()) &&
                        patient.getAge() == patientRequest.getAge() &&
                        patient.getDoctorName().equalsIgnoreCase(patientRequest.getDoctorName())) {
                    return patient;
                }
            }
        } catch (EOFException e) {
            log.info("File data ended.");
        } catch (ClassNotFoundException e) {
            log.error("Class not found in patient data. Check PatientData.txt");
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> fetchAllAdmittedPatients() {
        List<Patient> admittedPatients = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(patientData);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream.reset();
            while (true) {
                Patient patient = (Patient) objectInputStream.readObject();
                if (patient.getStatus().equals("ADMITTED")) {
                    admittedPatients.add(patient);
                }
            }
        } catch (EOFException e) {
            log.info("File data ended.");
        } catch (ClassNotFoundException e) {
            log.error("Class not found in patient data. Check PatientData.txt");
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage());
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
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(patientData, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(patient);
            objectOutputStream.close();
            fileOutputStream.close();
            log.info("File updated with new patient {}", patient);
        } catch (EOFException e) {
            log.info("File data ended.");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return patient;
    }
}
