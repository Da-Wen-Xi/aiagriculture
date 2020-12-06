package com.ynu.aiagri.service.kwgra.result;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResultEnum {

    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误");

    private Boolean success;

    private Integer code;

    private String message;

    ResultEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
