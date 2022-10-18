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
import org.apache.http.util.EncodingUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        return AjaxResult.success("上传成功", sysFileService.uploadFile(file, parentDirId));
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
        return AjaxResult.success("查询成功", object);
    }

    @GetMapping("/create/{parentDirId}/{dirName}")
    @ApiOperation("创建文件夹")
    @Log(title = "创建文件夹")
    public AjaxResult createDir(@PathVariable Long parentDirId, @PathVariable String dirName) {
        return AjaxResult.success("创建成功", sysFileService.createDir(parentDirId, dirName));
    }

    @GetMapping("/query/{fileId}")
    @ApiOperation("查询文件详情")
    @Log(title = "查询文件详情")
    public AjaxResult queryFile(@PathVariable Long fileId) {
        return AjaxResult.success("查询成功", sysFileService.querySysFileInfoById(fileId));
    }

    @DeleteMapping("/delete/{fileId}")
    @ApiOperation("删除文件")
    @Log(title = "删除文件")
    public AjaxResult deleteFile(@PathVariable Long fileId) {
        return AjaxResult.success("删除成功", sysFileService.deleteSysFileInfo(fileId));
    }

    @GetMapping("/download/{fileId}")
    @ApiOperation("删除文件")
    @Log(title = "删除文件")
    public void download(@PathVariable Long fileId, HttpServletRequest request, HttpServletResponse response) {
        SysFileInfoVO sysFileInfoVO = sysFileService.querySysFileInfoById(fileId);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = sysFileService.getFileInputStream(fileId);
            response.setHeader("Content-Disposition", "attachment;filename=" + sysFileInfoVO.getFileName());
            // 获取输出流
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败:" + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
