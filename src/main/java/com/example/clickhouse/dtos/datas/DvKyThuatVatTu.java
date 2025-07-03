package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DvKyThuatVatTu {

    private String maLk; // Mã liên kết
    private String stt; // Số thứ tự
    private String maDichVu; // Mã dịch vụ
    private String maNhom; // Mã nhóm
    private String tenDichVu; // Tên dịch vụ
    private String ngayYl; // Ngày yêu cầu
    private String duPhong; // Dự phòng

}
