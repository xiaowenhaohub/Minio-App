package com.minio.file.service.impl;

import com.minio.common.exception.ServiceException;
import com.minio.file.config.MinioConfig;
import com.minio.file.constant.DataTypeConstant;
import com.minio.file.domain.SysFile;
import com.minio.file.domain.SysFileInfo;
import com.minio.file.domain.vo.SysFileInfoVO;
import com.minio.file.mapper.SysFileInfoMapper;
import com.minio.file.service.SysFileService;
import com.minio.file.utils.FileTypeUtils;
import com.minio.file.utils.FileUploadUtils;
import com.minio.file.utils.MinioUtils;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 21:10 2022/10/9
 * @ Modified By：
 */
@Service
public class MinioSysFileServiceImpl implements SysFileService {

    private static final Logger log = LoggerFactory.getLogger(MinioSysFileServiceImpl.class);

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional
    public SysFileInfoVO uploadFile(MultipartFile file, Long parentDirId)  {

        String fileName = file.getOriginalFilename();

        // 存储文件信息到数据库
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(parentDirId);
        if (Objects.isNull(sysFileInfo)) {
            sysFileInfo = new SysFileInfo();
            sysFileInfo.setPath(fileName);

        }else {
            sysFileInfo.setPath(sysFileInfo.getPath()+fileName);
        }
        // 检查文件是否存在
        if (!Objects.isNull(sysFileInfoMapper.selectSysFileInfoByPath(sysFileInfo.getPath()))) {
            throw new ServiceException("文件已存在");
        }

        sysFileInfo.setFileName(fileName);
        sysFileInfo.setExt(FileTypeUtils.getExtension(file));
        sysFileInfo.setDataType(DataTypeConstant.FILE);
        sysFileInfo.setParentDirId(parentDirId);
        sysFileInfo.setSize(FileUploadUtils.getFileSize(file.getSize(), "M"));
        int i = sysFileInfoMapper.insertSysFileInfo(sysFileInfo);

        PutObjectArgs args = MinioUtils.getArgs(minioConfig.getBucketName(), sysFileInfo.getPath(), file);
        minioClient.putObject(args);

//        try {
//            // 上传到Minio
//            PutObjectArgs args = PutObjectArgs.builder()
//                    .bucket(minioConfig.getBucketName())
//                    .object(sysFileInfo.getPath())
//                    .stream(file.getInputStream(), file.getSize(), -1)
//                    .contentType(file.getContentType())
//                    .build();
//            minioClient.putObject(args);
//        }catch (Exception e) {
//            throw new RuntimeException("上传mino出现错误:" + e.getMessage());
//        }
        SysFileInfoVO sysFileInfoVO = mapperFacade.map(sysFileInfo, SysFileInfoVO.class);
//        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
        log.info("sysFileInfoVO: {}",sysFileInfoVO);
        return sysFileInfoVO;
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
