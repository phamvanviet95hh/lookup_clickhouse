package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailAppointmentLetterListRq {

    private String request_id;
    private String ma_lk;
    private String soCCCD;
    private String so_giayhen_kl;

}
