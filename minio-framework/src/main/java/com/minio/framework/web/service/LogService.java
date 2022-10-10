package com.minio.framework.web.service;


import com.minio.framework.web.model.OperationLog;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:57 2022/8/25
 * @ Modified By：
 */
public interface LogService {

    /**
     * 打印日志
     */
    void printLog(OperationLog operationLog);
}
