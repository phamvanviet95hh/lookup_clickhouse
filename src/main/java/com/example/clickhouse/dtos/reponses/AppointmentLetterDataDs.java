package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentLetterDataDs {

    private String ma_lk;
    private String so_giay_hen_kl;
    private String ho_ten;
    private String ngay_sinh;
    private String ma_cskcb;
    private String ngay_hen_kl;
    private String chan_doan_rv;

}
