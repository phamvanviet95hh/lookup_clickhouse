package com.example.clickhouse.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookupHistoryTH {

    private String request_id;
    private String tuNgay;
    private String denNgay;
    private String loaiHoSo;

}
