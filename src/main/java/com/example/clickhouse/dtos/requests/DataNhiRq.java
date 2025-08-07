package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataNhiRq {

    private String requestId; // Mã định danh của request
    private String maCSKCB;
    private String soCCCD; // Số căn cước công dân của người bệnh
}
