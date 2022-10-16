package com.minio.file.controller;

import com.minio.common.annotation.Log;
import com.minio.common.core.AjaxResult;
import com.minio.file.domain.SysFile;
import com.minio.file.service.SysFileService;
import com.minio.file.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 20:38 2022/10/9
 * @ Modified By：
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件操作")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private SysFileService sysFileService;

    @PostMapping("/upload/{parentDirId}")
    @ApiOperation("文件上传")
    @Log(title = "文件上传")
    public AjaxResult uploadFile(MultipartFile file, @PathVariable Long parentDirId) {
        return AjaxResult.success(sysFileService.uploadFile(file, parentDirId));

    }

    @GetMapping("/list/{dirId}")
    @Log(title = "查询文件列表")
    public AjaxResult getFileList(@PathVariable Long dirId) {
        return AjaxResult.success(sysFileService.querySysFileList(dirId));
    }
}
