package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DotKham {

    private String ma_cskcb;
    private String ma_noi_di;
    private String ly_do_vv;
    private String ma_loai_kcb;
    private String ngay_vao;
    private String ngay_ra;
    private String ket_qua_dtri;
    private String ma_loai_rv;
    private String ma_noi_den;
    private String chan_doan_rv;
    private String ma_benh;
    private String so_cccd_nnd;
    private String ghi_chu;
}

