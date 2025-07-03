package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailSoSucKhoeDienTuRp {

    private String stt;
    private String ma_bn;
    private String ho_ten;
    private String so_cccd;
    private String ngay_sinh;
    private String gioi_tinh;
    private String nhom_mau;
    private QuocTich quoctich;
    private DanToc dantoc;
    private NgheNghiep nghe_nghiep;
    private String dia_chi;
    private TinhCutru tinh_cutru;
    private HuyenCuTru huyen_cutru;
    private XaCutru xa_cu_tru;
    private String dien_thoai;
    private String ma_the_bhyt;
    private MaDkbd ma_dkbd;
    private String ly_do_vv;
    private String chan_doan_rv;
    private BenhChinh benh_chinh;
    private BenhKt benh_kt;
    private BenhYhct benh_yhct;
    private NoiDi noi_di;
    private NoiDen noi_den;
    private String ngay_vao;
    private String ngay_ra;
    private KetQuaDTri ket_qua_dtri;
    private LoaiRv loai_rv;
    private String ghi_chu;
    private LoaiKcb loai_kcb;
    private Cskcb cskcb;
    private String ngay_tai_kham;
    private String du_phong;
    private List<DetailMedicineDatas> thuoc;
    private List<DetailDvKyThuatVatTus> dv_ky_thuat_vat_tu;
    private List<DetailDvCanLamSangs> dv_can_lam_sang;
    private List<DetailTtHoSoBenhAn> tt_ho_so_benh_an;

}
