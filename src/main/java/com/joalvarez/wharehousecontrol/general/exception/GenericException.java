package com.joalvarez.wharehousecontrol.general.exception;

import com.joalvarez.wharehousecontrol.general.data.type.ResponseCode;
import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException{

    private HttpStatus httpStatus;
    private ResponseCode responseCode;

    public GenericException(final HttpStatus httpStatus, final ResponseCode responseCode, final String detailMessage) {
        super(detailMessage);
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
