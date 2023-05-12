package com.bobo.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
  private static ObjectMapper M = new ObjectMapper();
  
  public static <T> T deserialize(InputStream in, Class<T> c) throws IOException {
    return M.readValue(in, c);
  }
}
