package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineData {

    private String maLk; // Mã liên kết
    private String maThuoc; // Mã thuốc
    private String tenThuoc; // Tên thuốc
    private String stt; // Số thứ tự
    private String donViTinh; // Đơn vị
    private String soLuong; // Số lượng
    private String hamLuong; // Hàm lượng
    private String duongDung; // Đường dùng
    private String cachDung; // Cách dùng
    private String lieuDung; // Liều dùng'
    private String ngayThYl;
    private String duPhong; // Dự phòng
    private String maNhom; // Mã nhóm thuốc

}

