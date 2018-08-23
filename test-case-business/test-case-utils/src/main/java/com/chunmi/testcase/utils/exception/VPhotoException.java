package com.chunmi.testcase.utils.exception;

import org.apache.commons.lang3.StringUtils;

public class VPhotoException extends RuntimeException {
    public final ResultCodeEnum r;
    private String data;

    public VPhotoException(ResultCodeEnum r) {
        this(r.name(), r);
    }

    public VPhotoException(ResultCodeEnum r, String data) {
        this(r.name(), r);
        this.data = data;
    }

    public VPhotoException(String msg, ResultCodeEnum r) {
        super(msg);
        this.r = r;
    }

    public VPhotoException(Throwable ex, ResultCodeEnum r) {
        this(ex, r.defaultMsg, r);
    }

    public VPhotoException(Throwable ex, String msg, ResultCodeEnum r) {
        super(msg, ex);
        this.r = r;
    }

    public String getErrorText() {
        return StringUtils.isBlank(data) ? r.name() : data;
    }


//    @Override
//    public Throwable fillInStackTrace() {
//        return this;
//    }
}
