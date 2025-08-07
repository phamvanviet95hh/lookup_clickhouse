package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BaseReposeLookupTH<T> {
    private Integer statusCode;
    private Long totalRecord;
    private List<T> data;

}

