package com.bobo.study.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

  public static void main(String[] args) {
    List<Author> authors = getAuthors();
    Set<String> set =  authors.stream()
      .distinct()
      .flatMap(author -> author.getBooks().stream())
      .map(book -> book.getName())
      .collect(Collectors.toSet());
    System.out.println(set);
    // List list =  authors.stream()
    //   .distinct()
    //   .map(author -> author.getName())
    //   .collect(Collectors.toList());
    // System.out.println(list);
    // Optional<Integer> score = authors.stream()
    //     .distinct()// 去重
    //     .flatMap(author-> author.getBooks().stream())
    //     .distinct()
    //     .map(book->book.getScore())
    //     .min((Integer score1 , Integer score2)-> score1- score2);
    // Optional<Integer> max = authors.stream()
    //     .distinct()// 去重
    //     .flatMap(author-> author.getBooks().stream())
    //     .distinct()
    //     .map(book->book.getScore())
    //     .max((Integer score1 , Integer score2)-> score1- score2);

    // System.out.println(max.get());
    // long count = authors.stream()
    //     .distinct()// 去重
    //     .flatMap(author-> author.getBooks().stream())
    //     .distinct()
    //     .count();
    // System.out.println(count);
    // authors.stream()
    //     .distinct()// 去重
    //     .flatMap(new Function<Author,Stream<? extends Book>>() {

    //       @Override
    //       public Stream<? extends Book> apply(Author t) {
    //         return t.getBooks().stream();
    //       }
          
    //     })
    //     .distinct()
    //     .flatMap(new Function<Book,Stream<?>>() {

    //       @Override
    //       public Stream<?> apply(Book book) {
    //         return Arrays.stream(book.getCategory().split(","));
    //       }
          
    //     })
    //     .distinct()
    //     .forEach(category -> System.out.println(category));
    // authors.stream()
    //     .distinct()// 去重
    //     .flatMap(new Function<Author,Stream<? extends Book>>() {

    //       @Override
    //       public Stream<? extends Book> apply(Author t) {
    //         return t.getBooks().stream();
    //       }
          
    //     })
    //     .distinct()
    //     .forEach(book -> System.out.println(book.getName()));
    // authors.stream()
    //     .distinct()// 去重
    //     .skip(1)
    //     .forEach(author -> System.out.println(author));
    // authors.stream()
    //     .distinct()// 去重
    //     .limit(1)
    //     .forEach(author -> System.out.println(author));
    // authors.stream()
    // .distinct()// 去重
    // .sorted(new Comparator<Author>() {

    // @Override
    // public int compare(Author o1, Author o2) {
    // return o1.getAge() - o2.getAge();
    // }

    // })
    // .forEach(author -> System.out.println(author));

    // authors.stream()
    // .distinct()// 去重
    // .filter(author -> author.getAge() > 18)
    // .forEach( author -> System.out.println(author));

    // 单列集合
    // Stream<Author> stream = getAuthors().stream();
    // // 数组
    // Integer[] arr = { 1, 2, 3, 4, 5, 6 };
    // Stream<Integer> stream1 = Arrays.stream(arr);
    // Stream<Integer> stream2 = Stream.of(arr);
    // // 双列集合
    // Map<String, Integer> hashMap = new HashMap();
    // hashMap.put("tom1", 12);
    // hashMap.put("tom13", 12);
    // hashMap.put("tom19", 19);
    // Stream<Entry<String, Integer>> stream3 = hashMap.entrySet().stream();
    // stream3.filter(entry -> entry.getValue() > 16)
    // .forEach(entry -> {
    // System.out.println(entry.getKey());
    // });

    // authors.stream()
    // .map(new Function<Author, String>() {

    // @Override
    // public String apply(Author t) {
    // return t.getName();
    // }

    // })
    // .forEach(authorName -> System.out.println(authorName));
    // authors.stream()
    // .map(new Function<Author, Author>() {

    // @Override
    // public Author apply(Author author) {
    // author.setAge(author.getAge()+10);
    // return author;
    // }

    // })
    // .forEach(author -> System.out.println(author));

  }

  private static List<Author> getAuthors() {
    Author author1 = new Author(1L, "tom1", 12, "asdasd", null);
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
