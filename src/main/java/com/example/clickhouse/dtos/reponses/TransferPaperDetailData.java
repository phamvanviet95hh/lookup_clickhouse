package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferPaperDetailData {

    private String giay_chuyen_tuyen;
    private String ma_cskcb;
    private String noi_di;
    private String noi_den;
    private String ho_ten;
    private String ngay_sinh;
    private String gioi_tinh;
    private String quoctich;
    private String dantoc;
    private String nghe_nghiep;
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
    private String benh_chinh;
    private String benh_kt;
    private String benh_yhct;
    private String loai_rv;
    private String ma_lydo_ct;
    private String huong_dieu_tri;
    private String phuongtien_vc;
    private String hoten_nguoi_ht;
    private String chucdanh_nguoi_ht;
    private String bac_si;
    private String ttdv;
    private String du_phong;
}
