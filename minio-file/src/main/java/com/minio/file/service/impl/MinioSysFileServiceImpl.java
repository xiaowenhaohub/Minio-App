package com.minio.file.service.impl;

import com.minio.common.exception.ServiceException;
import com.minio.common.utils.SnowFlakeUtils;
import com.minio.file.config.MinioConfig;
import com.minio.file.constant.DataTypeConstant;
import com.minio.file.domain.SysFile;
import com.minio.file.domain.SysFileInfo;
import com.minio.file.domain.vo.SysDirInfoVO;
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
import java.io.InputStream;
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

        if(Objects.isNull(sysFileInfo) || sysFileInfo.getDataType() != 1) {
            throw new ServiceException("文件夹不存在");
        }

        sysFileInfo.setPath(sysFileInfo.getPath() + "/" + fileName);

        // 检查文件是否存在
        if (!Objects.isNull(sysFileInfoMapper.selectSysFileInfoByPath(sysFileInfo.getPath()))) {
            throw new ServiceException("文件已存在");
        }
        //雪花算法生成id
        sysFileInfo.setId(SnowFlakeUtils.getInstance().nextId());
        sysFileInfo.setFileName(fileName);
        sysFileInfo.setExt(FileTypeUtils.getExtension(file));
        sysFileInfo.setDataType(DataTypeConstant.FILE);
        sysFileInfo.setParentDirId(parentDirId);
        sysFileInfo.setSize(file.getSize());
        int i = sysFileInfoMapper.insertSysFileInfo(sysFileInfo);

        // 上传到Minio
        MinioUtils.putObject(minioClient, minioConfig.getBucketName(), sysFileInfo.getPath(), file);
        SysFileInfoVO sysFileInfoVO = mapperFacade.map(sysFileInfo, SysFileInfoVO.class);
        return sysFileInfoVO;
    }


    @Override
    public List<SysFileInfoVO> querySysFileList(Long dirId) {
        List<SysFileInfo> sysFileInfoList = sysFileInfoMapper.selectSysFileInfoByParentDirId(dirId);
        return mapperFacade.mapAsList(sysFileInfoList, SysFileInfoVO.class);
    }

    @Override
    public SysDirInfoVO queryDirInfo(Long dirId) {

        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(dirId);

        if (Objects.isNull(sysFileInfo) || sysFileInfo.getDataType() != 1) {
            throw new ServiceException("没有该文件夹");
        }
        SysDirInfoVO sysDirInfoVO = mapperFacade.map(sysFileInfo, SysDirInfoVO.class);

        sysDirInfoVO.setFileNum(sysFileInfoMapper.querySysFileInfoNum(dirId));
        return sysDirInfoVO;
    }

    @Override
    @Transactional
    public SysDirInfoVO createDir(Long parentDirId, String dirName) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(parentDirId);

        if (Objects.isNull(sysFileInfo) || sysFileInfo.getDataType() != 1) {
            throw new ServiceException("不能在不存在的文件夹下创建文件夹");
        }
        String dirPath = sysFileInfo.getPath() + "/" + dirName;

        // 检查文件是否存在
        if (!Objects.isNull(sysFileInfoMapper.selectSysFileInfoByPath(dirPath))) {
            throw new ServiceException("文件夹已存在");
        }
        sysFileInfo.setId(SnowFlakeUtils.getInstance().nextId());
        sysFileInfo.setFileName(dirName);
        sysFileInfo.setDataType(1);
        sysFileInfo.setPath(dirPath);
        sysFileInfo.setSize(0L);
        sysFileInfo.setParentDirId(parentDirId);
        sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
        SysDirInfoVO sysDirInfoVO = mapperFacade.map(sysFileInfo, SysDirInfoVO.class);
        sysDirInfoVO.setFileNum(0);
        return sysDirInfoVO;
    }

    @Override
    public SysFileInfoVO querySysFileInfoById(Long fileId) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(fileId);
        if (Objects.isNull(sysFileInfo)) {
            throw new ServiceException("文件不存在");
        }
        return mapperFacade.map(sysFileInfo, SysFileInfoVO.class);
    }

    @Override
    @Transactional
    public SysFileInfoVO deleteSysFileInfo(Long fileId) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(fileId);
        if (Objects.isNull(sysFileInfo)) {
            throw new ServiceException("文件不存在");
        }
//        // 判断是否为文件夹 如果是文件夹遍历删除文件
//        if (sysFileInfo.getDataType() != 1) {
//            sysFileInfoMapper.deleteSysFileInfo(sysFileInfo.getId());
//            MinioUtils.deleteObject(minioClient, minioConfig.getBucketName(), sysFileInfo.getPath());
//            return mapperFacade.map(sysFileInfo, SysFileInfoVO.class);
//        }

        List<SysFileInfo> sysFileInfoList = sysFileInfoMapper.selectSysFileInfoByParentDirId(fileId);
        for (SysFileInfo file : sysFileInfoList) {
            sysFileInfoMapper.deleteSysFileInfo(file.getId());
            MinioUtils.deleteObject(minioClient, minioConfig.getBucketName(), file.getPath());
        }
        sysFileInfoMapper.deleteSysFileInfo(sysFileInfo.getId());
        MinioUtils.deleteObject(minioClient, minioConfig.getBucketName(), sysFileInfo.getPath());
        return mapperFacade.map(sysFileInfo, SysFileInfoVO.class);
    }

    @Override
    public InputStream getFileInputStream(Long fileId) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoById(fileId);
        if (Objects.isNull(sysFileInfo)) {
            throw new ServiceException("文件不存在");
        }
        return MinioUtils.getObject(minioClient,minioConfig.getBucketName(), sysFileInfo.getPath());
    }
}
