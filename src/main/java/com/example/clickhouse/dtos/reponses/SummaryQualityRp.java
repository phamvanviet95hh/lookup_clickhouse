package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.BaseSummaryRp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryQualityRp extends BaseSummaryRp {

    private Long soLuotKham;
    private Long soLuongChuyenTuyen;
    private Long soLuongVuotTuyen;
}
