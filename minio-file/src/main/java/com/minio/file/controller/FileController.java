package com.minio.file.controller;

import com.minio.common.annotation.Log;
import com.minio.common.core.AjaxResult;
import com.minio.file.domain.SysFile;
import com.minio.file.service.SysFileService;
import com.minio.file.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 20:38 2022/10/9
 * @ Modified By：
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private SysFileService sysFileService;

    @PostMapping("/upload")
    @Log(title = "文件上传")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return AjaxResult.success(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/list")
    @Log(title = "查询文件列表")
    public AjaxResult getFileList() {
        return AjaxResult.success(sysFileService.listObjects());
    }
}
