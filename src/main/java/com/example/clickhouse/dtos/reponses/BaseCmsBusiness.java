package com.example.clickhouse.dtos.reponses;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BaseCmsBusiness {
    protected BaseRp<?> makeSingleResponseData(Object data) {
        BaseRp<Object> rp = new BaseRp<>();
        rp.setSuccess(true);
        rp.setData(data);
        var error = ErrorRp.builder().code(ResCode.ERROR_00).build();
//		error.setMessage(cmService.getMessage(Enums.Locale.VIETNAMESE, ResCode.ERROR_00));
        rp.setError(error);
        return rp;
    }

    protected BaseRp<?> makePagingResponseData(Page<?> page) {
        BaseRp<Object> rp = new BaseRp<>();
        rp.setSuccess(true);
        rp.setData(page.getContent());
        rp.setPageNumber(page.getPageable().getPageNumber() + 1);
        rp.setTotalElements(page.getTotalElements());
        rp.setTotalPages(page.getTotalPages());
        var error = ErrorRp.builder().code(ResCode.ERROR_00).build();
//		error.setMessage(cmService.getMessage(Enums.Locale.VIETNAMESE, ResCode.ERROR_00));
        rp.setError(error);

        return rp;
    }

    protected BaseRp<?> makeResponseWithoutData() {
        var rp = new BaseRp<>();
        rp.setSuccess(true);
        var error = ErrorRp.builder().code(ResCode.ERROR_00).build();
//		error.setMessage(cmService.getMessage(Enums.Locale.VIETNAMESE, ResCode.ERROR_00));
        rp.setError(error);
        return rp;
    }
}
