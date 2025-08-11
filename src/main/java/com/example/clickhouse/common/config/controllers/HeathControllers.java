package com.example.clickhouse.common.config.controllers;

import com.example.clickhouse.bussiness.HeathBussiness;
import com.example.clickhouse.common.gloables.Constants;
import com.example.clickhouse.dtos.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HeathControllers {

    @Autowired
    private HeathBussiness heathBussiness;

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/sosuckhoedientu")
    public ResponseEntity<?> sosuckhoedientu(@RequestBody SoSucKhoeDienTuRq rq) throws Exception {
        return heathBussiness.sosuckhoedientu(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/sosuckhoedientu/danhsach")
    public ResponseEntity<?> danhsach(@RequestBody SoSucKhoeDienTuList rq) throws Exception {
        return heathBussiness.danhsach(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/sosuckhoedientu/chitiet")
    public ResponseEntity<?> chitiet(@RequestBody DetailSoSucKhoeDienTuRq rq) throws Exception {
        return heathBussiness.chitiet(rq);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giayhenkhamlai")
    public ResponseEntity<?> giayhenkhamlai(@RequestBody AppointmentLetterRq rq) throws Exception {
        return heathBussiness.giayhenkhamlai(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giayhenkhamlai/danhsach")
    public ResponseEntity<?> danhsachGiayHen(@RequestBody AppointmentLetterListRq rq) throws Exception {
        return heathBussiness.danhsachGiayHen(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giayhenkhamlai/chitiet")
    public ResponseEntity<?> danhsachGiayHenDetail(@RequestBody DetailAppointmentLetterListRq rq) throws Exception {
        return heathBussiness.danhsachGiayHenDetail(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giaychuyentuyen")
    public ResponseEntity<?> giaychuyentuyen(@RequestBody TransferPaperRq rq) throws Exception {
        return heathBussiness.giaychuyentuyen(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giaychuyentuyen/danhsach")
    public ResponseEntity<?> giaychuyentuyenDs(@RequestBody TransferPaperListRq rq) throws Exception {
        return heathBussiness.giaychuyentuyenDs(rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PostMapping(value = "/giaychuyentuyen/chitiet")
    public ResponseEntity<?> giaychuyentuyenDetail(@RequestBody TransferPaperDetailRq rq) throws Exception {
        return heathBussiness.giaychuyentuyenDetail(rq);
    }

}
