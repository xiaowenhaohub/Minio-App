package com.minio.file.controller;

import com.minio.common.core.AjaxResult;
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

    @PostMapping("/upload")
    public AjaxResult fileUpload(MultipartFile file) {
        return AjaxResult.success();
    }
}
