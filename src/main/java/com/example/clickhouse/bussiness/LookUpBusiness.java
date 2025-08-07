package com.example.clickhouse.bussiness;


import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.*;
import com.example.clickhouse.dtos.requests.*;
import com.example.clickhouse.entitys.auth.MedicalXml;
import com.example.clickhouse.mappers.*;
import com.example.clickhouse.models.DataNhi;
import com.example.clickhouse.repositories.AccountRegisterRepository;
import com.example.clickhouse.repositories.MedicalXmlRepository;
import com.example.clickhouse.services.MinioService;
import com.google.j2objc.annotations.AutoreleasePool;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LookUpBusiness {

    @Autowired
    private AccountRegisterRepository accountRegisterRepository;

    @Autowired
    private AdminssionCheckinMapper adminssionCheckinMapper;

    @Autowired
    private LookUpMapper lookUpMapper;

    @Autowired
    private ProcessFileMapper processFileMapper;

    @Autowired
    private MedicalXmlRepository medicalXmlRepository;

    @Autowired
    private AdmissionBirthCertificateMapper admissionBirthCertificateMapper;

    @Autowired
    private AdmissionMedicalRecordMapper admissionMedicalRecordMapper;

    @Autowired
    private MedicalXmlMapper  medicalXmlMapper;

    @Autowired
    private AdmisionMedMapper admisionMedMapper;


    @Autowired
    private MinioService minioService;

    private ResponseEntity<?> checkAccessPermissionByMalk(String maLk, Map<String, Object> claims) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String maCskb = (String) claims.get("ma_cskb");
        String userNameLogin = (String) claims.get("username");
        System.out.println("maCskb = " + maCskb);
        System.out.println("userNameLogin = " + userNameLogin);
        for (var authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            if ("ROLE_syt".equals(role)) {
                String maTinhLogin = accountRegisterRepository.getMaTinhByUserName(userNameLogin);
                String maTinhRq = adminssionCheckinMapper.findMaTinhByMalk(maLk);
                System.out.println("maTinhLogin = " + maTinhLogin);
                System.out.println("maTinhRq = " + maTinhRq);
                if (!maTinhLogin.equalsIgnoreCase(maTinhRq)) {
                    return ResponseEntity.ok(BaseResponseFail.builder()
                            .statusCode(0)
                            .errorDetail("Người dùng không có quyền truy cập thông tin bệnh nhân của SYT ở tỉnh khác")
                            .build());
                }
            } else if ("ROLE_tw".equals(role)) {
                String maCskcbByCccd = adminssionCheckinMapper.findMaCskcbByMaLk(maLk);
                if (!maCskb.equalsIgnoreCase(maCskcbByCccd)) {
                    return ResponseEntity.ok(BaseResponseFail.builder()
                            .statusCode(0)
                            .errorDetail("Người dùng không có quyền truy cập thông tin bệnh nhân của bệnh viện trung ương khác")
                            .build());
                }
            }
        }
        return null; // hợp lệ
    }


    private ResponseEntity<?> checkAccessPermissionByCccd(Map<String, Object> claims, String soCccd) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String maCskb = (String) claims.get("ma_cskb");
        String userNameLogin = (String) claims.get("username");
        for (var authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_syt".equals(role)) {
                String maTinhLogin = accountRegisterRepository.getMaTinhByUserName(userNameLogin);
                String maTinhRq = adminssionCheckinMapper.findMaTinhByCccd(soCccd);
                if (!maTinhLogin.equalsIgnoreCase(maTinhRq)) {
                    return ResponseEntity.ok(BaseResponseFail.builder()
                            .statusCode(0)
                            .errorDetail("Người dùng không có quyền truy cập thông tin bệnh nhân của SYT ở tỉnh khác")
                            .build());
                }
            } else if ("ROLE_tw".equals(role)) {
                String maCskcbByCccd = adminssionCheckinMapper.findMaCskcbByCccd(soCccd);
                System.out.println("maCskcbByCccd = " + maCskcbByCccd);
                if (!maCskb.equalsIgnoreCase(maCskcbByCccd)) {
                    return ResponseEntity.ok(BaseResponseFail.builder()
                            .statusCode(0)
                            .errorDetail("Người dùng không có quyền truy cập thông tin bệnh nhân của bệnh viện trung ương khác")
                            .build());
                }
            }
        }
        return null; // hợp lệ
    }


    public ResponseEntity<?> benhandientu(Jwt jwt, ElectronicHumanHospitalRq rq) throws Exception {
        Map<String, Object> claims = jwt.getClaims();
        ResponseEntity<?> permissionCheck = checkAccessPermissionByMalk(rq.getMaLK(), claims);
        if (permissionCheck != null) return permissionCheck;

        String nameFile = null;
        String data = null;
        if (rq == null){
            return ResponseEntity.badRequest().body(this.reponseError("Không tìm thấy request gửi lên...", null));
        }

        ProcessedFiles processedFiles = processFileMapper.findByUnitNameAndMaLk(rq.getMaCSKCB(), rq.getMaLK());
        if (processedFiles.isEmpty()) {
            Optional<MedicalXml> processedFiles2 = medicalXmlRepository.findByMaCSKCBAndMaLK(rq.getMaCSKCB(), rq.getMaLK());
            if (processedFiles2.isEmpty()){
                return ResponseEntity.ok(reponseError(
                        "Không tìm thấy dữ liệu", null
                ));
            }else {
                nameFile = processedFiles2.get().getMaTraCuu();
                data = minioService.getFileAsBase64(processedFiles2.get().getDirectory());
            }
        }else  {
            data = minioService.getFileAsBase64(processedFiles.getDirectory());
            nameFile = processedFiles.getFileName();
        }
        ElectronicHumanHospitalResponse electronicHumanHospitalResponse = ElectronicHumanHospitalResponse.builder()
                .statusCode(1)
                .fileHoSo(data)
                .build();
        return ResponseEntity.ok(electronicHumanHospitalResponse);
    }


    public ElectronicHumanHospitalError reponseError(String msg, String msTraCuu){
        return ElectronicHumanHospitalError.builder()
                .statusCode(0)
                .errorDetail(msg)
                .maTraCuu(msTraCuu)
                .build();
    }

    public ResponseEntity<?> sosuckhoedientutresosinh(Jwt jwt, DataNhiRq rq) {
        Map<String, Object> claims = jwt.getClaims();
        ResponseEntity<?> permissionCheck = checkAccessPermissionByCccd(claims , rq.getSoCCCD());
        if (permissionCheck != null) return permissionCheck;


        List<DataNhiRp> nhiRps = new ArrayList<>();
        List<TienSu> tienSu = new ArrayList<>();
        List<TienSus> tienSus = new ArrayList<>();
        List<DotKhamList> dotKhams = new ArrayList<>();
        List<DotKham> dotKham = new ArrayList<>();
        List<KqCanLamSang> kqCanLamSangs = new ArrayList<>();
        List<KqCanLamSangs> kqCanLamSangss = new ArrayList<>();
        List<DonthuocDake> donthuocDakes = new ArrayList<>();
        List<PhauthuatThuthuat> phauthuatThuthuats = new ArrayList<>();
        List<QuatrinhDieutri> quatrinhDieutris = new ArrayList<>();

        Cskcb cskcb = null;
        NoiDi noiDi = null;
        NoiDen noiDen = null;

        Benh benh = null;

        String soCccd = rq.getSoCCCD().trim();
        String maCskcb = rq.getMaCSKCB().trim();
        List<DataNhi> dataNhis = null;
        System.out.println("start nhi -------------------------------->");
        dataNhis = admissionBirthCertificateMapper.findBySoSucKhoeSoSinh(maCskcb, soCccd);
        try{
            if(!dataNhis.isEmpty()){
                for (DataNhi dataNhi: dataNhis){
                    tienSu = adminssionCheckinMapper.findByTienSu(dataNhi.getSo_cccd_nguoi_giam_ho());
                    if (!tienSu.isEmpty()){
                        for (TienSu data: tienSu) {
                            cskcb = admissionMedicalRecordMapper.getCskcbFacility(data.getMa_cskcb());

                            BenhChinh benhChinh = BenhChinh.builder()
                                    .ma(data.getMa_benh())
                                    .ten(null)
                                    .build();

                            TienSus tienSuRp = TienSus.builder()
                                    .cskcb(cskcb)
                                    .ngay_vao(data.getNgay_vao())
                                    .ngay_ra(data.getNgay_ra())
                                    .chan_doan_rv(data.getChan_doan_rv())
                                    .ma_benh(benhChinh)
                                    .build();
                            tienSus.add(tienSuRp);
                        }
                    }
                    dotKham = adminssionCheckinMapper.findByDotKham(dataNhi.getSo_cccd_nguoi_giam_ho());
                    if (!dotKham.isEmpty()){
                        for (DotKham data: dotKham) {
                            kqCanLamSangs = adminssionCheckinMapper.findByKqCanLamSang(data.getSo_cccd_nnd());
                            if (!kqCanLamSangs.isEmpty()) {
                                for (KqCanLamSang dataKq: kqCanLamSangs) {
                                    DichVu dichVu = DichVu.builder()
                                            .ma(dataKq.getMa_dich_vu())
                                            .ten(null)
                                            .build();

                                    KqCanLamSangs kqCanLamSangcc = KqCanLamSangs.builder()
                                            .dich_vu(dichVu)
                                            .ma_chi_so(dataKq.getMa_chi_so())
                                            .ten_chi_so(dataKq.getTen_chi_so())
                                            .gia_tri(dataKq.getGia_tri())
                                            .ket_luan(dataKq.getNgayKq())
                                            .ngay_kq(dataKq.getKetLuan())
                                            .build();
                                    kqCanLamSangss.add(kqCanLamSangcc);
                                }
                            }

                            donthuocDakes = adminssionCheckinMapper.findByDonThuocDaKe(data.getSo_cccd_nnd());
                            phauthuatThuthuats = adminssionCheckinMapper.findByPhauThuatThuThuat(data.getSo_cccd_nnd());
                            quatrinhDieutris = adminssionCheckinMapper.findByQuaTrinhDieuTri(data.getSo_cccd_nnd());

                            cskcb = admissionMedicalRecordMapper.getCskcbFacility(data.getMa_cskcb());
                            String nameNoiDi = data.getMa_noi_di() == null ? null : admissionMedicalRecordMapper.getNoiDi(data.getMa_noi_di());
                            noiDi = NoiDi.builder()
                                    .ma(data.getMa_noi_di())
                                    .ten(nameNoiDi)
                                    .build();

                            String nameNoiDen = data.getMa_noi_den() == null ? null : admissionMedicalRecordMapper.getNoiDen(data.getMa_noi_den());
                            noiDen = NoiDen.builder()
                                    .ma(data.getMa_noi_den())
                                    .ten(nameNoiDen)
                                    .build();

                            benh = Benh.builder()
                                    .ma(data.getMa_benh())
                                    .ten(null)
                                    .build();

                            DotKhamList dotKhamRp = DotKhamList.builder()
                                    .cskcb(cskcb)
                                    .noi_di(noiDi)
                                    .ly_do_vv(data.getLy_do_vv())
                                    .ma_loai_kcb(data.getMa_loai_kcb())
                                    .ngay_vao(data.getNgay_vao())
                                    .ngay_ra(data.getNgay_ra())
                                    .ket_qua_dtri(data.getKet_qua_dtri())
                                    .ma_loai_rv(data.getMa_loai_rv())
                                    .noi_den(noiDen)
                                    .chan_doan_rv(data.getChan_doan_rv())
                                    .benh(benh)
                                    .ghi_chu(data.getGhi_chu())
                                    .kq_can_lam_sang(kqCanLamSangss)
                                    .donthuoc(donthuocDakes)
                                    .phauthuat_thuthuat(phauthuatThuthuats)
                                    .quatrinh_dieutri(quatrinhDieutris).build();
                            dotKhams.add(dotKhamRp);
                        }
                    }
                    NguoiGiamHoDto nguoiGiamHoDto = NguoiGiamHoDto.builder()
                            .ten(dataNhi.getNguoi_giam_ho())
                            .quan_he(null)
                            .so_cccd(dataNhi.getSo_cccd_nguoi_giam_ho())
                            .dien_thoai(dataNhi.getDien_thoai())
                            .build();
                    DataNhiRp dataNhiRp = DataNhiRp.builder()
                            .ho_ten(dataNhi.getHo_ten())
                            .ngay_sinh(dataNhi.getNgay_sinh())
                            .gioi_tinh(dataNhi.getGioi_tinh())
                            .ma_dantoc(dataNhi.getMa_dantoc())
                            .ma_quoctich(dataNhi.getMa_quoctich())
                            .ma_nghe_nghiep(dataNhi.getMa_nghe_nghiep())
                            .so_cccd(dataNhi.getSo_cccd())
                            .ma_the_bhyt(dataNhi.getMa_the_bhyt())
                            .ma_dkbd(dataNhi.getMa_dkbd())
                            .dien_thoai(dataNhi.getDien_thoai())
                            .dia_chi(dataNhi.getDia_chi())
                            .matinh_cu_tru(dataNhi.getMatinh_cu_tru())
                            .mahuyen_cu_tru(dataNhi.getMahuyen_cu_tru())
                            .maxa_cu_tru(dataNhi.getMaxa_cu_tru())
                            .nguoi_giam_ho(nguoiGiamHoDto)
                            .tiensu(tienSus)
                            .dotkham(dotKhams)
                            .build();

                    nhiRps.add(dataNhiRp);

                }
            }

        }catch (Exception e){
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail(e.getMessage())
                    .build();
            return ResponseEntity.ok(baseResponseFail);
        }

        BaseReposeLookup<DataNhiRp> baseReposeLookup = BaseReposeLookup.<DataNhiRp>builder()
                .statusCode(1)
                .data(nhiRps)
                .build();
        // Xử lý logic của bạn ở đây
        // Ví dụ: Trả về thông tin đã nhận
        return ResponseEntity.ok(baseReposeLookup);
    }

    public ResponseEntity<?> benhandientuID(Jwt jwt, ElectronicHumanMaTraCuuRq rq) throws Exception {

        Map<String, Object> claims = jwt.getClaims();
        ResponseEntity<?> permissionCheck = checkAccessPermissionByMalk(rq.getMaTraCuu(), claims);
        if (permissionCheck != null) return permissionCheck;

        if (rq == null){
            return ResponseEntity.badRequest().body(this.reponseError("Không tìm thấy request gửi lên...", null));
        }
        Optional<ProcessedFiles> processedFiles = processFileMapper.findByUnitNameAndFileName(rq.getMaCSKCB(), rq.getMaTraCuu());
        if (processedFiles.isEmpty()) {
            return ResponseEntity.badRequest().body(this.reponseError("Không tìm thấy hồ sơ trong kho...", null));
        }
        String data = minioService.getFileAsBase64(processedFiles.get().getDirectory());
        ElectronicHumanHospitalResponse electronicHumanHospitalResponse = ElectronicHumanHospitalResponse.builder()
                .statusCode(1)
                .fileHoSo(data)
                .build();
        return ResponseEntity.ok(electronicHumanHospitalResponse);
    }

    public ResponseEntity<?> lichsukcb(Jwt jwt, LookupHistoryKcbRq rq) {
        Map<String, Object> claims = jwt.getClaims();
        ResponseEntity<?> permissionCheck = checkAccessPermissionByCccd(claims, rq.getSoCCCD());
        if (permissionCheck != null) return permissionCheck;

        if (rq.getMaCSKCB() == null || rq.getMaCSKCB().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống MaCSKCB", "123645987"));
        }
        if (rq.getRequest_id() == null || rq.getRequest_id().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống request_id", "123645987"));
        }
        if (rq.getSoCCCD() == null || rq.getSoCCCD().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống getSoCCCD", "123645987"));
        }
        List<DataHistoryDto> dataHistoryDtoList = Optional.ofNullable(
                adminssionCheckinMapper.findByCreatedAt(rq.getMaCSKCB(), rq.getSoCCCD())
        ).orElse(Collections.emptyList());

        if (dataHistoryDtoList.isEmpty()) {
            return ResponseEntity.ok(BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không tìm thấy lịch sử khám chữa bệnh")
                    .build());
        }
        BaseReposeLookup<DataHistoryDto> baseReposeLookup = BaseReposeLookup.<DataHistoryDto>builder()
                .statusCode(1)
                .data(dataHistoryDtoList)
                .build();
        return ResponseEntity.ok(baseReposeLookup);

    }

    public ResponseEntity<?> sosuckhoedientu(Jwt jwt, LookupHistoryKcbRq rq) {

        Map<String, Object> claims = jwt.getClaims();
        ResponseEntity<?> permissionCheck = checkAccessPermissionByCccd(claims, rq.getSoCCCD());
        if (permissionCheck != null) return permissionCheck;

        List<Prehistoric> prehistoricsList = new ArrayList<>();
        List<DataPatient> dataPatientList = new ArrayList<>();
        List<Examination> examinationList = new ArrayList<>();
        List<ClinicalResults> clinicalResultList = new ArrayList<>();
        List<TreatmentProcess> treatmentProcessList = new ArrayList<>();
        List<ClinicalResultsDto> clinicalResultsList = null;
        List<Prescription> prescriptionList= null;
        List<TreatmentProcessDto> treatmentProcessListDtos= null;
        List<PrehistoricDtos> prehistorics =null;
        List<ExaminationDto> examinationDtos = null;

        if (rq.getMaCSKCB() == null || rq.getMaCSKCB().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống MaCSKCB", "123645987"));
        }
        if (rq.getRequest_id() == null || rq.getRequest_id().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống request_id", "123645987"));
        }
        if (rq.getSoCCCD() == null || rq.getSoCCCD().isEmpty()){
            return ResponseEntity.badRequest().body(this.reponseError("Không để trống getSoCCCD", "123645987"));
        }

        List<InfoPatient> infoPatients = admissionMedicalRecordMapper.findDataPatientsCustorm(rq.getMaCSKCB(), rq.getSoCCCD());

        if (!infoPatients.isEmpty()) {
            for (InfoPatient infoPatient: infoPatients){
                prehistorics = admissionMedicalRecordMapper.findDataPatientsCustormPrehistoric(rq.getMaCSKCB(), infoPatient.getCheckInId());
                examinationDtos = admissionMedicalRecordMapper.findByDoKhamCustorm(rq.getMaCSKCB(), infoPatient.getCheckInId());
                if (!examinationDtos.isEmpty()) {
                    for (ExaminationDto item : examinationDtos) {
                        clinicalResultsList = admisionMedMapper.findDataClinicalResultsCustorm(item.getMaCskcb(), item.getIdCheckIn());
                        if (!clinicalResultsList.isEmpty()){
                            for (ClinicalResultsDto clinicalResultsDto: clinicalResultsList){
                                ClinicalResults clinicalResults = ClinicalResults.builder()
                                        .ma_nhom(null)
                                        .ten_nhom(null)
                                        .ma_dich_vu(clinicalResultsDto.getMa_dich_vu())
                                        .ten_dich_vu(null)
                                        .ma_chi_so(clinicalResultsDto.getMa_chi_so())
                                        .ten_chi_so(clinicalResultsDto.getTen_chi_so())
                                        .gia_tri(clinicalResultsDto.getGia_tri())
                                        .ket_luan(clinicalResultsDto.getKet_luan())
                                        .ngay_kq(clinicalResultsDto.getNgay_kq())
                                        .link_pacs(null)
                                        .build();
                                clinicalResultList.add(clinicalResults);
                            }
                        }
                        prescriptionList = admissionCheckinRepository.findByPrescriptionList(item.getMaCskcb(), item.getIdCheckIn());

                        treatmentProcessListDtos = admissionCheckinRepository.findByTreatmentProcessList(item.getMaCskcb(), item.getIdCheckIn());

                        if (!treatmentProcessListDtos.isEmpty()){
                            for (TreatmentProcessDto treatmentProcessDto: treatmentProcessListDtos){
                                TreatmentProcess treatmentProcess = TreatmentProcess.builder()
                                        .qt_benhly(treatmentProcessDto.getQt_benhly())
                                        .tomtat_kq(treatmentProcessDto.getTomtat_kq())
                                        .pp_dieutri(treatmentProcessDto.getPp_dieutri())
                                        .ghi_chu_ngay_tai_kham(null)
                                        .bac_si_dtri(null)
                                        .dien_thoai_bac_si(null)
                                        .build();

                                treatmentProcessList.add(treatmentProcess);
                            }
                        }
                        String tenNoiDen = getNameFacility(item.getMaNoiDen());
                        String tenNoiDi = getNameFacility(item.getMaNoiDi());
                        Examination examination = Examination.builder()
                                .ma_cskcb(item.getMaCskcb())
                                .ten_cskcb(item.getTenCskcb())
                                .ma_noi_di(item.getMaNoiDi())
                                .ly_do_vv(item.getLyDoVv())
                                .ma_loai_kcb(item.getMaLoaiKcb())
                                .ngay_vao(item.getNgayVao())
                                .ngay_ra(item.getNgayRa())
                                .ket_qua_dtri(item.getKetQuaDtri())
                                .ma_loai_rv(item.getMaLoaiRv())
                                .ma_noi_den(item.getMaNoiDen())
                                .chan_doan_rv(item.getChanDoanRv())
                                .ma_benh(item.getMaBenh())
                                .ten_benh(null)
                                .ghi_chu(item.getGhiChu())
                                .ten_noi_den(tenNoiDen)
                                .ten_noi_di(tenNoiDi)
                                .kq_can_lam_sang(clinicalResultList)
                                .donthuoc_dake(prescriptionList)
                                .quatrinh_dieutri(treatmentProcessList)
                                .build();
                        examinationList.add(examination);
                    }

                }
                if (!prehistorics.isEmpty()){
                    String ngayVaoRa = prehistorics.get(0).getNgayVao() + " to " + prehistorics.get(0).getNgayRa();
                    Prehistoric prehistoric = Prehistoric.builder()
                            .ngay_vao_ngay_ra(ngayVaoRa)
                            .ma_cskcb(prehistorics.get(0).getMaCskcb())
                            .ten_cskcb(prehistorics.get(0).getTenCskcb())
                            .chan_doan_rv(prehistorics.get(0).getChanDoanRv())
                            .ma_benh(prehistorics.get(0).getMaBenh())
                            .ten_benh(null)
                            .build();
                    prehistoricsList.add(prehistoric);
                }
                DataPatient dataPatient = DataPatient.builder()
                        .ho_ten(infoPatient.getHoTen())
                        .ngay_sinh(infoPatient.getNgaySinh())
                        .gioi_tinh(infoPatient.getGioiTinh())
                        .ma_dantoc(infoPatient.getMaDantoc())
                        .ma_quoctich(infoPatient.getMaQuoctich())
                        .ma_nghe_nghiep(infoPatient.getMaNgheNghiep())
                        .so_cccd(infoPatient.getSoCccd())
                        .ma_the_bhyt(infoPatient.getMaTheBhyt())
                        .ma_dkbd(infoPatient.getMaDkbd())
                        .dien_thoai(infoPatient.getDienThoai())
                        .dia_chi(infoPatient.getDiaChi())
                        .matinh_cu_tru(infoPatient.getMatinhCuTru())
                        .mahuyen_cu_tru(infoPatient.getMahuyenCuTru())
                        .maxa_cu_tru(infoPatient.getMaxaCuTru())
                        .nguoi_giam_ho(infoPatient.getNguoiGiamHo())
                        .tiensu(prehistoricsList)
                        .dotkham(examinationList)
                        .build();
                dataPatientList.add(dataPatient);
            }

        }

        BaseReposeLookup<DataPatient> baseReposeLookup = BaseReposeLookup.<DataPatient>builder()
                .statusCode(1)
                .data(dataPatientList)
                .build();
        return  ResponseEntity.ok(baseReposeLookup);

    }


    public ResponseEntity<?> lichsudongbofiletonghop(LookupHistoryTH rq, int size, int page) {
        if (rq.getTuNgay().isEmpty()) {
            return ResponseEntity.ok(this.reponseError("Không để trống ngày bắt đầu", null));
        }
        if (rq.getDenNgay().isEmpty()) {
            return ResponseEntity.ok(this.reponseError("Không để trống ngày kết thúc", null));
        }

        LocalDateTime startDate = convertToStartOfDay(rq.getTuNgay());
        LocalDateTime endDate = convertToEndOfDay(rq.getDenNgay());


        Pageable pageable = PageRequest.of(page, size);
        int offset = page * size;
        RowBounds rowBounds = new RowBounds(offset, size);

        List<LookupHistoryResponseTH> lookupHistoryResponseTHList = medicalXmlMapper.findByCreatedAt(
                startDate, endDate, rq.getLoaiHoSo(), rowBounds
        );

        Long totalRecord = medicalXmlMapper.findByUpdatedAt(startDate, endDate, rq.getLoaiHoSo());


        Page<LookupHistoryResponseTH> pageResult = new PageImpl<>(
                lookupHistoryResponseTHList, pageable, totalRecord != null ? totalRecord : 0
        );

        BaseReposeLookupTH<LookupHistoryResponseTH> baseReposeLookup = BaseReposeLookupTH.<LookupHistoryResponseTH>builder()
                .statusCode(1)
                .totalRecord(totalRecord != null ? totalRecord : 0)
                .data(pageResult.getContent())
                .build();

        return ResponseEntity.ok(baseReposeLookup);
    }

    public static LocalDateTime convertToStartOfDay(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atStartOfDay();
    }

    public static LocalDateTime convertToEndOfDay(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atTime(23, 59, 59);
    }

    public ResponseEntity<?> lichsudongbofilechitiet(LookupHistoryTH rq, int size, int page) {

        if (rq.getTuNgay().isEmpty()) {
            return ResponseEntity.ok(this.reponseError("Không để trống ngày bắt đầu", null));
        }
        if (rq.getDenNgay().isEmpty()) {
            return ResponseEntity.ok(this.reponseError("Không để trống ngày kết thúc", null));
        }

        LocalDateTime startDate = convertToStartOfDay(rq.getTuNgay());
        LocalDateTime endDate = convertToEndOfDay(rq.getDenNgay());

        int offset = page * size;
        RowBounds rowBounds = new RowBounds(offset, size);

        List<LookupHistoryResponseDetail> lookupHistoryResponseTHList = medicalXmlMapper.findByMaLk(
                startDate, endDate, rq.getLoaiHoSo(), rowBounds
        );

        Long totalRecord = medicalXmlMapper.findByUpdatedAt(startDate, endDate, rq.getLoaiHoSo());

        Pageable pageable = PageRequest.of(page, size);
        Page<LookupHistoryResponseDetail> pageResult = new PageImpl<>(
                lookupHistoryResponseTHList, pageable, totalRecord != null ? totalRecord : 0
        );

        BaseReposeLookupTH<LookupHistoryResponseDetail> baseReposeLookup = BaseReposeLookupTH.<LookupHistoryResponseDetail>builder()
                .statusCode(1)
                .totalRecord(totalRecord != null ? totalRecord : 0)
                .data(pageResult.getContent())
                .build();

        return ResponseEntity.ok(baseReposeLookup);
    }


}
