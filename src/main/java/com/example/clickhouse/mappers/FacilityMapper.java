package com.example.clickhouse.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface FacilityMapper {
    List<String> findAllCreatedBy(@Param("username") String username);
}
