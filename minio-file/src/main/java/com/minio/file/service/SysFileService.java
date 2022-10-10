package com.minio.file.service;

import com.minio.file.domain.SysFile;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 查询桶下对象
     * @return
     */
    List<SysFile> listObjects();
}
