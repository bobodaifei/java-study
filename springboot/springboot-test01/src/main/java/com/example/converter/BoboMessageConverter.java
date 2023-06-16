package com.example.converter;

import com.example.controller.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class BoboMessageConverter extends AbstractHttpMessageConverter<User> { //此处泛型为需要转换的类型

  public BoboMessageConverter() {
    super(new MediaType("application","x-bobo", Charset.forName("UTF-8")));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    return null;
  }

  @Override
  protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    //自定义协议的写出
    String data = user.getName() + ";" + user.getAge();
    outputMessage.getBody().write(data.getBytes());
  }


}
