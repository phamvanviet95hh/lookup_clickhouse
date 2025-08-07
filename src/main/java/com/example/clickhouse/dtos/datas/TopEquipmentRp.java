package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopEquipmentRp extends BaseSummaryRp {

    private String maDv;
    private String tenDv;
    private Long total;

    public TopEquipmentRp(TopEquipmentRp rp) {
        this.maDv = rp.getMaDv();
        this.tenDv = rp.getTenDv();
        this.total = rp.getTotal();
    }
}
