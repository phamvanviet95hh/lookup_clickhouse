package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TienSu {

    public String ngay_vao; // Ngày vào
    public String ngay_ra; // Ngày ra
    public String ma_cskcb; // Mã cơ sở khám chữa bệnh
    public String ten_cskcb; // Tên cơ sở khám chữa bệnh
    public String chan_doan_rv; // Chẩn đoán ra viện
    public String ma_benh; // Mã bệnh

}
