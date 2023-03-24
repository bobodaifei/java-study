package com.bobo.study.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamDemo01 {
  public static void main(String[] args) {
    List<Author> authors = getAuthors();
    // Optional<Integer> i = authors.stream()
    // .distinct()
    // .map(author -> author.getAge())
    // .reduce((maxAge, age) -> maxAge > age ? maxAge : age);
    // i.ifPresent((age)-> {System.out.println(age);});
    // Integer i = authors.stream()
    // .distinct()
    // .map(author -> author.getAge())
    // .reduce(0, (allAge, age) -> allAge + age);
    // System.out.println(i);

    // authors.stream()
    // .distinct()
    // .map(Author::getAge)// Integer
    // .map(age -> age + 10)// Integer先拆箱称int 再计算
    // .filter(age -> age > 18)
    // .map(age -> age + 2)
    // .forEach(System.out::println);

    authors.parallelStream()
        .distinct()
        .mapToInt(Author::getAge)// 全部拆箱
        .map(age -> age + 10)// 计算
        .filter(age -> age > 18)
        .map(age -> age + 2)
        .forEach(System.out::println);

    // Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    // Optional<Integer> optional = stream.parallel()
    // .filter(num -> num > 5)
    // .reduce((total, num) -> total + num);
    // optional.ifPresent(System.out::println);
    // Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    // Optional<Integer> optional1 = stream1.parallel()
    // .peek(new Consumer<Integer>() {

    // @Override
    // public void accept(Integer num) {
    // System.out.println(num + Thread.currentThread().getName());
    // }

    // })
    // .filter(num -> num > 5).reduce((total, num) -> total + num);
    // optional1.ifPresent(System.out::println);
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
