package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperFilter {

    private String cskcb;
    private String noi_di;
    private String noi_den;
    private String ngay_vao_tu;
    private String ngay_vao_den;


}
