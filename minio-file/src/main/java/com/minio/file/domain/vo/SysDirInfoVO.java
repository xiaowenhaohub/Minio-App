package com.minio.file.domain.vo;

/**
 * @author 小问号
 * @date 2022/10/16 23
 * description
 */

public class SysDirInfoVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 父目录id
     */
    private Long parentDirId;

    /**
     * 文件名称
     */
    private String fileName;;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件数量
     */
    private Integer fileNum;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    @Override
    public String toString() {
        return "SysDirInfoVO{" +
                "id=" + id +
                ", parentDirId=" + parentDirId +
                ", fileName='" + fileName + '\'' +
                ", size='" + size + '\'' +
                ", path='" + path + '\'' +
                ", fileNum=" + fileNum +
                '}';
    }
}
