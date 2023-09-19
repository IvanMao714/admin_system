package com.gsiv.springboot.service.note;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service.note
 * @InterfaceName: FileService
 * @author: Ivan Mao
 * @description: TODO
 * @date: 2023-09-04 15:59
 * @version: 1.0
 */
public interface FileService {
    /**
     * 上传文件
     * @param file 待上传文件
     * @return 文件的url
     */
    String upload(MultipartFile file);

    /**
     * 上传为文件
     */
    File uploadFile(MultipartFile file);

    /**
     * 创建一个
     */
    File createTmpEmptyDir();

    boolean writeStringToFile(String content, File targetFile);

    String getContentFromFile(File file);

    void deleteFile(File file);
//
//    void batchDelete(List<String> fileNames);

//    UserFile listUserFiles(int pageIndex, int pageSize);
}
