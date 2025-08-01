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
public class DataNhiRp {

    private String ho_ten; // Họ và tên của người bệnh
    private String ngay_sinh; // Ngày sinh của người bệnh
    private String gioi_tinh; // Giới tính của người bệnh
    private String ma_dantoc; // Mã dân tộc của người bệnh
    private String ma_quoctich; // Mã quốc tịch của người bệnh
    private String ma_nghe_nghiep; // Mã nghề nghiệp của người bệnh
    private String so_cccd; // Số căn cước công dân của người bệnh
    private String ma_the_bhyt; // Mã thẻ bảo hiểm y tế của người bệnh
    private String ma_dkbd; // Mã đăng ký khám chữa bệnh của người bệnh
    private String dien_thoai; // Số điện thoại của người bệnh
    private String dia_chi; // Địa chỉ của người bệnh
    private String matinh_cu_tru; // Mã tỉnh cư trú của người bệnh
    private String mahuyen_cu_tru; // Mã huyện cư trú của người bệnh
    private String maxa_cu_tru; // Mã xã cư trú của người bệnh
    private NguoiGiamHoDto nguoi_giam_ho; // Người giám hộ của người bệnh
    private List<TienSus> tiensu; // Tiền sử bệnh của người bệnh
    private List<DotKhamList> dotkham; // Danh sách phiếu khám của người bệnh


}
