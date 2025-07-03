package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRq {

    private Integer page;
    private Integer pageSize;
}
