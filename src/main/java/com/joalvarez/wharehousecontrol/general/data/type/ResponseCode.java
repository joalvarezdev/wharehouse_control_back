package com.joalvarez.wharehousecontrol.general.data.type;

public enum ResponseCode {


    INCORRECT_DATA(10000, "Uno o varios datos son incorrectos"),
    PRODUCT_ALREADY_EXISTS(10100, "El producto ya existe"),
    PRODUCT_DONT_EXISTS(10101, "El producto que desea eliminar no existe")
    ;
    private final int code;
    private final String message;

    ResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
