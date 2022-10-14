package com.minio.file.domain.vo;

/**
 * @author 小问号
 * @date 2022/10/14 13
 * description
 */

public class SysFileInfoVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父目录id
     */
    private Long parentDirId;

    /**
     * 1 DIR:目录; 0 FILE文件
     */
    private Integer dataType;

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
     * 文件路径
     */
    private String path;

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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "SysFileInfoVO{" +
                "id=" + id +
                ", parentDirId=" + parentDirId +
                ", dataType=" + dataType +
                ", fileName='" + fileName + '\'' +
                ", ext='" + ext + '\'' +
                ", size='" + size + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
