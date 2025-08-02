package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPatient {

    private Long checkInId;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String maDantoc;
    private String maQuoctich;
    private String maNgheNghiep;
    private String soCccd;
    private String maTheBhyt;
    private String maDkbd;
    private String dienThoai;
    private String diaChi;
    private String matinhCuTru;
    private String mahuyenCuTru;
    private String maxaCuTru;
    private String nguoiGiamHo;
}
