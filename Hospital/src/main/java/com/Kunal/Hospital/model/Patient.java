package com.Kunal.Hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Patient implements Serializable {
    private String name;
    private int age;
    private int room;
    private String doctorName;
    private String admitDate;
    private double expense;
    private String status;
}
