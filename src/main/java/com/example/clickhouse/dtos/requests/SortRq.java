package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortRq {

    private String sortBy;
    private String sortOrder; // "asc" or "desc"

}
