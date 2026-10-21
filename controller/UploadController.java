package com.qingda.tliaswebmanagement.controller;

import com.qingda.tliaswebmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String username, Integer name, MultipartFile image) throws IOException {
        log.info("文件上传：{},{},{}",username,name,image);


        //获取原始文件名
        String originalFilename = image.getOriginalFilename();
//        image.transferTo(new File("D:\\JAVA\\image\\"+originalFilename));
        //构造唯一文件名 解决上述文件名重复导致图片覆盖问题
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString()+extname;
        log.info("新的文件名:{}",newFileName);
        image.transferTo(new File("D:\\JAVA\\image\\"+newFileName));

        return Result.success();
    }
}
