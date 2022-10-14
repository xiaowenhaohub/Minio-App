package com.minio.file.domain;

import com.minio.common.domain.BaseEntity;

/**
 * @author 小问号
 * @date 2022/10/14 11
 * description
 */

public class SysFileInfo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父目录id
     */
    private Long parentDirId;

    /**
     * 数据类型DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他
     */
    private String dataType;

    /**
     * 文件名称
     */
    private String fileName;;

    /**
     * 文件后缀
     */
    private String ext;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件md5值
     */
    private String fileMd5;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentDirId() {
        return parentDirId;
    }

    public void setParentDirId(Long parentDirId) {
        this.parentDirId = parentDirId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysFileInfo{" +
                "id=" + id +
                ", parentDirId=" + parentDirId +
                ", dataType='" + dataType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", ext='" + ext + '\'' +
                ", size='" + size + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }
}
