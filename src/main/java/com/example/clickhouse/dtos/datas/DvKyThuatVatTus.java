package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DvKyThuatVatTus {
    private String ma_lk; // Mã liên kết
    private String stt; // Số thứ tự
    private String ma_dich_vu; // Mã dịch vụ
    private MaNhomThuoc ma_nhom; // Mã nhóm
    private String ten_dich_vu; // Tên dịch vụ
    private String ngay_yl; // Ngày yêu cầu
    private String du_phong; // Dự phòng
}
