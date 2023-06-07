package com.bobo.webmvc.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController {

  @PostMapping("/load")
  public Map load(@RequestBody MultipartFile file) throws IOException {
    System.out.println(file);

    //上传文件保存
    //1.获得当前上传文件的输入流
    InputStream inputStream = file.getInputStream();
    //2.获得上传位置的输出流
    FileOutputStream outputStream = new FileOutputStream("E:\\新建文件夹 (2)\\" + file.getOriginalFilename());
    //3.执行文件拷贝
    IOUtils.copyLarge(inputStream,outputStream);
    inputStream.close();
    outputStream.close();


    Map map = new HashMap();
    map.put("111","2222");
    return map;
  }

}
