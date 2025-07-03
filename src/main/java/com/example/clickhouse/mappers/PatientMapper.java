package com.example.clickhouse.mappers;

import com.example.clickhouse.models.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientMapper {

    List<Patient> getAllPatients();

    Patient getPatientById(String uuid);
}
