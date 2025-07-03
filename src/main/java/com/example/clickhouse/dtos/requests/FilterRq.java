package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRq {

    private String cskcb;
    private String chan_doan;

}
