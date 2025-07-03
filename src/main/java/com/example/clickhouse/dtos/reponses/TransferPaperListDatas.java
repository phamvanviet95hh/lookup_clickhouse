package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperListDatas {

    private String ma_lk;
    private String so_hoso;
    private String so_chuyentuyen;
    private String ho_ten;
    private Cskcb cskcb;
    private NoiDi noi_di;
    private NoiDen noi_den;
    private String ngay_vao;
    private String ngay_ra;

}

