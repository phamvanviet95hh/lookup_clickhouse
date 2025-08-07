package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiGiamHoDto {

    private String ten; // Họ và tên của người giám hộ
    private String quan_he;
    private String so_cccd; // Số căn cước công dân của người giám hộ
    private String dien_thoai; // Số điện thoại của người giám hộ

}

