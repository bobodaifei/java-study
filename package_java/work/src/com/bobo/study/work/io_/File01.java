package com.bobo.study.work.io_;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class File01 {
  public static void main(String[] args) {
    copy_("E:\\abc.txt", "E:\\新建文件夹 (2)");
  }

  public static void copy_(String fromFile, String toFile) {

    FileInputStream in = null;
    FileOutputStream out = null;

    File file = new File(fromFile);

    try {
      in = new FileInputStream(file);
      out = new FileOutputStream(new File(toFile + "/" + file.getName()));
      byte[] bytes = new byte[1024];
      int len;
      while ((len = in.read(bytes)) != -1) {
        out.write(bytes, 0, len);
      }

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        in.close();
        out.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

  }
}
