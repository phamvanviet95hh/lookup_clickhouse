package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuatrinhDieutri {

    private String qt_benhly;
    private String tomtat_kq;
    private String pp_dieutri;
    private String ngay_tai_kham;
//    private String bac_si_dtri;
//    private String dien_thoai_bac_si;

}
