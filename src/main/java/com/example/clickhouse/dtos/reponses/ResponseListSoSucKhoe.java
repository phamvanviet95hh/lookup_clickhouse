package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseListSoSucKhoe<T> {

    private Integer statusCode;
    private PaginationRp pagination;
    private List<T> data;

}
