package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperDetailDatas {

    private String giay_chuyen_tuyen;
    private Cskcb cskcb;
    private NoiDi noi_di;
    private NoiDen noi_den;
    private String ho_ten;
    private String ngay_sinh;
    private String gioi_tinh;
    private QuocTich quoctich;
    private DanToc dantoc;
    private NgheNghiep nghe_nghiep;
    private String dia_chi;
    private String ma_the_bhyt;
    private String gt_the_den;
    private String ngay_vao;
    private String ngay_vao_noi_tru;
    private String ngay_ra;
    private String dau_hieu_ls;
    private String chan_doan_rv;
    private String qt_benhly;
    private String tomtat_kq;
    private String pp_dieutri;
    private BenhChinh benh_chinh;
    private BenhKt benh_kt;
    private BenhYhct benh_yhct;
    //    private String tinh_trang_ct;
    private LoaiRv loai_rv;
    private String ma_lydo_ct;
    private String huong_dieu_tri;
    private String phuongtien_vc;
    private String hoten_nguoi_ht;
    private String chucdanh_nguoi_ht;
    private BacSi bac_si;
    private TTDV ttdv;
    private String du_phong;


}
