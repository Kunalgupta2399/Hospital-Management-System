package com.Kunal.Hospital.model;

import lombok.Data;

@Data
public class PatientSearchRequest {

    private String name;
    private int age;
    private String doctorName;
}
