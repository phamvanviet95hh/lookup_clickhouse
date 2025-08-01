package com.example.clickhouse.controllers;


import com.example.clickhouse.bussiness.LookUpBusiness;
import com.example.clickhouse.common.gloables.Constants;
import com.example.clickhouse.dtos.requests.DataNhiRq;
import com.example.clickhouse.dtos.requests.ElectronicHumanHospitalRq;
import com.example.clickhouse.dtos.requests.ElectronicHumanMaTraCuuRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Url.LOOKUP )
public class LookUpControllers {

    @Autowired
    private LookUpBusiness lookUpBusiness;

    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/benhandientu")
    public ResponseEntity<?> benhandientu(@AuthenticationPrincipal Jwt jwt, @RequestBody ElectronicHumanHospitalRq rq) throws Exception {
        return lookUpBusiness.benhandientu(jwt, rq);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/sosuckhoedientutresosinh")
    public ResponseEntity<?> sosuckhoedientutresosinh(@AuthenticationPrincipal Jwt jwt, @RequestBody DataNhiRq rq) throws Exception {
        return lookUpBusiness.sosuckhoedientutresosinh(jwt, rq);
    }


//    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
//    @PostMapping(value = "/benhandientuID")
//    public ResponseEntity<?> benhandientuID (@AuthenticationPrincipal Jwt jwt, @RequestBody ElectronicHumanMaTraCuuRq rq) throws Exception {
//
//        return lookUpBusiness.benhandientuID(jwt, rq);
//    }

}
