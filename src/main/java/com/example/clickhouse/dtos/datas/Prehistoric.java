package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prehistoric {

    private String ngay_vao_ngay_ra;
    private String ma_cskcb;
    private String ten_cskcb;
    private String chan_doan_rv;
    private String ma_benh;
    private String ten_benh;
}

