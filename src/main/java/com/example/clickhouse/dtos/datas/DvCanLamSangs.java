package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DvCanLamSangs {

    private String ma_lk; // Mã liên kết
    private String stt; // Số thứ tự
    private DichVu dich_vu; // Mã dịch vụ
    private String ma_chi_so; // Mã chỉ số
    private String ten_chi_so; // Tên chỉ số
    private String gia_tri; // Giá trị
    private String don_vi_do; // Đơn vị đo
    private String mo_ta; // Mô tả
    private String ket_luan; // Kết luận
    private String ngay_kq; // Ngày kết quả
    private String du_phong; // Dự phòng

}
