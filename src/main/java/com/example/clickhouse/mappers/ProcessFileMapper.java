package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.datas.ProcessedFiles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ProcessFileMapper {
    ProcessedFiles findByUnitNameAndMaLk(@Param("unitName") String unitName, @Param("maLk") String maLk);

    Optional<ProcessedFiles> findByUnitNameAndFileName(String maCSKCB, String maTraCuu);
}
