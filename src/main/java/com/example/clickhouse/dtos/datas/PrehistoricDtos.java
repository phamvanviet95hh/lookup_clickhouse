package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrehistoricDtos {

    private String ngayVao;
    private  String ngayRa;
    private String maCskcb;
    private String tenCskcb;
    private String chanDoanRv;
    private String maBenh;

}

