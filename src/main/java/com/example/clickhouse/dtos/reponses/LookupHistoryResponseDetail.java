package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LookupHistoryResponseDetail {
    private String maLK;
    private String soCCCD;
    private String ngayRaVien;
    private String maTinh;
    private String chuyenTuyen;
    private String maCSKCBNoiDen;
    private String fileHoSo;
    private String maCSKCB;
    private String tenCSKCB;
    private String errorDetail;
    private String request_time;
    private String loaiHoSo;
    private String trangThaiDongBo;
}

