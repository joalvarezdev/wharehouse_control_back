package com.joalvarez.wharehousecontrol.general.data.dto;

import com.joalvarez.wharehousecontrol.general.data.type.ResponseCode;

import java.util.List;

public class ResponseDTO implements BaseDTO{

    private int code;
    private String message;
    private List<String> details;

    public ResponseDTO() {}

    public ResponseDTO(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
