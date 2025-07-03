package com.example.clickhouse.services;


import com.example.clickhouse.mappers.PatientMapper;
import com.example.clickhouse.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientServices {

    @Autowired
    private PatientMapper patientMapper;


    public List<Patient> getAllPatients() {

        // This method would typically call a mapper to fetch patient data
        List<Patient> patients = patientMapper.getAllPatients();

        // Here you can add any additional processing if needed
        return patients;
    }

    public Patient getPatient(String uuid) {
        // This method would typically call a mapper to fetch patient data
        Patient patient = patientMapper.getPatientById(uuid);

        // Here you can add any additional processing if needed
        return patient;
    }
}
