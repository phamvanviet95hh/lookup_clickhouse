package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.reponses.DetailAppointmentLetter;
import com.example.clickhouse.dtos.reponses.TransferPaperData;
import com.example.clickhouse.dtos.reponses.TransferPaperDetailData;
import com.example.clickhouse.dtos.reponses.TransferPaperListData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppointmentLetterMapper {
    DetailAppointmentLetter findDetailAppointmentLetter(String soCccd, String maLk, String soGiayHen);

    List<TransferPaperData> findTransferPaperDatas(String namQt, String soCccd);

    List<TransferPaperListData> findTransferPaperListDatas(String namQt, String soCccd, String cskcbFilter, String noiDiFilter, String noiDenFilter, String ngayVaoTu, String ngayVaoDen, int pageSize, int offset);

    long countTransferPaperListData(String namQt, String soCccd, String cskcbFilter, String noiDiFilter, String noiDenFilter, String ngayVaoTu, String ngayVaoDen);

    TransferPaperDetailData findTransferPaperDetailData(String soHoSo, String soCccd, String maLk, String soGiayChuyenTuyen);
}
