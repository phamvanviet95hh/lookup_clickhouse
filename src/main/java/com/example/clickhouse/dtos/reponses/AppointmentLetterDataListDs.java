package com.example.clickhouse.dtos.reponses;

import com.example.clickhouse.dtos.datas.Cskcb;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentLetterDataListDs {

    private String ma_lk;
    private String so_giayhen_kl;
    private String ho_ten;
    private String ngay_sinh;
    private Cskcb cskcb;
    private String ngay_hen_kl;
    private String chan_doan_rv;

}
