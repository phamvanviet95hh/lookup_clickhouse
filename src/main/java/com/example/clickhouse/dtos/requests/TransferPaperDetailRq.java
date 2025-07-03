package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperDetailRq {

    private String request_id;
    private String ma_lk;
    private String soCCCD;
    private String so_chuyentuyen;
    private String so_hoso;

}
