package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ElectronicHumanHospitalError {

    private Integer statusCode;
    private String errorDetail;
    private String maTraCuu;

}
