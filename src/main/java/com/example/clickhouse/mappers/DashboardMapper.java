package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.DischargeStatusRp;
import com.example.clickhouse.dtos.reponses.SummaryQualityRp;
import com.example.clickhouse.dtos.reponses.TreatmentRp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DashboardMapper {
    List<SummarySickRp> summary(@Param("from") LocalDateTime startTime,
                                @Param("to") LocalDateTime endTime,
                                @Param("isByt")int isByt,
                                @Param("cskbs") List<String> cskbs,
                                @Param("level") String level);

    List<TopRp> top(@Param("from") LocalDateTime startTime,
                    @Param("to") LocalDateTime endTime,
                    @Param("isByt")int isByt,
                    @Param("cskbs") List<String> cskbs,
                    @Param("level") String level);

    List<TopRp> percent(@Param("from") LocalDateTime startTime,
                        @Param("to") LocalDateTime endTime,
                        @Param("fromlast") LocalDateTime startTimeLast,
                        @Param("tolast") LocalDateTime endTimeLast,
                        @Param("isByt")int isByt,
                        @Param("cskbs") List<String> cskbs,
                        @Param("level") String level);

    List<SummaryQualityRp> summaryForQuality(@Param("from") LocalDateTime startTime,
                                             @Param("to") LocalDateTime endTime,
                                             @Param("isByt")int isByt,
                                             @Param("cskbs") List<String> cskbs,
                                             @Param("level") String level);
    List<DischargeStatusRp> dischargeStatusForQuality(@Param("from") LocalDateTime startTime,
                                                      @Param("to") LocalDateTime endTime,
                                                      @Param("isByt")int isByt,
                                                      @Param("cskbs") List<String> cskbs,
                                                      @Param("level") String level);
    List<TreatmentRp> treatmentForQuality(@Param("from") LocalDateTime startTime,
                                          @Param("to") LocalDateTime endTime,
                                          @Param("isByt")int isByt,
                                          @Param("cskbs") List<String> cskbs,
                                          @Param("level") String level);
    List<TimeDTriRp> timeDtriForQuality(@Param("from") LocalDateTime startTime,
                                        @Param("to") LocalDateTime endTime,
                                        @Param("isByt")int isByt,
                                        @Param("cskbs") List<String> cskbs,
                                        @Param("level") String level);
    List<MedRp> med(@Param("from") LocalDateTime startTime,
                    @Param("to") LocalDateTime endTime,
                    @Param("isByt")int isByt,
                    @Param("cskbs") List<String> cskbs,
                    @Param("level") String level);
    List<MedRp> equipment(@Param("from") LocalDateTime startTime,
                          @Param("to") LocalDateTime endTime,
                          @Param("isByt")int isByt,
                          @Param("cskbs") List<String> cskbs,
                          @Param("level") String level);
    List<TopEquipmentRp> topEquipment(@Param("from") LocalDateTime startTime,
                                      @Param("to") LocalDateTime endTime,
                                      @Param("isByt")int isByt,
                                      @Param("cskbs") List<String> cskbs,
                                      @Param("level") String level);
    List<TopEquipmentRp> qualitySevice(@Param("from") LocalDateTime startTime,
                                       @Param("to") LocalDateTime endTime,
                                       @Param("isByt")int isByt,
                                       @Param("cskbs") List<String> cskbs,
                                       @Param("level") String level);
}
