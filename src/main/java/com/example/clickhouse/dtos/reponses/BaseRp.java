package com.example.clickhouse.dtos.reponses;

import lombok.*;

import java.io.Serializable;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseRp<T> implements Serializable {

    boolean success;
    ErrorRp error;
    T data;

    /* ===== Dành cho phân trang ===== */
    Long totalElements;
    Integer totalPages;
    Integer pageNumber;

    /**
     * Dùng cho trường hợp success
     *
     * @param errorCode
     */
    public BaseRp(String errorCode) {
        this.setSuccess(errorCode.endsWith("_00"));
        error = ErrorRp.builder().code(errorCode).build();
//		error.setMessage(cmService.getMessage(Enums.Locale.VIETNAMESE, errorCode));
        this.setError(error);
    }

    public BaseRp(String errorCode, String errorMsg) {
        this.setSuccess(errorCode.endsWith("_00"));
        error = ErrorRp.builder().code(errorCode).build();
        error.setMessage(errorMsg);
        this.setError(error);
    }

    public BaseRp(T data) {
        this.success = true;
        this.data = (T) data;
    }

}
