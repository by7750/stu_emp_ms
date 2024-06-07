package com.ywk.sems.utils;

import com.ywk.sems.exceptions.FileNotSupportException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author yan
 * @version 1.0
 * @date 2024/4/19 - 0:52
 * @className FileUploadUtils
 * @since 1.0
 */
public class FileUploadUtil {

    public static String uploadImg(MultipartFile mf, String path) throws IOException {


        // 校验文件类型
        String originalName = mf.getOriginalFilename();
        if (!originalName.endsWith(".jpg") && !originalName.endsWith(".png") && !originalName.endsWith(".jpeg")) {
            throw new FileNotSupportException("不支持的文件类型！");
        }

        // 拼接文件路劲
        String classpath = ResourceUtils.getURL("classpath:").getPath();
        String realPath;
        if (classpath.contains(".jar")) {
            String userdir = System.getProperty("user.dir");
            realPath = userdir + "/" + path;
        } else {
            realPath = classpath + path;
        }
        System.out.println(realPath);

        // 创建文件夹
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 拼接文件名
        String name = UUID.randomUUID().toString().replace("-", "") + originalName.substring(originalName.lastIndexOf("."));
        // 执行上传
        mf.transferTo(new File(folder, name));

        return name;
    }
}
