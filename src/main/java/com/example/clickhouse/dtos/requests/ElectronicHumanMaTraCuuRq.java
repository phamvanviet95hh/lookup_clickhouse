package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicHumanMaTraCuuRq {

    String request_id;
    String maCSKCB;
    String maTraCuu;

}
