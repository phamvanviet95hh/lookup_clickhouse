package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperListData {

    private String ma_lk;
    private String so_hoso;
    private String so_chuyentuyen;
    private String ho_ten;
    private String ma_cskcb;
    private String maNoiDi;
    private String maNoiDen;
    private String ngay_vao;
    private String ngay_ra;

}
