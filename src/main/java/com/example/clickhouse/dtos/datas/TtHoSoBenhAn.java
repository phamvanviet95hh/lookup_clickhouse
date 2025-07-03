package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TtHoSoBenhAn {

    private String ma_lk; // Mã liên kết
    private String ho_ten_cha; // Họ tên Cha bệnh nhân
    private String ho_ten_me; // Họ tên Mẹ bệnh nhân
    private String nguoi_giam_ho; // Người giám hộ bệnh nhân
    private String qt_benhly; // Quá trình bệnh lý
    private String tomtat_kq; // Tóm tắt kết quả
    private String pp_dieutri; // Phương pháp điều trị
    private String du_phong; // Dự phòng

}
