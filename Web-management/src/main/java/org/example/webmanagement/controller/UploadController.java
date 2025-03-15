package org.example.webmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.webmanagement.pojo.Result;
import org.example.webmanagement.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception{
        log.info("文件上传：{}",file.getOriginalFilename());
        String url =aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传完成：{}",url);
        return Result.success(url);
    }
}
