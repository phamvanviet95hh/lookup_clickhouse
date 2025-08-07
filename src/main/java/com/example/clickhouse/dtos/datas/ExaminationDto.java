package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExaminationDto {

    private String idCheckIn;
    private String maCskcb;
    private String tenCskcb;
    private String maNoiDi;
    private String lyDoVv;
    private String maLoaiKcb;
    private String ngayVao;
    private String ngayRa;
    private String ketQuaDtri;
    private String maLoaiRv;
    private String maNoiDen;
    private String chanDoanRv;
    private String maBenh;
    private String ghiChu;

}
