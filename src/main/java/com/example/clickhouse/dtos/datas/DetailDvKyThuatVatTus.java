package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailDvKyThuatVatTus {

    private String stt;
    private String ma_dich_vu;
    private MaNhomThuoc ma_nhom;
    private String ten_dich_vu;
    private String ngay_yl;
    private String du_phong;

}

