package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DvCanLamSang {

    private String maLk; // Mã liên kết
    private String stt; // Số thứ tự
    private String dichVu; // Mã dịch vụ
    private String maChiSo; // Mã chỉ số
    private String tenChiSo; // Tên chỉ số
    private String giaTri; // Giá trị
    private String donViDo; // Đơn vị đo
    private String moTa; // Mô tả
    private String ketLuan; // Kết luận
    private String ngayKq; // Ngày kết quả
    private String duPhong; // Dự phòng

}
