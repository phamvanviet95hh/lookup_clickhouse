package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Examination {

    private String ma_cskcb;
    private String ten_cskcb;
    private String ma_noi_di;
    private String ten_noi_di;
    private String ly_do_vv;
    private String ma_loai_kcb;
    private String ngay_vao;
    private String ngay_ra;
    private String ket_qua_dtri;
    private String ma_loai_rv;
    private String ma_noi_den;
    private String ten_noi_den;
    private String chan_doan_rv;
    private String ma_benh;
    private String ten_benh;
    private String ghi_chu;
    private List<ClinicalResults> kq_can_lam_sang;
    private List<Prescription> donthuoc_dake;
    private List<SurgicalProcedure> phauthuat_thuthuat;
    private List<TreatmentProcess> quatrinh_dieutri;

}
