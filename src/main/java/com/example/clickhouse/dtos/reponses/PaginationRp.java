package com.example.clickhouse.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRp {

    private Integer page; // Trang hiện tại
    private Integer pageSize; // Số lượng bản ghi trên mỗi trang
    private Integer totalPages; // Tổng số trang
    private Integer totalItems; // Tổng số bản ghi

}
