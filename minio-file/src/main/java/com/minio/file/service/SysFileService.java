package com.minio.file.service;

import com.minio.file.domain.SysFile;
import com.minio.file.domain.SysFileInfo;
import com.minio.file.domain.vo.SysFileInfoVO;
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
    SysFileInfoVO uploadFile(MultipartFile file, Long parentDirId);

    /**
     * 查询文件夹文件列表
     * @return
     */
    List<SysFileInfoVO> querySysFileList(Long dirId);
}
