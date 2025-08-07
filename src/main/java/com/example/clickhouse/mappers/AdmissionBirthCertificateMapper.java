package com.example.clickhouse.mappers;

import com.example.clickhouse.models.DataNhi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmissionBirthCertificateMapper {

    /**
     * Finds a list of DataNhi records based on the provided medical facility code and citizen ID.
     *
     * @param maCskcb The medical facility code.
     * @param soCccd  The citizen ID.
     * @return A list of DataNhi records matching the criteria.
     */
    List<DataNhi> findBySoSucKhoeSoSinh(String maCskcb, String soCccd);
}
