package com.ywk.sems.controller;

import com.ywk.sems.common.ResultInfo;
import com.ywk.sems.utils.FileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yan
 * @version 1.0
 * @date 2024/4/19 - 1:05
 * @className FileUploadController
 * @since 1.0
 */
@RestController
@RequestMapping("/file/upload")
@CrossOrigin
public class FileUploadController {
    @PostMapping("/img")
    public ResultInfo uploadImg(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        try {
            String filename = FileUploadUtil.uploadImg(file, "static/images");
            map.put("url", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/" + filename);
            map.put("uri", request.getContextPath() + "/images/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultInfo.error(e.getMessage());
        }

        return ResultInfo.success(map);
    }
}
