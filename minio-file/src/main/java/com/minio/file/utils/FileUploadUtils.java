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



    /**
     * 获取文件大小
     *
     * @param len
     *            文件大小
     * @param
     *            （B,K,M,G）
     * @return
     */
    public static String getFileSize(Long len) {
        if (len < 1024) {
            return  String.format("%.1f",(double) len) + " B";
        }else if (len < 1048576) {
            return  String.format("%.1f",(double) len / 1024) + " KiB";
        }else if (len < 1073741824) {
            return  String.format("%.1f",(double) len / 1048576) + " MiB";
        }else {
            return  String.format("%.1f",(double) len / 1073741824) + " G";
        }

//        if ("B".equals(unit.toUpperCase())) {
//            return  String.valueOf((double) len) + 'B';
//        } else if ("K".equals(unit.toUpperCase())) {
//            return  String.valueOf((double) len / 1024) + 'K';
//        } else if ("M".equals(unit.toUpperCase())) {
//            return  String.format("%.1f",(double) len / 1048576) + 'M';
//        } else if ("G".equals(unit.toUpperCase())) {
//            return  String.valueOf((double) len / 1073741824) + 'G';
//        }
//        return null;
    }
}
