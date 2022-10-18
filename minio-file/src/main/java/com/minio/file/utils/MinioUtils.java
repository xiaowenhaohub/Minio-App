package com.minio.file.utils;

import com.minio.common.exception.ServiceException;
import io.minio.*;
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

    public static InputStream getObject(MinioClient minioClient, String bucketName, String filePath) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(filePath).build();
        GetObjectResponse object = null;
        try {
            object = minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            throw new ServiceException("获取文件输入流错误:" + e.getMessage());
        }
        return object;
    }


    /**
     * 删除文件
     * @param minioClient
     * @param bucketName
     * @param filePath
     */
    public static void deleteObject(MinioClient minioClient, String bucketName, String filePath) {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(bucketName).object(filePath).build();
        try {
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new ServiceException("Mino删除文件错误:" + e.getMessage());
        }
    }


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
