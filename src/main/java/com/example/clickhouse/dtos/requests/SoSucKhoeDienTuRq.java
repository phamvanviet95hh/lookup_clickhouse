package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoSucKhoeDienTuRq {
    private String requestId; // Mã định danh của request
    private String namQt; // Năm của quá trình khám chữa bệnh
    private String soCCCD; // Số căn cước công dân của người bệnh
}
