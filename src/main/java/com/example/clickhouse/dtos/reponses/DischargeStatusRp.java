package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.BaseSummaryRp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DischargeStatusRp extends BaseSummaryRp {

    private String maRv;
    private String tenRv;
    private Long total;

    public DischargeStatusRp(DischargeStatusRp rp) {
        this.maRv = rp.getMaRv();
        this.tenRv = rp.getTenRv();
        this.total = rp.getTotal();
    }

}
