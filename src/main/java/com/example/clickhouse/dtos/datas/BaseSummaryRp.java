package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSummaryRp {

    private String ngayThang;
    private String tenTinh;
    private String tenHuyen;
    private String tenPhuong;
}
