package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.reponses.LookupHistoryResponseDetail;
import com.example.clickhouse.dtos.reponses.LookupHistoryResponseTH;
import com.example.clickhouse.entitys.platform.MedicalXml;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MedicalXmlMapper {

    Optional<MedicalXml> findByMaCSKCBAndMaLK(@Param("maCSKCB") String maCSKCB, @Param("maLK") String maLK);

    List<LookupHistoryResponseTH> findByCreatedAt(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("loaiHoSo") String loaiHoSo,
            RowBounds rowBounds
    );

    Long findByUpdatedAt(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("loaiHoSo") String loaiHoSo
    );

    List<LookupHistoryResponseDetail> findByMaLk(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("loaiHoSo") String loaiHoSo,
            RowBounds rowBounds
    );

    Boolean existsByMaCSKCBAndMaLK(@Param("maCSKCB") String maCSKCB, @Param("maLK") String maLK);
}
