package com.minio.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:10 2022/10/9
 * @ Modified By：
 */
public interface SysFileService {

    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;
}
