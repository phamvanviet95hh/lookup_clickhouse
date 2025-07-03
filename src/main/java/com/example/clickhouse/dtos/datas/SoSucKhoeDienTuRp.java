package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoSucKhoeDienTuRp {
    private Integer statusCode;
    private List<SoSucKhoeDienTuData> data;
}
