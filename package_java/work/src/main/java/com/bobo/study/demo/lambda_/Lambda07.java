package com.bobo.study.demo.lambda_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bobo.study.demo.stream.Author;
import com.bobo.study.demo.stream.Book;

public class Lambda07 {
  public static void main(String[] args) {
    List<Author> authors = getAuthors();

    // authors.stream()
    // .distinct()// 去重
    // .map(author -> author.getAge().toString())
    // .forEach(age -> System.out.println(age));

    // authors.stream()
    // .distinct()// 去重
    // .map(Author::getAge)
    // .map(String::valueOf)
    // .forEach(System.out::println);

    // StringBuffer stringBuffer = new StringBuffer();
    // authors.stream()
    // .distinct()// 去重
    // .map(author -> author.getName())
    // .forEach(name -> stringBuffer.append(name));
    // StringBuffer stringBuffer1 = new StringBuffer();
    // authors.stream()
    // .distinct()// 去重
    // .map(Author::getName)
    // .forEach(stringBuffer1::append);

    // String subName = subName("aoligeo", new UserString() {

    // @Override
    // public String use(String str, int start, int length) {
    // return str.substring(start, length);
    // }

    // });
    // System.out.println(subName);

    // String subName1 = subName("aoligeo", String::substring);
    // System.out.println(subName1);

    authors.stream()
        .distinct()
        .map(author -> author.getName())
        .map(name -> new StringBuffer(name))
        .map(sb->sb.append("aoligeo").toString())
        .forEach(name->System.out.println(name));

    authors.stream()
        .distinct()
        .map(Author::getName)
        .map(StringBuffer::new)
        .map(sb->sb.append("111"))
        .forEach(System.out::println);

        

  }

  public static String subName(String str, UserString userString) {
    int start = 0;
    int length = 1;

    return userString.use(str, start, length);

  }

  private static List<Author> getAuthors() {
    Author author1 = new Author(1L, "tom1", 15, "asdasd", null);
    Author author6 = author1;
    Author author2 = new Author(2L, "tom2", 19, "asdasd", null);
    Author author3 = new Author(3L, "tom3", 13, "asdasd", null);
    Author author4 = new Author(4L, "tom4", 22, "asdasd", null);
    ArrayList<Book> books1 = new ArrayList<>();
    ArrayList<Book> books2 = new ArrayList<>();
    ArrayList<Book> books3 = new ArrayList<>();
    ArrayList<Book> books4 = new ArrayList<>();
    books1.add(new Book(1L, "book1", "哲学,小说", 12));
    books1.add(new Book(2L, "book2", "爱情", 12));
    books1.add(new Book(3L, "book3", "风俗,小说", 12));
    books1.add(new Book(4L, "book4", "名著,小说", 12));

    books2.add(new Book(5L, "book11", "传记", 12));
    books2.add(new Book(6L, "book12", "亲情", 12));
    books2.add(new Book(7L, "book13", "哲学,小说", 12));
    books2.add(new Book(8L, "book14", "哲学,伟大", 12));

    books3.add(new Book(9L, "book21", "修仙,小说", 16));
    books3.add(new Book(10L, "book22", "玄幻", 12));

    books4.add(new Book(11L, "book31", "恐怖,电影", 12));

    author1.setBooks(books1);
    author2.setBooks(books2);
    author3.setBooks(books3);
    author4.setBooks(books4);

    ArrayList<Author> arrayList = new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author6));
    return arrayList;

  }
}

interface UserString {
  String use(String str, int start, int length);
}
