package com.minio.file.service;

import com.minio.file.domain.SysFile;
import com.minio.file.domain.SysFileInfo;
import com.minio.file.domain.vo.SysDirInfoVO;
import com.minio.file.domain.vo.SysFileInfoVO;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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

    /**
     * 查询文件夹详情
     * @param dirId
     * @return
     */
    SysDirInfoVO queryDirInfo(Long dirId);

    /**
     * 创建文件夹
     * @param dirName
     * @return
     */
    SysDirInfoVO createDir(Long parentDirId, String dirName);

    /**
     * 查询文件详情
     * @param fileId
     * @return
     */
    SysFileInfoVO querySysFileInfoById(Long fileId);

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    SysFileInfoVO deleteSysFileInfo(Long fileId);

    /**
     * 获取文件输入流
     * @param fileId
     * @return
     */
    InputStream getFileInputStream(Long fileId);
}
