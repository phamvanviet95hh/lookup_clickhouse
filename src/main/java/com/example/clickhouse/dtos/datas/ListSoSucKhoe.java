package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListSoSucKhoe {

    private String ma_lk; // Mã liên kết
    private String ma_bn; // Mã bệnh nhân
    private String ngay_vao; // Ngày vào viện
    private String ngay_ra; // Ngày ra viện
    private String chan_doan_rv; // Chuẩn đoán ra viện
    private Cskcb cskcb; // Cơ sở khám chữa bệnh

}
