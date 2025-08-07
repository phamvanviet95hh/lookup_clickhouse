package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DataPatient {

    private String ho_ten;
    private String ngay_sinh;
    private String gioi_tinh;
    private String ma_dantoc;
    private String ma_quoctich;
    private String ma_nghe_nghiep;
    private String so_cccd;
    private String ma_the_bhyt;
    private String ma_dkbd;
    private String dien_thoai;
    private String dia_chi;
    private String matinh_cu_tru;
    private String mahuyen_cu_tru;
    private String maxa_cu_tru;
    private String nguoi_giam_ho;
    private String quan_he_nguoi_giam_ho;
    private String so_cccd_nguoi_giam_ho;
    private String dien_thoai_nguoi_giam_ho;
    private List<Prehistoric> tiensu;
    private List<Examination> dotkham;

}
