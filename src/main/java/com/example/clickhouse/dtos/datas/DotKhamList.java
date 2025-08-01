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
public class DotKhamList {

    private Cskcb cskcb;
    private NoiDi noi_di;
    private String ly_do_vv;
    private String ma_loai_kcb;
    private String ngay_vao;
    private String ngay_ra;
    private String ket_qua_dtri;
    private String ma_loai_rv;
    private NoiDen noi_den;
    private String chan_doan_rv;
    private Benh benh;
    private String ghi_chu;
    private List<KqCanLamSangs> kq_can_lam_sang;
    private List<DonthuocDake> donthuoc;
    private List<PhauthuatThuthuat> phauthuat_thuthuat;
    private List<QuatrinhDieutri> quatrinh_dieutri;
}
