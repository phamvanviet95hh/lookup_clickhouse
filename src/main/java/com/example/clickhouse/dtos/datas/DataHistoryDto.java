package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataHistoryDto {

    private String maCSKCB;
    private String tenCSKCB;
    private String maLK;
    private String ngayRaVien;

}
