package com.lyj.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadFileUtil {
    /**
     * 上传并保存文件
     * @param image_file
     * @param request
     * @return 返回文件url字符串
     */
    public static String uploadHeadImage(MultipartFile image_file, HttpServletRequest request) {
        // 获取文件的名称
        String fileName = image_file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newName = UUIDUtils.getUUID() + suffix;

        // 保存图片的目录
        String path = request.getServletContext().getRealPath("/") +"headImage\\";
        System.out.println(path);
        File file = new File(path);
        // 如果目录不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(path, newName);
        // 上传文件
        try {
            image_file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request.getContextPath() + "/headImage/" + newName;
    }
}
