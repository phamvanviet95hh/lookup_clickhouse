package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LookupHistoryResponseTH {

    private String ngayGui;
    private Long tongSo;
    private Long soHSThanhCong;
    private Long soHSLoi;
    private String maTinh;
    private String maCSKCB;
    private String tenCSKCB;

}
