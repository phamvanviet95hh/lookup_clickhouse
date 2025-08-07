package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedRp extends BaseSummaryRp {

    private Long soLuongBnMuaThuoc;
    private Long soLuongBnSdDvkt;
    private Long bntt;
    private Long bhtt;
    private Long bncct;
    private Long nguonkhac;
}
