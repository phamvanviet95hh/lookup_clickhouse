package com.example.clickhouse.dtos.datas;

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
public class DashboardForQualityServiceRp extends BaseSummaryRp {

    List<TopEquipmentRp> services;

    public DashboardForQualityServiceRp(BaseSummaryRp rp){
        this.setNgayThang(rp.getNgayThang()!=null?rp.getNgayThang():null);
        this.setTenTinh(rp.getTenTinh()!=null?rp.getTenTinh():null);
        this.setTenHuyen(rp.getTenHuyen()!=null?rp.getTenHuyen():null);
        this.setTenPhuong(rp.getTenPhuong()!=null?rp.getTenPhuong():null);
    }

}

