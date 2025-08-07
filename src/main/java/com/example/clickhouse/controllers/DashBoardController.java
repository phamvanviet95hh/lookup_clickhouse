package com.example.clickhouse.controllers;


import com.example.clickhouse.bussiness.DashboardBusiness;
import com.example.clickhouse.common.gloables.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Url.BACKOFFICE + "/dashboard")
@Log4j2
public class DashBoardController {

    @Autowired
    private DashboardBusiness dashboardBusiness;

    @GetMapping("disease-model")
    @PreAuthorize("hasAnyAuthority('ROLE_admin','byt','syt','tw')")
    public ResponseEntity<?> getDiseaseModel(@RequestParam(defaultValue = "1" , value = "month") Integer month,
                                             @RequestParam(defaultValue = "2000" , value = "year") Integer year,
                                             @RequestParam(defaultValue="", value = "level")String level ) {
        return ResponseEntity.ok(dashboardBusiness.getDiseaseModel(month, year, level));
    }

    @GetMapping("quality-treatment")
    @PreAuthorize("hasAnyAuthority('ROLE_admin','byt','syt','tw')")
    public ResponseEntity<?> getQualityTreatment(@RequestParam(defaultValue = "1" , value = "month") Integer month,
                                                 @RequestParam(defaultValue = "2000" , value = "year") Integer year,
                                                 @RequestParam(defaultValue="", value = "level")String level ) {
        return ResponseEntity.ok(dashboardBusiness.getQualityTreatment(month, year, level));
    }

    @GetMapping("med")
    @PreAuthorize("hasAnyAuthority('ROLE_admin','byt','syt','tw')")
    public ResponseEntity<?> getMed(@RequestParam(defaultValue = "1" , value = "month") Integer month,
                                    @RequestParam(defaultValue = "2000" , value = "year") Integer year,
                                    @RequestParam(defaultValue="", value = "level")String level ) {
        return ResponseEntity.ok(dashboardBusiness.getMed(month, year, level));
    }

    @GetMapping("equipment")
    @PreAuthorize("hasAnyAuthority('ROLE_admin','byt','syt','tw')")
    public ResponseEntity<?> getVatTu(@RequestParam(defaultValue = "1" , value = "month") Integer month,
                                      @RequestParam(defaultValue = "2000" , value = "year") Integer year,
                                      @RequestParam(defaultValue="", value = "level")String level ) {
        return ResponseEntity.ok(dashboardBusiness.getVatTu(month, year, level));
    }

    @GetMapping("quality-service")
    @PreAuthorize("hasAnyAuthority('ROLE_admin','byt','syt','tw')")
    public ResponseEntity<?> getQualityService(@RequestParam(defaultValue = "1" , value = "month") Integer month,
                                               @RequestParam(defaultValue = "2000" , value = "year") Integer year,
                                               @RequestParam(defaultValue="", value = "level")String level ) {
        return ResponseEntity.ok(dashboardBusiness.getQualityService(month, year, level));
    }

}
