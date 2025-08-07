package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.AppointmentLetterDataDs;
import com.example.clickhouse.dtos.reponses.DetailAppointmentLetter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface AdmissionMedicalRecordMapper {
    List<SoSucKhoeDienTuDataSt> findSoSucKhoeDienTuCustorm(String namQt, String soCccd);

    List<MedicineData> findMedicineCustorm(String namQt, String soCccd);

    List<DvKyThuatVatTu> findDvKyThuatVatTuCustorm(String namQt, String soCccd);

    List<DvCanLamSang> findDvCanLamSangCustorm(String namQt, String soCccd);

    List<TtHoSoBenhAn> findTtHoSoBenhAnCustorm(String namQt, String soCccd);

    String getNoiDen(String cskb);

    TinhCutru getTinhCutru(Long aLong);

    HuyenCuTru getHuyenCutru(Long aLong);

    XaCutru getXaCutru(Long aLong);

    String getNoiDi(String noiDi);


    List<SoKhamChuaBenhListData> findGetListSoSucKhoeDienTu(String namQt, String soCCCD, String maCskcb, String chanDoan,
                                                            int pageSize, int offset, String sortBy, String sortOrder);

    long countSoKhamChuaBenh(String namQt, String soCCCD, String maCskcb, String chanDoan);

    DetailSoSucKhoeDienTu findDetailSoSucKhoeDienTuCustorm(String namQt, String soCCCD, String maCskcb, String maLk);

    List<DetailMedicineData> getDetailMedicineData(String namQt, String soCCCD, String maCskcb, String maLk);

    List<DetailDvKyThuatVatTu> getDetailDvKyThuatVatTu(String namQt, String soCCCD, String maCskcb, String maLk);

    List<DetailDvCanLamSang> getDetailDvCanLamSang(String namQt, String soCCCD, String maCskcb, String maLk);

    List<DetailTtHoSoBenhAn> getDetailTtHoSoBenhAn(String namQt, String soCCCD, String maCskcb, String maLk);

    List<AppointmentLetterData> findAppointmentLetterCustorm(String namQt, String soCCCD);

    List<AppointmentLetterDataDs> findAppointmentLetterListCustorm(String namQt, String soCCCD, String maCskcb, String ngayHenTu,
                                                                   String ngayHenDen, String chanDoan, int pageSize, int offset,String sortBy, String sortOrder);

    long countAppointmentLetter(String namQt, String soCCCD, String maCskcb, String ngayHenTu, String ngayHenDen, String chanDoan);

}
