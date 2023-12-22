package com.example.template.common.data;

import com.example.template.common.constants.HttpResultConstants;
import lombok.Data;

/**
 * @author created by sunjy on 12/7/23
 */
@Data
public class HttpResult {

    private int code;
    private String message;
    private Object data;

    public HttpResult() {

    }

    public HttpResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public <T> HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static HttpResult success() {
        return new HttpResult(HttpResultConstants.SUCCESS, "success");
    }

    public static <T> HttpResult success(T data) {
        return new HttpResult(HttpResultConstants.SUCCESS, "success", data);
    }

    public static HttpResult error(String message) {
        return new HttpResult(HttpResultConstants.ERROR, message);
    }
}
