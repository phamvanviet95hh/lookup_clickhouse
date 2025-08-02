package com.example.clickhouse.mappers;

import com.example.clickhouse.dtos.datas.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminssionCheckinMapper {
    String findMaTinhByMalk(@Param("maLk") String maLk);

    String findMaCskcbByMaLk(@Param("maLk") String maLk);

    String findMaTinhByCccd(@Param("soCccd") String soCccd);

    String findMaCskcbByCccd(@Param("soCccd") String soCccd);

    List<TienSu> findByTienSu(String soCccdNnd);

    List<DotKham> findByDotKham(String soCccdNnd);

    List<KqCanLamSang> findByKqCanLamSang(String soCccdNnd);

    List<DonthuocDake> findByDonThuocDaKe(String soCccdNnd);

    List<PhauthuatThuthuat> findByPhauThuatThuThuat(String soCccdNnd);

    List<QuatrinhDieutri> findByQuaTrinhDieuTri(String soCccdNnd);

    List<DataHistoryDto> findByCreatedAt(String maCSKCB, String soCCCD);
}
