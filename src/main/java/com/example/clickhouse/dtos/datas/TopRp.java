package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopRp extends BaseSummaryRp {

    private String code;
    private String description;
    private Long total;
    private Long totalCurrent;
    private Long totalLast;
    private Double rate;

    public TopRp(TopRp r){
        this.code = r.getCode()!=null?r.getCode():"";
        this.description = r.getDescription()!=null?r.getDescription():"";
        this.total = r.getTotal()!=null?r.getTotal():null;
        this.totalCurrent = r.getTotalCurrent()!=null?r.getTotalCurrent():null;
        this.totalLast = r.getTotalLast()!=null?r.getTotalLast():null;
        this.rate = r.getRate()!=null?r.getRate():null;
    }
}
