package com.minio.common.exception;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 1:44 2022/8/21
 * @ Modified By：
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
