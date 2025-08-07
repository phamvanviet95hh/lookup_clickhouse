package com.example.clickhouse.repositories.platform;

import com.example.clickhouse.dtos.reponses.LookupHistoryResponseDetail;
import com.example.clickhouse.dtos.reponses.LookupHistoryResponseTH;
import com.example.clickhouse.entitys.platform.MedicalXml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicalXmlRepository2 extends JpaRepository<MedicalXml, Long> {
    Optional<MedicalXml> findByMaCSKCBAndMaLK(String maCSKCB, String maLK);

    @Query(value = "select new com.example.clickhouse.dtos.reponses.LookupHistoryResponseTH(" +
            " TO_CHAR(mx.createdAt, 'DD/MM/YYYY') AS ngayGui, count(mx.Id) as tongSo," +
            " sum(case when mx.status = 'true' then 1 else 0 end) as soHSThanhCong," +
            " sum(case when mx.status = 'false' then 1 else 0 end) as soHSLoi, " +
            " mx.maTinh as maTinh, mx.maCSKCB as maCSKCB, mx.tenCSKCB as tenCSKCB) from" +
            " MedicalXml mx where mx.loaiHoSo = :loaiHoSo and mx.createdAt between :startDate and :endDate group by maCSKCB, tenCSKCB, TO_CHAR(mx.createdAt, 'DD/MM/YYYY'), maTinh")
    Page<LookupHistoryResponseTH> findByCreatedAt(LocalDateTime startDate, LocalDateTime endDate, String loaiHoSo, Pageable pageable);

    @Query(value = "select count(mx.Id) from MedicalXml mx where mx.loaiHoSo = :loaiHoSo and mx.createdAt between :startDate and :endDate")
    Long findByUpdatedAt(LocalDateTime startDate, LocalDateTime endDate, String loaiHoSo);

    @Query(value = "select new com.example.clickhouse.dtos.reponses.LookupHistoryResponseDetail(" +
            " mx.maLK as maLK, mx.soCCCD as soCCCD, mx.ngayRaVien as ngayRaVien, " +
            " mx.maTinh as maTinh, mx.chuyenTuyen as chuyenTuyen, mx.maCSKCBNoiDen as maCSKCBNoiDen, mx.maTraCuu as fileHoSo, mx.maCSKCB as maCSKCB, mx.tenCSKCB as tenCSKCB," +
            "  mx.errorDetail as errorDetail,TO_CHAR(mx.createdAt, 'DD/MM/YYYY') as request_time, mx.loaiHoSo as loaiHoSo , case when mx.status = 'true' then 'Thành công' else 'Thất bại' end AS trangThaiDongBo) from " +
            " MedicalXml mx where mx.loaiHoSo = :loaiHoSo and mx.createdAt between :startDate and :endDate")
    Page<LookupHistoryResponseDetail> findByMaLk(LocalDateTime startDate, LocalDateTime endDate, String loaiHoSo, Pageable pageable);
}
