package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailTtHoSoBenhAn {

    private String ho_ten_cha;
    private String ho_ten_me;
    private String nguoi_giam_ho;
    private String qt_benhly;
    private String tomtat_kq;
    private String pp_dieutri;
    private String du_phong;

}
