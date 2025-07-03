package com.example.clickhouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patients {

    private String uuid;
    private String diaChi;
    private String dienThoai;
    private String gioiTinh;
    private String hoTen;
    private String hoTenCha;
    private String hoTenMe;
    private String maDanToc;
    private String maNgheNghiep;
    private String maQuocTich;
    private String maHuyenCuTru;
    private String maTinhCuTru;
    private String maXaCuTru;
    private String ngaySinh;
    private String nhomMau;
    private String soCccd;
    private String stt;

}

