package com.example.clickhouse.dtos.datas;

import com.example.clickhouse.dtos.reponses.DischargeStatusRp;
import com.example.clickhouse.dtos.reponses.TreatmentRp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 *  thống kê theo tháng
 */
@Builder
public class DashboardForQualityRp extends BaseSummaryRp {

    private Long soLuotKham;
    private Long soLuongChuyenTuyen;
    private Long soLuongVuotTuyen;
    List<DischargeStatusRp> tinhTrangRv;
    List<TreatmentRp> ketQuaDtri;
    private Float trungBinhSoNgayDTri;
}
