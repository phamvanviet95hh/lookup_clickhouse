package com.example.clickhouse.bussiness;

import com.example.clickhouse.dtos.datas.*;
import com.example.clickhouse.dtos.reponses.*;
import com.example.clickhouse.dtos.requests.*;
import com.example.clickhouse.mappers.AdmissionMedicalRecordMapper;
import com.example.clickhouse.mappers.AppointmentLetterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HeathBussiness {

    @Autowired
    private AdmissionMedicalRecordMapper admissionMedicalRecordMapper;

    @Autowired
    private AppointmentLetterMapper appointmentLetterMapper;

    public ResponseEntity<?> sosuckhoedientu(SoSucKhoeDienTuRq rq) {

        SoSucKhoeDienTuRp soSucKhoeDienTuRp = null;
        List<SoSucKhoeDienTuData> soSucKhoeDienTuData = new ArrayList<>();
        List<MedicineData> medicineData = null;
        List<MedicineDatas> medicineDatas =  new ArrayList<>();
        List<DvKyThuatVatTu> dvKyThuatVatTuList = null;
        List<DvKyThuatVatTus> dvKyThuatVatTuLists = new ArrayList<>();
        List<DvCanLamSang> dvCanLamSangList = null;
        List<DvCanLamSangs> dvCanLamSangLists = new ArrayList<>();
        List<TtHoSoBenhAn> ttHoSoBenhAnList = null;

        String soCccd = rq.getSoCCCD().trim();
        String namQt = rq.getNamQt().trim();
        List<SoSucKhoeDienTuDataSt> soSucKhoeDienTuDataSt = null;

        if (rq == null) {
            return ResponseEntity.badRequest().body("Request body is null");
        }else {
            System.out.println("start-->>>>>>>>>>>>>>>>>>>");
            soSucKhoeDienTuDataSt = admissionMedicalRecordMapper.findSoSucKhoeDienTuCustorm(namQt, soCccd);
            System.out.println("soSucKhoeDienTuDataSt: " + soSucKhoeDienTuDataSt);
            try {
                if (!soSucKhoeDienTuDataSt.isEmpty()){
                    for (SoSucKhoeDienTuDataSt soSucKhoeDienTuDataSt1 : soSucKhoeDienTuDataSt){
                        medicineData = admissionMedicalRecordMapper.findMedicineCustorm(soSucKhoeDienTuDataSt1.getNamQt(), soSucKhoeDienTuDataSt1.getSoCccd());
                        if (medicineData != null && !medicineData.isEmpty()) {
                            for (MedicineData medicineData1 : medicineData) {
                                MaNhomThuoc maNhomThuocs = MaNhomThuoc.builder()
                                        .ma(medicineData1.getMaNhom())
                                        .ten(null)
                                        .build();
                                DuongDungThuoc duongDungThuocs = DuongDungThuoc.builder()
                                        .ma(medicineData1.getDuongDung())
                                        .ten(null)
                                        .build();

                                MedicineDatas medicineDatas1 = MedicineDatas.builder()
                                        .stt(medicineData1.getStt())
                                        .ma_lk(medicineData1.getMaLk())
                                        .ma_thuoc(medicineData1.getMaThuoc())
                                        .ma_nhom(maNhomThuocs)
                                        .ten_thuoc(medicineData1.getTenThuoc())
                                        .don_vi_tinh(medicineData1.getDonViTinh())
                                        .ham_luong(medicineData1.getHamLuong())
                                        .duong_dung(duongDungThuocs)
                                        .lieu_dung(medicineData1.getLieuDung())
                                        .cach_dung(medicineData1.getCachDung())
                                        .so_luong(medicineData1.getSoLuong())
                                        .ngay_th_yl(medicineData1.getNgayThYl())
                                        .du_phong(medicineData1.getDuPhong())
                                        .build();
                                medicineDatas.add(medicineDatas1);
                            }
                        }

                        dvKyThuatVatTuList = admissionMedicalRecordMapper.findDvKyThuatVatTuCustorm(namQt, soCccd);

                        if (dvKyThuatVatTuList != null && !dvKyThuatVatTuList.isEmpty()) {
                            for (DvKyThuatVatTu dvKyThuatVatTu : dvKyThuatVatTuList) {
                                MaNhomThuoc maNhomThuoc1 = MaNhomThuoc.builder()
                                        .ma(dvKyThuatVatTu.getMaNhom())
                                        .ten(null)
                                        .build();

                                DvKyThuatVatTus dvKyThuatVatTus1 = DvKyThuatVatTus.builder()
                                        .stt(dvKyThuatVatTu.getStt())
                                        .ma_dich_vu(dvKyThuatVatTu.getMaDichVu())
                                        .ma_nhom(maNhomThuoc1)
                                        .ma_lk(dvKyThuatVatTu.getMaLk())
                                        .ten_dich_vu(dvKyThuatVatTu.getTenDichVu())
                                        .ngay_yl(dvKyThuatVatTu.getNgayYl())
                                        .du_phong(dvKyThuatVatTu.getDuPhong())
                                        .build();
                                dvKyThuatVatTuLists.add(dvKyThuatVatTus1);
                            }
                        }
                        dvCanLamSangList = admissionMedicalRecordMapper.findDvCanLamSangCustorm(namQt, soCccd);

                        if (dvCanLamSangList != null && !dvCanLamSangList.isEmpty()) {
                            for (DvCanLamSang dvCanLamSang : dvCanLamSangList) {
                                DichVu dichVu = DichVu.builder()
                                        .ma(dvCanLamSang.getDichVu())
                                        .ten(null)
                                        .build();

                                DvCanLamSangs dvCanLamSangs1 = DvCanLamSangs.builder()
                                        .stt(dvCanLamSang.getStt())
                                        .dich_vu(dichVu)
                                        .ma_lk(dvCanLamSang.getMaLk())
                                        .ma_chi_so(dvCanLamSang.getMaChiSo())
                                        .ten_chi_so(dvCanLamSang.getTenChiSo())
                                        .gia_tri(dvCanLamSang.getGiaTri())
                                        .don_vi_do(dvCanLamSang.getDonViDo())
                                        .mo_ta(dvCanLamSang.getMoTa())
                                        .ket_luan(dvCanLamSang.getKetLuan())
                                        .ngay_kq(dvCanLamSang.getNgayKq())
                                        .du_phong(dvCanLamSang.getDuPhong())
                                        .build();
                                dvCanLamSangLists.add(dvCanLamSangs1);
                            }
                        }

                        ttHoSoBenhAnList = admissionMedicalRecordMapper.findTtHoSoBenhAnCustorm(namQt, soCccd);

                        QuocTich quocTich = QuocTich.builder()
                                .ma(soSucKhoeDienTuDataSt1.getQuocTich())
                                .ten(null)
                                .build();

                        DanToc danToc = DanToc.builder()
                                .ma(soSucKhoeDienTuDataSt1.getDanToc())
                                .ten(null)
                                .build();

                        NgheNghiep ngheNghiep = NgheNghiep.builder()
                                .ma(soSucKhoeDienTuDataSt1.getNgheNghiep())
                                .ten(null)
                                .build();

                        MaDkbd maDkbd = MaDkbd.builder()
                                .ma(soSucKhoeDienTuDataSt1.getMaDkbd())
                                .ten(null)
                                .build();

                        BenhChinh benhChinh = BenhChinh.builder()
                                .ma(soSucKhoeDienTuDataSt1.getBenhChinh())
                                .ten(null)
                                .build();

                        BenhKt benhKt = BenhKt.builder()
                                .ma(soSucKhoeDienTuDataSt1.getBenhKt())
                                .ten(null)
                                .build();

                        BenhYhct benhYhct = BenhYhct.builder()
                                .ma(soSucKhoeDienTuDataSt1.getBenhYhct())
                                .ten(null)
                                .build();

                        KetQuaDTri ketQuaDTri = KetQuaDTri.builder()
                                .ma(soSucKhoeDienTuDataSt1.getKetQuaDtri())
                                .ten(null)
                                .build();

                        LoaiRv loaiRv = LoaiRv.builder()
                                .ma(soSucKhoeDienTuDataSt1.getLoaiRv())
                                .ten(null)
                                .build();

                        LoaiKcb loaiKcb = LoaiKcb.builder()
                                .ma(soSucKhoeDienTuDataSt1.getLoaiKcb())
                                .ten(null)
                                .build();

                        System.out.println(soSucKhoeDienTuDataSt1.getCskb());
                        Cskcb cskcb = Cskcb.builder()
                                .ma(soSucKhoeDienTuDataSt1.getCskb())
                                .ten(soSucKhoeDienTuDataSt1.getCskb() == null ? null : "test")
                                .build();

                        TinhCutru tinhCutru = TinhCutru.builder().ma(Long.valueOf(soSucKhoeDienTuDataSt1.getTinhCuTru())).ten("Tesst").build();

                        HuyenCuTru huyenCuTru = HuyenCuTru.builder()
                                .ma(Long.valueOf(soSucKhoeDienTuDataSt1.getHuyenCuTru()))
                                .ten("test")
                                .build();

                        XaCutru xaCutru = XaCutru.builder()
                                .ma(Long.valueOf(soSucKhoeDienTuDataSt1.getXaCuTru()))
                                .ten("test")
                                .build();

                        String nameNoiDi = soSucKhoeDienTuDataSt1.getNoiDi() == null ? null : "test";

                        NoiDi noiDi = NoiDi.builder()
                                .ma(soSucKhoeDienTuDataSt1.getNoiDi())
                                .ten(nameNoiDi)
                                .build();

                        NoiDen noiDen = NoiDen.builder()
                                .ma(soSucKhoeDienTuDataSt1.getNoiDen())
                                .ten(soSucKhoeDienTuDataSt1.getNoiDen() == null ? null : "test")
                                .build();


                        SoSucKhoeDienTuData soSucKhoeDienTuData1 = SoSucKhoeDienTuData.builder()
                                .ma_lk(soSucKhoeDienTuDataSt1.getMaLk())
                                .stt(soSucKhoeDienTuDataSt1.getStt())
                                .ma_bn(soSucKhoeDienTuDataSt1.getMaBn())
                                .ho_ten(soSucKhoeDienTuDataSt1.getHoTen())
                                .so_cccd(soSucKhoeDienTuDataSt1.getSoCccd())
                                .ngay_sinh(soSucKhoeDienTuDataSt1.getNgaySinh())
                                .gioi_tinh(soSucKhoeDienTuDataSt1.getGioiTinh())
                                .nhom_mau(soSucKhoeDienTuDataSt1.getNhomMau())
                                .quoctich(quocTich)
                                .dantoc(danToc)
                                .nghe_nghiep(ngheNghiep)
                                .dia_chi(soSucKhoeDienTuDataSt1.getDiaChi())
                                .tinh_cutru(tinhCutru)
                                .huyen_cutru(huyenCuTru)
                                .xa_cu_tru(xaCutru)
                                .dien_thoai(soSucKhoeDienTuDataSt1.getDienThoai())
                                .ma_the_bhyt(soSucKhoeDienTuDataSt1.getMaTheBhyt())
                                .ma_dkbd(maDkbd)
                                .ly_do_vv(soSucKhoeDienTuDataSt1.getLyDoVv())
                                .chan_doan_rv(soSucKhoeDienTuDataSt1.getChuanDoanRv())
                                .benh_chinh(benhChinh)
                                .benh_kt(benhKt)
                                .benh_yhct(benhYhct)
                                .noi_di(noiDi)
                                .noi_den(noiDen)
                                .ngay_vao(soSucKhoeDienTuDataSt1.getNgayVao())
                                .ngay_ra(soSucKhoeDienTuDataSt1.getNgayRa())
                                .ket_qua_dtri(ketQuaDTri)
                                .loai_rv(loaiRv)
                                .ghi_chu(soSucKhoeDienTuDataSt1.getGhiChu())
                                .loai_kcb(loaiKcb)
                                .cskcb(cskcb)
                                .ngay_tai_kham(soSucKhoeDienTuDataSt1.getNgayTaiKham())
                                .namQt(soSucKhoeDienTuDataSt1.getNamQt())
                                .du_phong(soSucKhoeDienTuDataSt1.getDuPhong())
                                .thuoc(medicineDatas)
                                .dv_ky_thuat_vat_tu(dvKyThuatVatTuLists)
                                .dv_can_lam_sang(dvCanLamSangLists)
                                .tt_ho_so_benh_an(ttHoSoBenhAnList)
                                .build();

                        soSucKhoeDienTuData.add(soSucKhoeDienTuData1);
                    }
                }else {

                    BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                            .statusCode(0)
                            .errorDetail("Không thấy bệnh nhân được yêu cầu kiểm tra lại trường CCCC và năm quá trình khám bệnh")
                            .build();
                    return ResponseEntity.ok(baseResponseFail);
                }
            }catch (Exception exception){
                BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                        .statusCode(0)
                        .errorDetail(exception.getMessage())
                        .build();
                return ResponseEntity.ok(baseResponseFail);
            }

        }

        BaseReposeLookup<SoSucKhoeDienTuData> baseReposeLookup = BaseReposeLookup.<SoSucKhoeDienTuData>builder()
                .statusCode(1)
                .data(soSucKhoeDienTuData)
                .build();
        return ResponseEntity.ok(baseReposeLookup);

    }

    public ResponseEntity<?> danhsach(SoSucKhoeDienTuList rq) {
        if (Objects.equals(rq.getNamQt(), "") || Objects.equals(rq.getSoCCCD(), "")){
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống năm Qt và Số CCCD")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }

        Pageable pageable = PageRequest.of(rq.getPagination().getPage(), rq.getPagination().getPageSize(), Sort.by( Sort.Direction.fromString(rq.getSort().getSortOrder()) ,rq.getSort().getSortBy()));
        List<ListSoSucKhoe> listSoSucKhoess = new ArrayList<>();
        Cskcb cskcb = null;
        String maCskcb = rq.getFilters().getCskcb().trim().isEmpty() ? null : rq.getFilters().getCskcb().trim();
        String chanDoan = rq.getFilters().getChan_doan().trim().isEmpty() ? null : rq.getFilters().getChan_doan().trim();
        int offset = pageable.getPageNumber() * pageable.getPageSize();

        List<SoKhamChuaBenhListData> listSoSucKhoes = admissionMedicalRecordMapper.findGetListSoSucKhoeDienTu(rq.getNamQt(), rq.getSoCCCD(),maCskcb, chanDoan, pageable.getPageSize(),offset);
        PaginationRp paginationRp = null;
        long total = admissionMedicalRecordMapper.countSoKhamChuaBenh(
                rq.getNamQt(), rq.getSoCCCD(), maCskcb, chanDoan);
        // Tạo PageImpl
        Page<SoKhamChuaBenhListData> page = new PageImpl<>(listSoSucKhoes, pageable, total);
        if (!listSoSucKhoes.isEmpty()) {
            paginationRp = PaginationRp.builder()
                    .page(page.getNumber())
                    .pageSize(page.getSize())
                    .totalPages(page.getTotalPages())
                    .totalItems((int) page.getTotalElements())
                    .build();
            for (SoKhamChuaBenhListData listData : listSoSucKhoes){
                cskcb = Cskcb.builder()
                        .ma(listData.getMa_cskb())
                        .ten("test")
                        .build();


                ListSoSucKhoe listSoSucKhoe = ListSoSucKhoe.builder()
                        .ma_lk(listData.getMa_lk())
                        .ma_bn(listData.getMa_bn())
                        .ngay_vao(listData.getNgay_vao())
                        .ngay_ra(listData.getNgay_ra())
                        .chan_doan_rv(listData.getChan_doan_rv())
                        .cskcb(cskcb)
                        .build();
                listSoSucKhoess.add(listSoSucKhoe);
            }
        }else {
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không tìm được bản ghi phù hợp, vui lòng kiểm tra lại số CCCD và năm Qt")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }

        ResponseListSoSucKhoe<ListSoSucKhoe> responseListSoSucKhoe = ResponseListSoSucKhoe.<ListSoSucKhoe>builder()
                .statusCode(1)
                .pagination(paginationRp)
                .data(listSoSucKhoess)
                .build();

        return ResponseEntity.ok(responseListSoSucKhoe);
    }

    public ResponseEntity<?> chitiet(DetailSoSucKhoeDienTuRq rq) {

        DetailSoSucKhoeDienTuRp detailSoSucKhoeDienTuRp = null;
        DetailSoSucKhoeDienTu detailSoSucKhoeDienTu = null;
        Cskcb infoCskcb = null;
        TinhCutru tinhCutru = null;
        HuyenCuTru huyenCuTru = null;
        XaCutru xaCutru = null;
        NoiDen noiDen = null;
        NoiDi noiDi = null;
        List<DetailMedicineData> detailMedicineData = null;
        List<DetailMedicineDatas> detailMedicineDatas = new ArrayList<>();
        List<DetailDvKyThuatVatTu> detailDvKyThuatVatTu = null;
        List<DetailDvKyThuatVatTus> detailDvKyThuatVatTus = new ArrayList<>();
        List<DetailDvCanLamSang> detailDvCanLamSang = null;
        List<DetailDvCanLamSangs> detailDvCanLamSangs = new ArrayList<>();
        List<DetailTtHoSoBenhAn> detailTtHoSoBenhAn = null;

        QuocTich quocTich = null;
        DanToc danToc = null;
        NgheNghiep ngheNghiep = null;
        MaDkbd maDkbd = null;
        BenhChinh benhChinh = null;
        BenhKt benhKt = null;
        BenhYhct benhYhct = null;
        KetQuaDTri ketQuaDTri = null;
        LoaiRv loaiRv = null;
        LoaiKcb loaiKcb = null;
        MaNhomThuoc maNhomThuoc = null;
        DuongDungThuoc duongDungThuoc = null;
        DichVu dichVu = null;

        if (Objects.equals(rq.getNamQt(), "") || Objects.equals(rq.getSoCCCD(), "")|| Objects.equals(rq.getMaCSKCB(), "")|| Objects.equals(rq.getMa_lk(), "")){

            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống trường giá trị nào")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }else {
            detailSoSucKhoeDienTu = admissionMedicalRecordMapper.findDetailSoSucKhoeDienTuCustorm(rq.getNamQt(), rq.getSoCCCD(), rq.getMaCSKCB(), rq.getMa_lk());
            if (detailSoSucKhoeDienTu == null) {
                BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                        .statusCode(0)
                        .errorDetail("Không tìm thấy bản ghi phù hợp, vui lòng kiểm tra lại số CCCD, năm Qt, mã CSKCB và mã LK")
                        .build();
                return ResponseEntity.ok(baseResponseFail);
            }else {
                infoCskcb = Cskcb.builder()
                        .ma(rq.getMaCSKCB())
                        .ten("test")
                        .build();
                tinhCutru = TinhCutru.builder()
                        .ma(Long.valueOf(detailSoSucKhoeDienTu.getMaTinh()))
                        .ten("test")
                        .build();
                huyenCuTru = HuyenCuTru.builder()
                        .ma(Long.valueOf(detailSoSucKhoeDienTu.getMaHuyen()))
                        .ten("test")
                        .build();
                xaCutru = XaCutru.builder()
                        .ma(Long.valueOf(detailSoSucKhoeDienTu.getMaXa()))
                        .ten("test")
                        .build();
//                String tennoiDen = detailSoSucKhoeDienTu.getMaNoiDen() == null ? null :admissionMedicalRecordMapper.getNoiDen(detailSoSucKhoeDienTu.getMaNoiDen());
//                String tennoiDi = detailSoSucKhoeDienTu.getMaNoiDi() == null ? null :admissionMedicalRecordMapper.getNoiDi(detailSoSucKhoeDienTu.getMaNoiDi());

                noiDi = NoiDi.builder()
                        .ma(detailSoSucKhoeDienTu.getMaNoiDi())
                        .ten("test")
                        .build();

                noiDen = NoiDen.builder()
                        .ma(detailSoSucKhoeDienTu.getMaNoiDen())
                        .ten("test")
                        .build();

                System.out.println("check Data : " + rq.getNamQt() + "/" + detailSoSucKhoeDienTu.getSo_cccd() + "/" + infoCskcb.getMa() + "/" + rq.getMa_lk());

                detailMedicineData = admissionMedicalRecordMapper.getDetailMedicineData(rq.getNamQt(), detailSoSucKhoeDienTu.getSo_cccd(), infoCskcb.getMa(), rq.getMa_lk());
                detailDvKyThuatVatTu = admissionMedicalRecordMapper.getDetailDvKyThuatVatTu(rq.getNamQt(), detailSoSucKhoeDienTu.getSo_cccd(), infoCskcb.getMa(), rq.getMa_lk());
                detailDvCanLamSang = admissionMedicalRecordMapper.getDetailDvCanLamSang(rq.getNamQt(), detailSoSucKhoeDienTu.getSo_cccd(), infoCskcb.getMa(), rq.getMa_lk());
                detailTtHoSoBenhAn = admissionMedicalRecordMapper.getDetailTtHoSoBenhAn(rq.getNamQt(), detailSoSucKhoeDienTu.getSo_cccd(), infoCskcb.getMa(), rq.getMa_lk());

                for (DetailMedicineData detailMedicineData1 : detailMedicineData) {
                    maNhomThuoc = MaNhomThuoc.builder()
                            .ma(detailMedicineData1.getMa_nhom())
                            .ten(null)
                            .build();
                    duongDungThuoc = DuongDungThuoc.builder()
                            .ma(detailMedicineData1.getDuong_dung())
                            .ten(null)
                            .build();

                    DetailMedicineDatas detailMedicineDatass = DetailMedicineDatas.builder()
                            .stt(detailMedicineData1.getStt())
                            .ma_thuoc(detailMedicineData1.getMa_thuoc())
                            .ma_nhom(maNhomThuoc)
                            .ten_thuoc(detailMedicineData1.getTen_thuoc())
                            .don_vi_tinh(detailMedicineData1.getDon_vi_tinh())
                            .ham_luong(detailMedicineData1.getHam_luong())
                            .duong_dung(duongDungThuoc)
                            .lieu_dung(detailMedicineData1.getLieu_dung())
                            .cach_dung(detailMedicineData1.getCach_dung())
                            .so_luong(detailMedicineData1.getSo_luong())
                            .ngay_th_yl(detailMedicineData1.getNgay_th_yl())
                            .du_phong(detailMedicineData1.getDu_phong())
                            .build();
                    detailMedicineDatas.add(detailMedicineDatass);
                }

                for (DetailDvKyThuatVatTu detailDvKyThuatVatTu1 : detailDvKyThuatVatTu) {
                    MaNhomThuoc maNhomThuoc1 = MaNhomThuoc.builder()
                            .ma(detailDvKyThuatVatTu1.getMa_nhom())
                            .ten(null)
                            .build();

                    DetailDvKyThuatVatTus detailDvKyThuatVatTus1 = DetailDvKyThuatVatTus.builder()
                            .stt(detailDvKyThuatVatTu1.getStt())
                            .ma_dich_vu(detailDvKyThuatVatTu1.getMa_dich_vu())
                            .ma_nhom(maNhomThuoc1)
                            .ten_dich_vu(detailDvKyThuatVatTu1.getTen_dich_vu())
                            .ngay_yl(detailDvKyThuatVatTu1.getNgay_yl())
                            .du_phong(detailDvKyThuatVatTu1.getDu_phong())
                            .build();
                    detailDvKyThuatVatTus.add(detailDvKyThuatVatTus1);
                }

                for (DetailDvCanLamSang detailDvCanLamSang1 : detailDvCanLamSang) {
                    DichVu dichVu1 = DichVu.builder()
                            .ma(detailDvCanLamSang1.getDich_vu())
                            .ten(null)
                            .build();

                    DetailDvCanLamSangs detailDvCanLamSangs1 = DetailDvCanLamSangs.builder()
                            .stt(detailDvCanLamSang1.getStt())
                            .dich_vu(dichVu1)
                            .ma_chi_so(detailDvCanLamSang1.getMa_chi_so())
                            .ten_chi_so(detailDvCanLamSang1.getTen_chi_so())
                            .gia_tri(detailDvCanLamSang1.getGia_tri())
                            .don_vi_do(detailDvCanLamSang1.getDon_vi_do())
                            .mo_ta(detailDvCanLamSang1.getMo_ta())
                            .ket_luan(detailDvCanLamSang1.getKet_luan())
                            .ngay_kq(detailDvCanLamSang1.getNgay_kq())
                            .du_phong(detailDvCanLamSang1.getDu_phong())
                            .build();
                    detailDvCanLamSangs.add(detailDvCanLamSangs1);
                }


                quocTich = QuocTich.builder()
                        .ma(detailSoSucKhoeDienTu.getQuoctich())
                        .ten(null)
                        .build();
                danToc = DanToc.builder()
                        .ma(detailSoSucKhoeDienTu.getDantoc())
                        .ten(null)
                        .build();
                ngheNghiep = NgheNghiep.builder()
                        .ma(detailSoSucKhoeDienTu.getNghe_nghiep())
                        .ten(null)
                        .build();
                maDkbd = MaDkbd.builder()
                        .ma(detailSoSucKhoeDienTu.getMa_dkbd())
                        .ten(null)
                        .build();
                benhChinh = BenhChinh.builder()
                        .ma(detailSoSucKhoeDienTu.getBenh_chinh())
                        .ten(null)
                        .build();

                benhKt = BenhKt.builder()
                        .ma(detailSoSucKhoeDienTu.getBenh_kt())
                        .ten(null)
                        .build();
                benhYhct = BenhYhct.builder()
                        .ma(detailSoSucKhoeDienTu.getBenh_yhct())
                        .ten(null)
                        .build();

                ketQuaDTri = KetQuaDTri.builder()
                        .ma(detailSoSucKhoeDienTu.getKet_qua_dtri())
                        .ten(null)
                        .build();

                loaiRv = LoaiRv.builder()
                        .ma(detailSoSucKhoeDienTu.getLoai_rv())
                        .ten(null)
                        .build();
                loaiKcb = LoaiKcb.builder()
                        .ma(detailSoSucKhoeDienTu.getLoai_kcb())
                        .ten(null)
                        .build();


                detailSoSucKhoeDienTuRp = DetailSoSucKhoeDienTuRp.builder()
                        .stt(detailSoSucKhoeDienTu.getStt())
                        .ma_bn(detailSoSucKhoeDienTu.getMa_bn())
                        .ho_ten(detailSoSucKhoeDienTu.getHo_ten())
                        .so_cccd(detailSoSucKhoeDienTu.getSo_cccd())
                        .ngay_sinh(detailSoSucKhoeDienTu.getNgay_sinh())
                        .gioi_tinh(detailSoSucKhoeDienTu.getGioi_tinh())
                        .nhom_mau(detailSoSucKhoeDienTu.getNhom_mau())
                        .quoctich(quocTich)
                        .dantoc(danToc)
                        .nghe_nghiep(ngheNghiep)
                        .dia_chi(detailSoSucKhoeDienTu.getDia_chi())
                        .tinh_cutru(tinhCutru)
                        .huyen_cutru(huyenCuTru)
                        .xa_cu_tru(xaCutru)
                        .dien_thoai(detailSoSucKhoeDienTu.getDien_thoai())
                        .ma_the_bhyt(detailSoSucKhoeDienTu.getMa_the_bhyt())
                        .ma_dkbd(maDkbd)
                        .ly_do_vv(detailSoSucKhoeDienTu.getLy_do_vv())
                        .chan_doan_rv(detailSoSucKhoeDienTu.getChan_doan_rv())
                        .benh_chinh(benhChinh)
                        .benh_kt(benhKt)
                        .benh_yhct(benhYhct)
                        .noi_di(noiDi)
                        .noi_den(noiDen)
                        .ngay_vao(detailSoSucKhoeDienTu.getNgay_vao())
                        .ngay_ra(detailSoSucKhoeDienTu.getNgay_ra())
                        .ket_qua_dtri(ketQuaDTri)
                        .loai_rv(loaiRv)
                        .ghi_chu(detailSoSucKhoeDienTu.getGhi_chu())
                        .loai_kcb(loaiKcb)
                        .cskcb(infoCskcb)
                        .ngay_tai_kham(detailSoSucKhoeDienTu.getNgay_tai_kham())
                        .du_phong(detailSoSucKhoeDienTu.getDu_phong())
                        .thuoc(detailMedicineDatas)
                        .dv_ky_thuat_vat_tu(detailDvKyThuatVatTus)
                        .dv_can_lam_sang(detailDvCanLamSangs)
                        .tt_ho_so_benh_an(detailTtHoSoBenhAn)
                        .build();

            }
        }
        ResponseDetail responseDetail = ResponseDetail.builder()
                .statusCode(1)
                .data(detailSoSucKhoeDienTuRp)
                .build();

        return ResponseEntity.ok(responseDetail);
    }

    public ResponseEntity<?> giayhenkhamlai(AppointmentLetterRq rq) {

        List<AppointmentLetterDataList> appointmentLetterDataList = new ArrayList<>();
        Cskcb cskcb = null;

        List<AppointmentLetterData> appointmentLetterData = new ArrayList<>();
        if (Objects.equals(rq.getNamQt(),"") || Objects.equals(rq.getSoCCCD(), "")){
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống năm Qt và Số CCCD")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }else {
            appointmentLetterData = admissionMedicalRecordMapper.findAppointmentLetterCustorm(rq.getNamQt(), rq.getSoCCCD());
            if(!appointmentLetterData.isEmpty()){
                for (AppointmentLetterData data : appointmentLetterData){
                    System.out.println("Cskcb: " + data.getMa_cskcb());
                    cskcb = Cskcb.builder()
                            .ma(data.getMa_cskcb())
                            .ten(data.getMa_cskcb() == null ? null : "test")
                            .build();

                    BenhChinh benhChinh = BenhChinh.builder()
                            .ma(data.getBenh_chinh())
                            .ten(null)
                            .build();

                    BenhKt benhKt = BenhKt.builder()
                            .ma(data.getBenh_kt())
                            .ten(null)
                            .build();

                    BenhYhct benhYhct = BenhYhct.builder()
                            .ma(data.getBenh_yhct())
                            .ten(null)
                            .build();

                    DoiTuongKcb doituongKcb = DoiTuongKcb.builder()
                            .ma(data.getDoituong_kcb())
                            .ten(null)
                            .build();

                    BacSi bacSi = BacSi.builder()
                            .ma(data.getBac_si())
                            .ten(null)
                            .build();

                    TTDV ttdv = TTDV.builder()
                            .ma(data.getTtdv())
                            .ten(null)
                            .build();

                    AppointmentLetterDataList letterDataList = AppointmentLetterDataList.builder()
                            .ma_lk(data.getMa_lk())
                            .so_giayhen_kl(data.getSo_giayhen_kl())
                            .cskcb(cskcb)
                            .ho_ten(data.getHo_ten())
                            .ngay_sinh(data.getNgay_sinh())
                            .gioi_tinh(data.getGioi_tinh())
                            .dia_chi(data.getDia_chi())
                            .ma_the_bhyt(data.getMa_the_bhyt())
                            .gt_the_den(data.getGt_the_den())
                            .ngay_vao(data.getNgay_vao())
                            .ngay_vao_noi_tru(data.getNgay_vao_noi_tru())
                            .ngay_ra(data.getNgay_ra())
                            .ngay_hen_kl(data.getNgay_hen_kl())
                            .chan_doan_rv(data.getChan_doan_rv())
                            .benh_chinh(benhChinh)
                            .benh_kt(benhKt)
                            .benh_yhct(benhYhct)
                            .doituong_kcb(doituongKcb)
                            .bac_si(bacSi)
                            .ttdv(ttdv)
                            .ngay_ct(data.getNgay_ct())
                            .du_phong(data.getDu_phong())
                            .build();
                    appointmentLetterDataList.add(letterDataList);

                }
            }else {
                BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                        .statusCode(0)
                        .errorDetail("Không tìm thấy bản ghi phù hợp, vui lòng kiểm tra lại số CCCD và năm Qt")
                        .build();

                return ResponseEntity.ok(baseResponseFail);
            }
        }

        BaseReposeLookup<AppointmentLetterDataList> baseReposeLookup = BaseReposeLookup.<AppointmentLetterDataList>builder()
                .statusCode(1)
                .data(appointmentLetterDataList)
                .build();

        return ResponseEntity.ok(baseReposeLookup);

    }

    public ResponseEntity<?> danhsachGiayHen(AppointmentLetterListRq rq) {

        List<AppointmentLetterDataListDs> appointmentLetterDataLists = new ArrayList<>();
        List<AppointmentLetterDataDs> appointmentLetterDataPage =null;
        PaginationRp paginationRp = null;
        Cskcb cskcb =null;

        String soCccd = rq.getSoCCCD().trim();
        String namQt = rq.getNamQt().trim();
        String cskcbFilter = rq.getFilters().getCskcb().trim().isEmpty() ? null : rq.getFilters().getCskcb().trim();
        String chanDoan = rq.getFilters().getChan_doan().trim().isEmpty() ? null : rq.getFilters().getChan_doan().trim();
        String ngayHenTu = rq.getFilters().getNgay_hen_tu().isEmpty() ? null : rq.getFilters().getNgay_hen_tu();
        String ngayHenDen = rq.getFilters().getNgay_hen_den().isEmpty() ? null : rq.getFilters().getNgay_hen_den();
        String sortBy = rq.getSort().getSortBy().trim().isEmpty() ? "id" : rq.getSort().getSortBy().trim();
        String sortOrder = rq.getSort().getSortOrder().trim().isEmpty() ? "asc" : rq.getSort().getSortOrder().trim();
        Integer page = rq.getPagination().getPage() == null ? 0 : rq.getPagination().getPage();
        Integer pageSize = rq.getPagination().getPageSize() == null ? 10 : rq.getPagination().getPageSize();
        System.out.println("Payload : " + soCccd + "/" + namQt + "/" + cskcbFilter + "/" + chanDoan + "/" + ngayHenTu + "/"+ ngayHenDen + "/"
                + sortBy + "/" + sortOrder + "/" + page + "/" + pageSize + "/" );
        if (ngayHenDen == null && ngayHenTu != null){
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống ngày hẹn đến")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }

        if (Objects.equals(rq.getNamQt(), "") || Objects.equals(rq.getSoCCCD(), "")){
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống năm Qt và Số CCCD")
                    .build();

            return ResponseEntity.ok(baseResponseFail);
        }else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by( Sort.Direction.fromString(sortOrder) ,sortBy));
            int offset = pageable.getPageNumber() * pageable.getPageSize();
            appointmentLetterDataPage = admissionMedicalRecordMapper.findAppointmentLetterListCustorm(namQt, soCccd,
                    cskcbFilter,ngayHenTu, ngayHenDen, chanDoan, pageable.getPageSize(),offset);
            long total = admissionMedicalRecordMapper.countAppointmentLetter(
                    namQt, soCccd,
                    cskcbFilter,ngayHenTu, ngayHenDen, chanDoan);
            Page<AppointmentLetterDataDs> pageAppointmentLetter = new PageImpl<>(appointmentLetterDataPage, pageable, total);
            paginationRp = PaginationRp.builder()
                    .page(pageAppointmentLetter.getNumber())
                    .pageSize(pageAppointmentLetter.getSize())
                    .totalPages(pageAppointmentLetter.getTotalPages())
                    .totalItems((int) pageAppointmentLetter.getTotalElements())
                    .build();
            if(!appointmentLetterDataPage.isEmpty()){
                for (AppointmentLetterDataDs data : appointmentLetterDataPage){
                    cskcb = Cskcb.builder()
                            .ma(data.getMa_cskcb())
                            .ten(data.getMa_cskcb() == null ? null : "test")
                            .build();
                    AppointmentLetterDataListDs appointmentLetterDataListDs = AppointmentLetterDataListDs.builder()
                            .ma_lk(data.getMa_lk())
                            .so_giayhen_kl(data.getSo_giay_hen_kl())
                            .ho_ten(data.getHo_ten())
                            .ngay_sinh(data.getNgay_sinh())
                            .cskcb(cskcb)
                            .ngay_hen_kl(data.getNgay_hen_kl())
                            .chan_doan_rv(data.getChan_doan_rv())
                            .build();
                    appointmentLetterDataLists.add(appointmentLetterDataListDs);
                }
            }

        }

        ResponseListSoSucKhoe<AppointmentLetterDataListDs> responseListSoSucKhoe = ResponseListSoSucKhoe.<AppointmentLetterDataListDs>builder()
                .statusCode(1)
                .pagination(paginationRp)
                .data(appointmentLetterDataLists)
                .build();
        return ResponseEntity.ok(responseListSoSucKhoe);

    }

    public ResponseEntity<?> danhsachGiayHenDetail(DetailAppointmentLetterListRq rq) {

        DetailAppointmentLetterData detailAppointmentLetterData = null;
        DetailAppointmentLetter detailAppointmentLetter = null;
        String soCccd = rq.getSoCCCD().trim();
        String maLk = rq.getMa_lk().trim();
        String soGiayHen = rq.getSo_giayhen_kl().trim();
        Cskcb cskcb = null;

        if (Objects.equals(soCccd, "") || Objects.equals(maLk, "") || Objects.equals(soGiayHen, "")) {
            BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống năm Qt, Số CCCD và Số giấy hẹn khám lại")
                    .build();
            return ResponseEntity.ok(baseResponseFail);
        }else {
            detailAppointmentLetter = appointmentLetterMapper.findDetailAppointmentLetter(soCccd, maLk, soGiayHen);
            if (!(detailAppointmentLetter==null)){
                cskcb = Cskcb.builder()
                        .ma(detailAppointmentLetter.getMa_cskcb())
                        .ten("test")
                        .build();

                BenhChinh benhChinh = BenhChinh.builder()
                        .ma(detailAppointmentLetter.getBenh_chinh())
                        .ten(null)
                        .build();

                BenhKt benhKt = BenhKt.builder()
                        .ma(detailAppointmentLetter.getBenh_kt())
                        .ten(null)
                        .build();

                BenhYhct benhYhct = BenhYhct.builder()
                        .ma(detailAppointmentLetter.getBenh_yhct())
                        .ten(null)
                        .build();

                DoiTuongKcb doituongKcb = DoiTuongKcb.builder()
                        .ma(detailAppointmentLetter.getDoituong_kcb())
                        .ten(null)
                        .build();

                BacSi bacSi = BacSi.builder()
                        .ma(detailAppointmentLetter.getBac_si())
                        .ten(null)
                        .build();

                TTDV ttdv = TTDV.builder()
                        .ma(detailAppointmentLetter.getTtdv())
                        .ten(null)
                        .build();

                detailAppointmentLetterData = DetailAppointmentLetterData.builder()
                        .so_giayhen_kl(detailAppointmentLetter.getSo_giay_hen_kl())
                        .cskcb(cskcb)
                        .ho_ten(detailAppointmentLetter.getHo_ten())
                        .ngay_sinh(detailAppointmentLetter.getNgay_sinh())
                        .gioi_tinh(detailAppointmentLetter.getGioi_tinh())
                        .dia_chi(detailAppointmentLetter.getDia_chi())
                        .ma_the_bhyt(detailAppointmentLetter.getMa_the_bhyt())
                        .gt_the_den(detailAppointmentLetter.getGt_the_den())
                        .ngay_vao(detailAppointmentLetter.getNgay_vao())
                        .ngay_vao_noi_tru(detailAppointmentLetter.getNgay_vao_noi_tru())
                        .ngay_ra(detailAppointmentLetter.getNgay_ra())
                        .ngay_hen_kl(detailAppointmentLetter.getNgay_hen_kl())
                        .chan_doan_rv(detailAppointmentLetter.getChan_doan_rv())
                        .benh_chinh(benhChinh)
                        .benh_kt(benhKt)
                        .benh_yhct(benhYhct)
                        .doituong_kcb(doituongKcb)
                        .bac_si(bacSi)
                        .ttdv(ttdv)
                        .ngay_ct(detailAppointmentLetter.getNgay_ct())
                        .du_phong(detailAppointmentLetter.getDu_phong())
                        .build();

            }else {
                BaseResponseFail baseResponseFail = BaseResponseFail.builder()
                        .statusCode(0)
                        .errorDetail("Không tìm thấy bản ghi phù hợp, vui lòng kiểm tra lại số CCCD, mã LK và số giấy hẹn khám lại")
                        .build();
                return ResponseEntity.ok(baseResponseFail);
            }

        }
        BaseResponseNoList<DetailAppointmentLetterData> baseResponseNoList = BaseResponseNoList.<DetailAppointmentLetterData>builder()
                .statusCode(1)
                .data(detailAppointmentLetterData)
                .build();

        return ResponseEntity.ok(baseResponseNoList);
    }

    public ResponseEntity<?> giaychuyentuyen(TransferPaperRq rq) {

        String namQt = rq.getNamQt().trim();
        String soCccd = rq.getSoCCCD().trim();

        List<TransferPaperDatas> transferPaperDatasList = new ArrayList<>();
        List<TransferPaperData> transferPaperData = new ArrayList<>();
        Cskcb cskcb = null;
        NoiDi noiDi = null;
        NoiDen noiDen = null;
        if (Objects.equals(namQt, "") || Objects.equals(soCccd, "")){
            BaseResponseFail  baseResponseFail = BaseResponseFail.builder()
                    .statusCode(0)
                    .errorDetail("Không được để trống" +
                            " giá trị Nam Qt hoặc So CCCD")
                    .build();
        }else {
            transferPaperData = appointmentLetterMapper.findTransferPaperDatas(namQt, soCccd);
            if(!transferPaperData.isEmpty()){
                for (TransferPaperData data : transferPaperData){
                    cskcb = Cskcb.builder()
                            .ma(data.getCskcb())
                            .ten(data.getCskcb() == null ? null : "test")
                            .build();
                    System.out.println(data.getNoi_di());
                    String tenNoiDi = data.getNoi_di() == null ? null : "test";
                    String tenNoiDen = data.getNoi_den() == null ? null : "test";
                    noiDi =  NoiDi.builder()
                            .ma(data.getNoi_di())
                            .ten(tenNoiDi)
                            .build();
                    noiDen = NoiDen.builder()
                            .ma(data.getNoi_den())
                            .ten(tenNoiDen)
                            .build();

                    QuocTich quocTich = QuocTich.builder()
                            .ma(data.getQuoctich())
                            .ten(null)
                            .build();

                    DanToc danToc = DanToc.builder()
                            .ma(data.getDantoc())
                            .ten(null)
                            .build();

                    NgheNghiep ngheNghiep = NgheNghiep.builder()
                            .ma(data.getNghe_nghiep())
                            .ten(null)
                            .build();

                    BenhChinh benhChinh = BenhChinh.builder()
                            .ma(data.getBenh_chinh())
                            .ten(null)
                            .build();

                    BenhKt benhKt = BenhKt.builder()
                            .ma(data.getBenh_kt())
                            .ten(null)
                            .build();

                    BenhYhct benhYhct = BenhYhct.builder()
                            .ma(data.getBenh_yhct())
                            .ten(null)
                            .build();

                    LoaiRv loaiRv = LoaiRv.builder()
                            .ma(data.getLoai_rv())
                            .ten(null)
                            .build();

                    BacSi bacSi = BacSi.builder()
                            .ma(data.getBac_si())
                            .ten(null)
                            .build();

                    TTDV ttdv = TTDV.builder()
                            .ma(data.getTtdv())
                            .ten(null)
                            .build();

                    TransferPaperDatas transferPaperDatas =  TransferPaperDatas.builder()
                            .ma_lk(data.getMa_lk())
                            .so_hoso(data.getSo_hoso())
                            .so_chuyentuyen(data.getSo_chuyentuyen())
                            .giay_chuyen_tuyen(data.getGiay_chuyen_tuyen())
                            .cskcb(cskcb)
                            .noi_di(noiDi)
                            .noi_den(noiDen)
                            .ho_ten(data.getHo_ten())
                            .ngay_sinh(data.getNgay_sinh())
                            .gioi_tinh(data.getGioi_tinh())
                            .quoctich(quocTich)
                            .dantoc(danToc)
                            .nghe_nghiep(ngheNghiep)
                            .dia_chi(data.getDia_chi())
                            .ma_the_bhyt(data.getMa_the_bhyt())
                            .gt_the_den(data.getGt_the_den())
                            .ngay_vao(data.getNgay_vao())
                            .ngay_vao_noi_tru(data.getNgay_vao_noi_tru())
                            .ngay_ra(data.getNgay_ra())
                            .dau_hieu_ls(data.getDau_hieu_ls())
                            .chan_doan_rv(data.getChan_doan_rv())
                            .qt_benhly(data.getQt_benhly())
                            .tomtat_kq(data.getTomtat_kq())
                            .pp_dieutri(data.getPp_dieutri())
                            .benh_chinh(benhChinh)
                            .benh_kt(benhKt)
                            .benh_yhct(benhYhct)
                            .loai_rv(loaiRv)
                            .ma_lydo_ct(data.getMa_lydo_ct())
                            .huong_dieu_tri(data.getHuong_dieu_tri())
                            .phuongtien_vc(data.getPhuongtien_vc())
                            .hoten_nguoi_ht(data.getHoten_nguoi_ht())
                            .chucdanh_nguoi_ht(data.getChucdanh_nguoi_ht())
                            .bac_si(bacSi)
                            .ttdv(ttdv)
                            .du_phong(data.getDu_phong())
                            .build();

                    transferPaperDatasList.add(transferPaperDatas);

                }

            }


        }

        BaseReposeLookup<TransferPaperDatas> transferPaperDatasBaseReposeLookup = BaseReposeLookup.<TransferPaperDatas>builder()
                .statusCode(1)
                .data(transferPaperDatasList)
                .build();


        return ResponseEntity.ok(transferPaperDatasBaseReposeLookup);

    }
}
