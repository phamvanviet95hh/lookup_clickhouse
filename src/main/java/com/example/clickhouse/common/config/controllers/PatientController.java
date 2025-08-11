package com.example.clickhouse.common.config.controllers;

import com.example.clickhouse.models.Patient;
import com.example.clickhouse.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientServices patientServices;


    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllPatients() {
        // This method would typically call a service to fetch patient data

        List<Patient> patients = patientServices.getAllPatients();

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/one-record/{uuid}")
    public ResponseEntity<?> getPatients(@PathVariable String uuid) {
        // This method would typically call a service to fetch patient data

        Patient patient = patientServices.getPatient(uuid);

        return ResponseEntity.ok(patient);
    }

}
