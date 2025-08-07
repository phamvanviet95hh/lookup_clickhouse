package com.example.clickhouse.bussiness;

import com.example.clickhouse.dtos.reponses.ElectronicHumanHospitalError;
import com.example.clickhouse.dtos.reponses.LookupHistoryResponseDetail;
import com.example.clickhouse.dtos.reponses.LookupHistoryResponseTH;
import com.example.clickhouse.dtos.requests.BaseReposeLookupTH;
import com.example.clickhouse.dtos.requests.LookupHistoryTH;
import com.example.clickhouse.repositories.platform.MedicalXmlRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HospitalLookupBussiness {

    @Autowired
    private MedicalXmlRepository2 medicalXmlRepository;

    public ResponseEntity<?> lichsudongbofiletonghop(LookupHistoryTH rq, int size, int page) {

        if (rq.getTuNgay().isEmpty()){
            return ResponseEntity.ok(this.reponseError("Không để trống ngày bắt đầu", null));
        }
        if (rq.getDenNgay().isEmpty()){
            return ResponseEntity.ok(this.reponseError("Không để trống ngày kết thúc", null));
        }

        LocalDateTime startDate = convertToStartOfDay(rq.getTuNgay());
        LocalDateTime endDate = convertToEndOfDay(rq.getDenNgay());

        Pageable pageable = PageRequest.of(page, size); // page = 0-based
        Page<LookupHistoryResponseTH> lookupHistoryResponseTHList = medicalXmlRepository.findByCreatedAt(startDate, endDate, rq.getLoaiHoSo(), pageable);

        Long totalRecord = medicalXmlRepository.findByUpdatedAt(startDate, endDate, rq.getLoaiHoSo());

        BaseReposeLookupTH<LookupHistoryResponseTH> baseReposeLookup = BaseReposeLookupTH.<LookupHistoryResponseTH>builder()
                .statusCode(1)
                .totalRecord(totalRecord)
                .data(lookupHistoryResponseTHList.getContent())
                .build();
        return  ResponseEntity.ok(baseReposeLookup);
    }

    public static LocalDateTime convertToStartOfDay(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atStartOfDay(); // 00:00:00
    }

    public static LocalDateTime convertToEndOfDay(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atTime(23, 59, 59); // 23:59:59
    }

    public ResponseEntity<?> lichsudongbofilechitiet(LookupHistoryTH rq, int size, int page) {

        if (rq.getTuNgay().isEmpty()){
            return ResponseEntity.ok(this.reponseError("Không để trống ngày bắt đầu", null));
        }
        if (rq.getDenNgay().isEmpty()){
            return ResponseEntity.ok(this.reponseError("Không để trống ngày kết thúc", null));
        }

        LocalDateTime startDate = convertToStartOfDay(rq.getTuNgay());
        LocalDateTime endDate = convertToEndOfDay(rq.getDenNgay());

        Pageable pageable = PageRequest.of(page, size); // page = 0-based
        Page<LookupHistoryResponseDetail> lookupHistoryResponseTHList = medicalXmlRepository.findByMaLk(startDate, endDate, rq.getLoaiHoSo(), pageable);

        Long totalRecord = medicalXmlRepository.findByUpdatedAt(startDate, endDate, rq.getLoaiHoSo());

        BaseReposeLookupTH<LookupHistoryResponseDetail> baseReposeLookup = BaseReposeLookupTH.<LookupHistoryResponseDetail>builder()
                .statusCode(1)
                .totalRecord(totalRecord)
                .data(lookupHistoryResponseTHList.getContent())
                .build();
        return  ResponseEntity.ok(baseReposeLookup);

    }

    public ElectronicHumanHospitalError reponseError(String msg, String msTraCuu){
        return ElectronicHumanHospitalError.builder()
                .statusCode(0)
                .errorDetail(msg)
                .maTraCuu(msTraCuu)
                .build();
    }

}
