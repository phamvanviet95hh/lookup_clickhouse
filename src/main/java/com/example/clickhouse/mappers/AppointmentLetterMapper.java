package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.reponses.DetailAppointmentLetter;
import com.example.clickhouse.dtos.reponses.TransferPaperData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppointmentLetterMapper {
    DetailAppointmentLetter findDetailAppointmentLetter(String soCccd, String maLk, String soGiayHen);

    List<TransferPaperData> findTransferPaperDatas(String namQt, String soCccd);
}
