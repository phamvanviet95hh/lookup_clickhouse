package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KqCanLamSang {

    private String ma_dich_vu;
    private String ma_chi_so;
    private String ten_chi_so;
    private String gia_tri;
    private String ketLuan;
    private String ngayKq;
}
