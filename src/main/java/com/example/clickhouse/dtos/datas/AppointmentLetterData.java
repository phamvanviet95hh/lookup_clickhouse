package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentLetterData {
    private String ma_lk;
    private String so_giayhen_kl;
    private String ho_ten;
    private String ngay_sinh;
    private String gioi_tinh;
    private String dia_chi;
    private String ma_the_bhyt;
    private String gt_the_den;
    private String ngay_vao;
    private String ngay_vao_noi_tru;
    private String ngay_ra;
    private String ngay_hen_kl;
    private String chan_doan_rv;
    private String benh_chinh;
    private String benh_kt;
    private String benh_yhct;
    private String doituong_kcb;
    private String bac_si;
    private String ttdv;
    private String ngay_ct;
    private String ma_cskcb;
    private String du_phong;
}