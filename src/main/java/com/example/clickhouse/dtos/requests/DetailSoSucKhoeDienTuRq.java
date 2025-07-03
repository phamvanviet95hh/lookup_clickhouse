package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailSoSucKhoeDienTuRq {

    private String request_id;
    private String ma_lk;
    private String soCCCD;
    private String maCSKCB;
    private String namQt;

}
