package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentLetterFilter {

    private String cskcb;
    private String ngay_hen_tu;
    private String ngay_hen_den;
    private String chan_doan;

}
