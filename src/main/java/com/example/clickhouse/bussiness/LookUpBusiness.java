package com.example.clickhouse.bussiness;


import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.BaseReposeLookup;
import com.example.clickhouse.dtos.reponses.BaseResponseFail;
import com.example.clickhouse.dtos.reponses.ElectronicHumanHospitalError;
import com.example.clickhouse.dtos.reponses.ElectronicHumanHospitalResponse;
import com.example.clickhouse.dtos.requests.DataNhiRq;
import com.example.clickhouse.dtos.requests.ElectronicHumanHospitalRq;
import com.example.clickhouse.dtos.requests.ElectronicHumanMaTraCuuRq;
import com.example.clickhouse.entitys.auth.MedicalXml;
import com.example.clickhouse.mappers.*;
import com.example.clickhouse.models.DataNhi;
import com.example.clickhouse.repositories.AccountRegisterRepository;
import com.example.clickhouse.repositories.MedicalXmlRepository;
import com.example.clickhouse.services.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

//    public ResponseEntity<?> benhandientuID(Jwt jwt, ElectronicHumanMaTraCuuRq rq) {
//    }
}
