package com.example.clickhouse.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferPaperListRq {

    private String request_id;
    private String namQt;
    private String soCCCD;
    private PaginationRq pagination;
    private SortRq sort;
    private TransferPaperFilter filters;

}
