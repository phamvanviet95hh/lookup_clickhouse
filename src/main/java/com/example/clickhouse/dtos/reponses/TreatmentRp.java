package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.BaseSummaryRp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentRp extends BaseSummaryRp {

    private String maDtri;
    private String tenDtri;
    private Long total;

    public TreatmentRp(TreatmentRp rp) {
        this.maDtri = rp.getMaDtri();
        this.tenDtri = rp.getTenDtri();
        this.total = rp.getTotal();
    }
}