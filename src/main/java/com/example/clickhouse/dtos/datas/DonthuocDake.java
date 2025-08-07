package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonthuocDake {

    private String ma_thuoc; // Mã thuốc
    private String ten_thuoc;
    private String ham_luong;
    private String don_vi_tinh;
    private String duong_dung;
    private String so_luong;
    private String lieu_dung;
    private String cach_dung;

}
