package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentLetterDataList {
    private String ma_lk;
    private String so_giayhen_kl;
    private Cskcb cskcb;
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
    private BenhChinh benh_chinh;
    private BenhKt benh_kt;
    private BenhYhct benh_yhct;
    private DoiTuongKcb doituong_kcb;
    private BacSi bac_si;
    private TTDV ttdv;
    private String ngay_ct;
    private String du_phong;
}
