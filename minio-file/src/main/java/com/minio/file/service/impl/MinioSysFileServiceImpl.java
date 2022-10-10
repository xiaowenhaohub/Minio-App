package com.minio.file.service.impl;

import com.minio.file.config.MinioConfig;
import com.minio.file.domain.SysFile;
import com.minio.file.service.SysFileService;
import com.minio.file.utils.FileUploadUtils;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:10 2022/10/9
 * @ Modified By：
 */
@Service
public class MinioSysFileServiceImpl implements SysFileService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
//        String fileName = FileUploadUtils.extractFilename(file);
        String fileName = file.getOriginalFilename();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    @Override
    public List<SysFile> listObjects() {
        List<SysFile> fileList = new ArrayList<>();
        Iterable<Result<Item>> listObjects = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(minioConfig.getBucketName())
                .build());
        for (Result<Item> result : listObjects) {
            Item item = null;
            try {
                item = result.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            fileList.add(new SysFile(item.objectName(), null));
        }

        return fileList;
    }
}
