package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileController {

  @Value("${file.upload.path}")
  private String filePath;

  private String ipPort = "http://localhost:8080";

  @RequestMapping("/upload")
  public String upload(@RequestPart MultipartFile file, Model model) {
    // 判断文件是否为空,不为空则进行文件上传
    if (!file.isEmpty()) {
      // 获取源文件名称
      String fileName = file.getOriginalFilename();
      // 获取源文件后缀名
      String suffix = FilenameUtils.getExtension(fileName);
      // 使用UUID重命名文件名称
      String newFileName = UUID.randomUUID().toString().replace("-", "") + (".") + suffix;
      // 使用日期解决同一文件夹中文件过多问题(以当前日期命名文件夹)
      String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      // 组装最终文件名
      String finalName = datePath + "/" + newFileName;
      // 构建文件对象
      File dest = new File(filePath + finalName);
      // 判断该文件夹是否存在,不存在则创建
      if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdirs(); // 创建文件夹
      }
      try {
        // 将文件保存到硬盘
        file.transferTo(dest);
        // 将当前图片放到模型中,便于页面回显
        model.addAttribute("file", ipPort + "/file/" + finalName);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // 返回页面(该页面是templates目录下的页面)
    return "success";
  }

  //文件下载方式
  @GetMapping("/{flag}/{finalName}")
  public void getFiles(@PathVariable String flag, @PathVariable String finalName, HttpServletResponse response) {
    OutputStream os; // 新建一个输出流对象
    String url = filePath + flag + "/" + finalName;//获取文件路径
    System.out.println(url);
    try {
      if (StrUtil.isNotEmpty(url)) {
        //attachment 下载，inline 内嵌
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(url, "UTF-8"));
        //自动匹配文件类型,但仍然会下载，需要指定对应类型才能内嵌在页面上
        response.setContentType("application/octet-stream");
        byte[] bytes = FileUtil.readBytes(url); // 通过文件的路径读取文件字节流
        os = response.getOutputStream(); // 通过输出流返回文件
        os.write(bytes);
        os.flush();
        os.close();
      }
    } catch (Exception e) {
      System.out.println("文件下载失败");
    }
  }

}


