package com.minio.file.mapper;

import com.minio.file.domain.SysFileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小问号
 * @date 2022/10/14 11
 * description
 */
@Repository
public interface SysFileInfoMapper {

    /**
     * 根据ID查询文件详解
     *
     * @param id 文件ID
     * @return 文件详情
     */
    SysFileInfo selectSysFileInfoById(@Param("id")Long id);

    /**
     * 根据父目录ID获取文件列表
     *
     * @param parentDirId 父目录ID
     * @return  文件列表
     */
    List<SysFileInfo> selectSysFileInfoByParentDirId(@Param("parentDirId") Long parentDirId);

    /**
     * 新增文件
     *
     * @param sysFileInfo 文件详情
     * @return
     */
    int insertSysFileInfo(@Param("sysFileInfo") SysFileInfo sysFileInfo);

    /**
     * 根据文件路径查询文件是否存在
     *
     * @param path 文件路径
     * @return 文件详情
     */
    SysFileInfo selectSysFileInfoByPath(@Param("path") String path);


    /**
     * 查询文件数量
     * @param parentDirId 父目录id
     * @return 文件数
     */
    Integer querySysFileInfoNum(@Param("parentDirId") Long parentDirId);

    /**
     * 根据文件id删除文件
     * @param fileId
     * @return
     */
    Integer deleteSysFileInfo(@Param("fileId") Long fileId);
}
