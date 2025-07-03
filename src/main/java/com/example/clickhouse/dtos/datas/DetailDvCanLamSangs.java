package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailDvCanLamSangs {

    private String stt;
    private DichVu dich_vu;
    private String ma_chi_so;
    private String ten_chi_so;
    private String gia_tri;
    private String don_vi_do;
    private String mo_ta;
    private String ket_luan;
    private String ngay_kq;
    private String du_phong;

}
