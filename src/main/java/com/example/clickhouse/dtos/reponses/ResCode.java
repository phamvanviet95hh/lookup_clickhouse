package com.example.clickhouse.dtos.reponses;

public class ResCode {
    public class Auth {

        public static final String AUTH_01 = "AUTH_01";     // THÔNG TIN XÁC THỰC KHÔNG HỢP LỆ
        public static final String AUTH_02 = "AUTH_02";     // EMAIL CHƯA XÁC THỰC
        public static final String AUTH_03 = "AUTH_03";     // User không có quyền truy cập tài nguyên
    }

    public static final String ERROR_00 = "ERROR_00"; // Thành công
    public static final String ERROR_01 = "ERROR_01"; // Input không hợp lệ
    public static final String ERROR_02 = "ERROR_02"; // Dữ liệu đã tồn tại
    public static final String ERROR_03 = "ERROR_03"; // Dữ liệu không tồn tại trên hệ thống.
    public static final String ERROR_99 = "ERROR_99"; // Lỗi không xác định
    public static final String ERROR_9999 = "ERROR_9999"; // Timeout

    // Các lỗi đặc biệt liên quan đến HTTP Status code
    public static final String HTTP_400 = "HTTP_400"; // Dữ liệu không hợp lệ
    public static final String HTTP_401 = "HTTP_401"; // Mã xác thực không hợp lệ
    public static final String HTTP_403 = "HTTP_403"; // Không đủ quyền truy cập

    public static class User {
        public static final String USER_01 = "USER_01"; // Người dùng không tồn tại
    }

    public class Backoffice{
        public static final String BO_FACILITY_01 = "BO_PARTNER_01"; // Người dùng không tồn tại
        public static final String BO_PARTNER_01 = "BO_PARTNER_01"; // Người dùng không tồn tại
        public static final String BO_PARTNER_02 = "BO_PARTNER_02"; // Username đã tồn tại
        public static final String BO_PARTNER_03 = "BO_PARTNER_03"; // Email đã tồn tại
        public static final String BO_PARTNER_04 = "BO_PARTNER_04"; // sdt đã tồn tại
        public static final String BO_PARTNER_05 = "BO_PARTNER_05"; // Người dùng không phải là agent
        public static final String BO_PARTNER_06 = "BO_PARTNER_06"; // Mã đối tác đã tồn tại
        public static final String BO_PARTNER_07 = "BO_PARTNER_07"; // Mã đối tác không tồn tại

        public static final String BO_CONFIG_01 = "BO_CONFIG_01"; // cấu hình không tồn tại
        public static final String BO_CONFIG_02 = "BO_CONFIG_02"; // cấu hình không được thay đổi
        public static final String BO_CONFIG_03 = "BO_CONFIG_03"; // Xảy ra lỗi khi đăng nhập lại hệ thống RAR
        public static final String BO_CONFIG_04 = "BO_CONFIG_04"; // Giá trị của cấu hình không hợp lệ

        public static final String BO_PARTNER_QUOTA_01 = "BO_PARTNER_QUOTA_01"; // Chỉ thêm được định mức Đối tác trả trước
        public static final String BO_PARTNER_QUOTA_02 = "BO_PARTNER_QUOTA_02"; // Định mức không tồn tại
        public static final String BO_PARTNER_QUOTA_03 = "BO_PARTNER_QUOTA_03"; // Chỉ huỷ được nếu trạng thái khác "Huỷ" và "Hết hạn"
        public static final String BO_PARTNER_QUOTA_04 = "BO_PARTNER_QUOTA_04"; // Chỉ Đối tác trả trước mới có danh sách định mức
        public static final String BO_PARTNER_QUOTA_05 = "BO_PARTNER_QUOTA_05"; // Đối tác đã dùng hết định mức
        public static final String BO_PARTNER_QUOTA_06 = "BO_PARTNER_QUOTA_06"; // Hạng khách hàng không được để trống
        public static final String BO_PARTNER_QUOTA_07 = "BO_PARTNER_QUOTA_07"; // Mã cấu hình đối tác không tồn tại

        public static final String BO_REPORT_01 = "BO_REPORT_01"; // Xảy ra lỗi khi xuất báo cáo

        public static final String BO_EXPORT_01 = "BO_EXPORT_01"; // Export service khong tồn tai
        public static final String BO_EXPORT_02 = "BO_EXPORT_02"; // Chỉ co the update khi trang thai la pending
        public static final String BO_EXPORT_03 = "BO_EXPORT_03"; // Khong the xoa khi trang thai la processing

    }
    public static class Partner {
        public static final String PARTNER_01 = "PARTNER_01"; // Người dùng không tồn tại
        public static final String PARTNER_SUB_01 = "PARTNER_SUB_01"; // Đối tác thành viên không tồn tại
        public static final String PARTNER_SUB_02 = "PARTNER_SUB_02"; // Mã đối tác thành viên đã tồn tại
        public static final String PARTNER_SUB_03 = "PARTNER_SUB_03"; // Đối tác thành viên không được phép sửa đổi
        public static final String PARTNER_SUB_04 = "PARTNER_SUB_04"; // Mã đối tác không tồn tại
        public static final String PARTNER_QUOTA_01 = "PARTNER_QUOTA_01"; // Đối tác đã dùng hết định mức
    }

    public static class SDK {
        public static final String SDK_01 = "SDK_01"; // x-api-key không được để trống
        public static final String SDK_02 = "SDK_02"; // SDK không tồn tại
        public static final String SDK_03 = "SDK_03"; // Partner không được phép verify
        public static final String SDK_04 = "SDK_04"; // Xảy ra lỗi khi verify
        public static final String SDK_05 = "SDK_05"; // Xảy ra lỗi khi xác thực khuôn mặt
        public static final String SDK_06 = "SDK_06"; // Service type khong hợp lệ
        public static final String SDK_07 = "SDK_07"; // Không hỗ trợ thông tin giấy tờ này
    }

    public static class Notify {

    }
}
