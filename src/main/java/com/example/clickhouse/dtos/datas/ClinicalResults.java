package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicalResults {

    private String ma_nhom;
    private String ten_nhom;
    private String ma_dich_vu;
    private String ten_dich_vu;
    private String ma_chi_so;
    private String ten_chi_so;
    private String gia_tri;
    private String ket_luan;
    private String ngay_kq;
    private String link_pacs;

}

