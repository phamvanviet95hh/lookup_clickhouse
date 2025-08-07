package com.example.clickhouse.entitys.platform;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalXml {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String loaiHoSo;
    private String maTraCuu;
    private String maTinh;
    private String maCSKCB;
    private String tenCSKCB;
    private String maLK;
    private String soCCCD;
    private String ngayRaVien;
    private String chuyenTuyen;
    private String maCSKCBNoiDen;
    private String directory;
    private String status;
    private String errorDetail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}