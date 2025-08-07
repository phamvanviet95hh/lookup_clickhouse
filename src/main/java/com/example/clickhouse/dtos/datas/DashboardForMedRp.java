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
public class DashboardForMedRp extends BaseSummaryRp {

    private Long soLuongBnMuaThuoc;
    private Long soLuongBnSdDvkt;
    private Long bntt;
    private Long bhtt;
    private Long bncct;
    private Long nguonkhac;
    //    List<MedRp> summary;
    List<TopEquipmentRp> services;

    public DashboardForMedRp(MedRp rp){
        this.setNgayThang(rp.getNgayThang()!=null?rp.getNgayThang():null);
        this.setTenTinh(rp.getTenTinh()!=null?rp.getTenTinh():null);
        this.setTenHuyen(rp.getTenHuyen()!=null?rp.getTenHuyen():null);
        this.setTenPhuong(rp.getTenPhuong()!=null?rp.getTenPhuong():null);
        this.setSoLuongBnMuaThuoc(rp.getSoLuongBnMuaThuoc()!=null?rp.getSoLuongBnMuaThuoc():null);
        this.setBntt(rp.getBntt()!=null?rp.getBntt():null);
        this.setBhtt(rp.getBhtt()!=null?rp.getBhtt():null);
        this.setBncct(rp.getBncct()!=null?rp.getBncct():null);
        this.setNguonkhac(rp.getNguonkhac()!=null?rp.getNguonkhac():null);
        this.setSoLuongBnSdDvkt(rp.getSoLuongBnSdDvkt()!=null?rp.getSoLuongBnSdDvkt():null);

    }

}

