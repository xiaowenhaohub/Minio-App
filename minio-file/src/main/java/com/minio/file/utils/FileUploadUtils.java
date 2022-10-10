package com.minio.file.utils;

import com.minio.common.utils.DateUtils;
import com.minio.common.utils.StringUtils;
import com.minio.common.utils.uuid.Seq;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小问号
 * @date 2022/10/10 16
 * description
 */

public class FileUploadUtils {
    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        return StringUtils.format("{}_{}.{}",
                FilenameUtils.getBaseName(file.getOriginalFilename()),
                Seq.getId(Seq.uploadSeqType),
                FileTypeUtils.getExtension(file));
    }
}
