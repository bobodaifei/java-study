package com.bobo.study.chapter14;

import java.util.ArrayList;
import java.util.Collections;

public class HomeWork01 {
  public static void main(String[] args) {
    News news1 = new News("新闻一000000000000000000000");
    News news2 = new News("新闻二1111111111");
    news1.setContent("内容1");
    news2.setContent("内容2");
    ArrayList list = new ArrayList();
    list.add(news1);
    list.add(news2);
    System.out.println(list);
    Collections.reverse(list);
    System.out.println(list);
    for (Object object : list) {
      News news = (News)object;
      if (news.getText().length()>15) {
        news.setText(news.getText().substring(0, 15)+"...");
      }
    }
    System.out.println(list);

  }
}

class News{
  private String text;
  private String content;

  public News(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  
}