package com.example.clickhouse.bussiness;


import com.example.clickhouse.common.config.Servant;
import com.example.clickhouse.common.gloables.Enums;
import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.*;
import com.example.clickhouse.mappers.DashboardMapper;
import com.example.clickhouse.mappers.FacilityMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DashboardBusiness extends BaseCmsBusiness {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Autowired
    private FacilityMapper facilityMapper;


    @Autowired
    Servant servant;

    public BaseRp<?> getDiseaseModel(Integer month, Integer year, String level) {
        String role = servant.getRole();
        List<DashboardRp> rps = new ArrayList<>();
        var reportTimeCurrent = YearMonth.of(year,month).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthCurrent = reportTimeCurrent.toLocalDate().withDayOfMonth(1).atStartOfDay();
        var reportTimeLastMonth= YearMonth.of(year,month).minusMonths(1).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthLastMonth = reportTimeLastMonth.toLocalDate().withDayOfMonth(1).atStartOfDay();
        int isByt = role.equals(Enums.UserRole.BYT.getValue()) ? 1 : 0;

        List<String> cskbs = new ArrayList<>();
        if(role.equals(Enums.UserRole.TW.getValue())) {
            cskbs.add(servant.getMaCskb());
        } else if(role.equals(Enums.UserRole.SYT.getValue())) {
            cskbs.addAll(facilityMapper.findAllCreatedBy(servant.getUsername()));
        }

        log.info("startDayOfMonthCurrent: {}", startDayOfMonthCurrent + ", reportTimeCurrent: " + reportTimeCurrent);
        log.info("cskbs: {}", cskbs);
        List<SummarySickRp> baseSummaryRps = dashboardMapper.summary(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);

        log.info("baseSummaryRps: {}", baseSummaryRps.size());
        if (!baseSummaryRps.isEmpty()){
            List<TopRp> topRps= dashboardMapper.top(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
            log.info("topRps: {}", topRps.size());
            List<TopRp> topPercent= dashboardMapper.percent(startDayOfMonthCurrent,reportTimeCurrent,startDayOfMonthLastMonth,reportTimeLastMonth, isByt,cskbs,level);
            log.info("topPercent: {}", topPercent.size());
            baseSummaryRps.parallelStream().forEach(s -> {
                DashboardRp rp = new DashboardRp();
                rp.setNgayThang(s.getNgayThang());
                rp.setTenTinh(s.getTenTinh());
                rp.setTenHuyen(s.getTenHuyen());
                rp.setTenPhuong(s.getTenPhuong());
                rp.setSoCaMac(s.getSoCaMac());
                rp.setSoCaTuVong(s.getSoCaTuVong());

                var t = topRps.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).sorted(Comparator.comparing(TopRp::getTotal).reversed()).limit(20).map(TopRp::new).collect(Collectors.toList());

                rp.setTopBenhPhoBien(t);

                var p = topPercent.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).sorted(Comparator.comparing(TopRp::getTotalCurrent).reversed()).limit(10).map(TopRp::new).collect(Collectors.toList());

                rp.setBenhCoTyLeTang(p);

                rps.add(rp);
            });
        }

        return makeSingleResponseData(rps);

    }

    public BaseRp<?> getQualityTreatment(Integer month, Integer year, String level) {
        String role = servant.getRole();
        List<DashboardForQualityRp> rps = new ArrayList<>();
        int isByt = role.equals(Enums.UserRole.BYT.getValue()) ? 1 : 0;
        var reportTimeCurrent = YearMonth.of(year,month).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthCurrent = reportTimeCurrent.toLocalDate().withDayOfMonth(1).atStartOfDay();

        List<String> cskbs = new ArrayList<>();
        if(role.equals(Enums.UserRole.TW.getValue())) {
            cskbs.add(servant.getMaCskb());
        } else if(role.equals(Enums.UserRole.SYT.getValue())) {
            cskbs.addAll(facilityMapper.findAllCreatedBy(servant.getUsername()));
        }

        List<SummaryQualityRp> baseSummaryRps = dashboardMapper.summaryForQuality(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
        if (!baseSummaryRps.isEmpty()){
            List<DischargeStatusRp> dischargeStatusRps = dashboardMapper.dischargeStatusForQuality(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
            List<TreatmentRp> treatmentRps = dashboardMapper.treatmentForQuality(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
            List<TimeDTriRp> timeDTriRps = dashboardMapper.timeDtriForQuality(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);

            baseSummaryRps.parallelStream().forEach(s -> {
                DashboardForQualityRp rp = new DashboardForQualityRp();
                rp.setNgayThang(s.getNgayThang());
                rp.setTenTinh(s.getTenTinh());
                rp.setTenHuyen(s.getTenHuyen());
                rp.setTenPhuong(s.getTenPhuong());
                rp.setSoLuotKham(s.getSoLuotKham());
                rp.setSoLuongVuotTuyen(s.getSoLuongVuotTuyen());
                rp.setSoLuongChuyenTuyen(s.getSoLuongChuyenTuyen());

                var staus = dischargeStatusRps.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).map(DischargeStatusRp::new).collect(Collectors.toList());

                rp.setTinhTrangRv(staus);

                var tr = treatmentRps.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).map(TreatmentRp::new).collect(Collectors.toList());

                rp.setKetQuaDtri(tr);

                timeDTriRps.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).findFirst().ifPresent(timeDTri -> rp.setTrungBinhSoNgayDTri(timeDTri.getTrungBinhSoNgayDTri()));

                rps.add(rp);
            });
        }

        return makeSingleResponseData(rps);

    }

    public BaseRp<?> getMed(Integer month, Integer year, String level) {

        String role = servant.getRole();
        List<DashboardForMedRp> rps = new ArrayList<>();
        int isByt = role.equals(Enums.UserRole.BYT.getValue()) ? 1 : 0;
        var reportTimeCurrent = YearMonth.of(year,month).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthCurrent = reportTimeCurrent.toLocalDate().withDayOfMonth(1).atStartOfDay();
        List<String> cskbs = new ArrayList<>();
        if(role.equals(Enums.UserRole.TW.getValue())) {
            cskbs.add(servant.getMaCskb());
        } else if(role.equals(Enums.UserRole.SYT.getValue())) {
            cskbs.addAll(facilityMapper.findAllCreatedBy(servant.getUsername()));
        }
        List<MedRp> med = dashboardMapper.med(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
        if (!med.isEmpty()){
            rps.addAll(med.stream().map(DashboardForMedRp::new).collect(Collectors.toList()));
        }

        return makeSingleResponseData(rps);
    }

    public BaseRp<?> getVatTu(Integer month, Integer year, String level) {

        String role = servant.getRole();
        List<DashboardForMedRp> rps = new ArrayList<>();
        int isByt = role.equals(Enums.UserRole.BYT.getValue()) ? 1 : 0;
        var reportTimeCurrent = YearMonth.of(year,month).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthCurrent = reportTimeCurrent.toLocalDate().withDayOfMonth(1).atStartOfDay();
        List<String> cskbs = new ArrayList<>();
        if(role.equals(Enums.UserRole.TW.getValue())) {
            cskbs.add(servant.getMaCskb());
        } else if(role.equals(Enums.UserRole.SYT.getValue())) {
            cskbs.addAll(facilityMapper.findAllCreatedBy(servant.getUsername()));
        }
        List<MedRp> med = dashboardMapper.equipment(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
        if (!med.isEmpty()){
            List<TopEquipmentRp> topEquipment = dashboardMapper.topEquipment(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
            med.parallelStream().forEach(s -> {
                DashboardForMedRp rp = new DashboardForMedRp(s);
                var service = topEquipment.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).sorted(Comparator.comparing(TopEquipmentRp::getTotal).reversed()).limit(10).map(TopEquipmentRp::new).collect(Collectors.toList());

                rp.setServices(service);
                rps.add(rp);
            });
        }
        return makeSingleResponseData(rps);
    }

    public BaseRp<?> getQualityService(Integer month, Integer year, String level) {

        String role = servant.getRole();
        List<DashboardForQualityServiceRp> rps = new ArrayList<>();
        int isByt = role.equals(Enums.UserRole.BYT.getValue()) ? 1 : 0;
        var reportTimeCurrent = YearMonth.of(year,month).atEndOfMonth().atTime(LocalTime.MAX);
        var startDayOfMonthCurrent = reportTimeCurrent.toLocalDate().withDayOfMonth(1).atStartOfDay();
        List<String> cskbs = new ArrayList<>();
        if(role.equals(Enums.UserRole.TW.getValue())) {
            cskbs.add(servant.getMaCskb());
        } else if(role.equals(Enums.UserRole.SYT.getValue())) {
            cskbs.addAll(facilityMapper.findAllCreatedBy(servant.getUsername()));
        }
        List<TopEquipmentRp> qualitySevice = dashboardMapper.qualitySevice(startDayOfMonthCurrent,reportTimeCurrent, isByt,cskbs,level);
        if (!qualitySevice.isEmpty()){
            var lst = qualitySevice.stream().map(s -> new BaseSummaryRp(s.getNgayThang(), s.getTenTinh(),s.getTenHuyen(),s.getTenPhuong())).distinct();
            lst.forEach(s -> {
                DashboardForQualityServiceRp rp = new DashboardForQualityServiceRp(s);
                var service = qualitySevice.stream().filter(x ->
                        x.getTenTinh().equals(s.getTenTinh())
                                && x.getTenHuyen().equals(s.getTenHuyen())
                                && x.getTenPhuong().equals(s.getTenPhuong())
                ).sorted(Comparator.comparing(TopEquipmentRp::getTotal).reversed()).limit(5).map(TopEquipmentRp::new).collect(Collectors.toList());

                rp.setServices(service);
                rps.add(rp);
            });
        }
        return makeSingleResponseData(rps);
    }
}
