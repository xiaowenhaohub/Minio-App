package com.minio.framework.web.service.impl;


import com.minio.framework.web.model.OperationLog;
import com.minio.framework.web.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:00 2022/8/25
 * @ Modified By：
 */
@Service
public class LogServiceImpl implements LogService {
    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);
    @Override
    public void printLog(OperationLog operationLog) {
      log.info("操作日志:{}",operationLog.toString());
    }
}
