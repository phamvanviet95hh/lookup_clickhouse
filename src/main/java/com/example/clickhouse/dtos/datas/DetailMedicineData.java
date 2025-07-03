package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailMedicineData {

    private String stt;
    private String ma_thuoc;
    private String ma_nhom;
    private String ten_thuoc;
    private String don_vi_tinh;
    private String ham_luong;
    private String duong_dung;
    private String lieu_dung;
    private String cach_dung;
    private String so_luong;
    private String ngay_th_yl;
    private String du_phong;

}
