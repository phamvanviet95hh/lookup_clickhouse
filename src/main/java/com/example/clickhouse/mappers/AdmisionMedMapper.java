package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.datas.ClinicalResultsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmisionMedMapper {
    List<ClinicalResultsDto> findDataClinicalResultsCustorm(String maCskcb, Long idCheckIn);
}
