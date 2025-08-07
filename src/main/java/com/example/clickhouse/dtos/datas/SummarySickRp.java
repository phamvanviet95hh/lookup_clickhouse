package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummarySickRp extends BaseSummaryRp {

    private Long soCaMac;
    private Long soCaTuVong;
}
