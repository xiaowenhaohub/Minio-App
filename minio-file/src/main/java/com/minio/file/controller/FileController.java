package com.minio.file.controller;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.minio.common.annotation.Log;
import com.minio.common.core.AjaxResult;
import com.minio.file.domain.SysFile;
import com.minio.file.domain.vo.SysDirInfoVO;
import com.minio.file.domain.vo.SysFileInfoVO;
import com.minio.file.service.SysFileService;
import com.minio.file.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @ApiOperation("查询文件列表")
    @Log(title = "查询文件列表")
    public AjaxResult getFileList(@PathVariable Long dirId) {
        SysDirInfoVO sysFileInfoVO = sysFileService.queryDirInfo(dirId);
        List<SysFileInfoVO> fileList = sysFileService.querySysFileList(dirId);
        JSONObject object = new JSONObject();
        object.put("dirInfo",sysFileInfoVO);
        object.put("FileList",fileList);
        return AjaxResult.success(object);
    }

    @GetMapping("/create/{parentDirId}/{dirName}")
    @ApiOperation("创建文件夹")
    @Log(title = "创建文件夹")
    public AjaxResult createDir(@PathVariable Long parentDirId, @PathVariable String dirName) {
        return AjaxResult.success(sysFileService.createDir(parentDirId, dirName));
    }
}
