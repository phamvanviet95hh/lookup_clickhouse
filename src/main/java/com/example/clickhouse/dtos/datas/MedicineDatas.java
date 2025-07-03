package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDatas {

    private String ma_lk; // Mã liên kết
    private String stt; // Mã liên kết
    private String ma_thuoc; // Mã liên kết
    private MaNhomThuoc ma_nhom; // Mã liên kết
    private String ten_thuoc; // Mã liên kết
    private String don_vi_tinh; // Mã liên kết
    private String ham_luong; // Mã liên kết
    private DuongDungThuoc duong_dung; // Mã liên kết
    private String lieu_dung; // Mã liên kết
    private String cach_dung; // Mã liên kết
    private String so_luong; // Mã liên kết
    private String ngay_th_yl; // Mã liên kết
    private String du_phong; // Mã liên kết

}

