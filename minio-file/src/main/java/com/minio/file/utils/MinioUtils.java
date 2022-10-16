package com.minio.file.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 小问号
 * @date 2022/10/16 18
 * description
 */

public class MinioUtils {


    /**
     * 上传文件
     * @param minioClient
     * @param bucketName
     * @param filePath
     * @param file
     */
    public static void putObject(MinioClient minioClient, String bucketName, String filePath, MultipartFile file) {
        PutObjectArgs args = getArgs(bucketName, filePath, file);
        try {
            minioClient.putObject(args);
        } catch (Exception e) {
            throw new RuntimeException("Minio文件上传失败");
        }
    }


    /**
     * 构建Minio上传参数
     * @param bucketName
     * @param filePath
     * @param file
     * @return
     */
    public static PutObjectArgs getArgs(String bucketName, String filePath, MultipartFile file) {
        try {
            return getArgs(bucketName, filePath, file.getInputStream(), file.getSize(), file.getContentType());
        } catch (IOException e) {
            throw new RuntimeException("获取输入流错误");
        }
    }


    /**
     * 构建Minio上传参数
     * @param bucketName
     * @param filePath
     * @param inputStream
     * @param fileSize
     * @param contentType
     * @return
     */
    private static PutObjectArgs getArgs(String bucketName, String filePath, InputStream inputStream, Long fileSize, String contentType) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath)
                .stream(inputStream, fileSize, -1)
                .contentType(contentType)
                .build();
        return args;
    }
}
