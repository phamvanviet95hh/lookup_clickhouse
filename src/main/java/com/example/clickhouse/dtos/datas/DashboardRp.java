package com.example.clickhouse.dtos.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 *  thống kê theo tháng
 */
@Builder
public class DashboardRp {

    private String ngayThang;
    private String tenTinh;
    private String tenHuyen;
    private String tenPhuong;
    private Long soCaMac;
    private Long soCaTuVong;
    List<TopRp> topBenhPhoBien;
    List<TopRp> benhCoTyLeTang;
}
