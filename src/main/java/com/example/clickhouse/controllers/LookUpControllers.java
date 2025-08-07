package com.example.clickhouse.controllers;


import com.example.clickhouse.bussiness.HospitalLookupBussiness;
import com.example.clickhouse.bussiness.LookUpBusiness;
import com.example.clickhouse.common.gloables.Constants;
import com.example.clickhouse.dtos.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.Url.LOOKUP )
public class LookUpControllers {

    @Autowired
    private HospitalLookupBussiness hospitalLookupBussiness;

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


    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/benhandientuID")
    public ResponseEntity<?> benhandientuID (@AuthenticationPrincipal Jwt jwt, @RequestBody ElectronicHumanMaTraCuuRq rq) throws Exception {
        return lookUpBusiness.benhandientuID(jwt, rq);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/lichsukcb")
    public ResponseEntity<?> lichsukcb(@AuthenticationPrincipal Jwt jwt, @RequestBody LookupHistoryKcbRq rq) throws Exception {

        return lookUpBusiness.lichsukcb(jwt, rq);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/sosuckhoedientu")
    public ResponseEntity<?> sosuckhoedientu(@AuthenticationPrincipal Jwt jwt,@RequestBody LookupHistoryKcbRq rq) throws Exception {

        return lookUpBusiness.sosuckhoedientu(jwt, rq);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/lichsudongbofiletonghop")
    public ResponseEntity<?> lichsudongbofiletonghop(@RequestBody LookupHistoryTH rq, @RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);
        return hospitalLookupBussiness.lichsudongbofiletonghop(rq, size, page);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_admin','ROLE_syt', 'ROLE_byt', 'ROLE_tw')")
    @PostMapping(value = "/lichsudongbofilechitiet")
    public ResponseEntity<?> lichsudongbofilechitiet(@RequestBody LookupHistoryTH rq, @RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
        return hospitalLookupBussiness.lichsudongbofilechitiet(rq, size, page);
    }

}
