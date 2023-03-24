package com.bobo.study.demo.optional_;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

import com.bobo.study.demo.stream.Author;
import com.bobo.study.demo.stream.Book;

public class Optional01 {
  public static void main(String[] args) {
    // Author author = getAuthor();
    // Optional<Author> ofNullable = Optional.ofNullable(author);

    // ofNullable.ifPresent(author1 -> System.out.println(author1.getName()));

    // Optional<Author> ofNullable2 = Optional.of(author);
    // ofNullable2.ifPresent(author1 -> System.out.println(author1.getName()));

    // Optional<Author> ofNullable1 = getAuthorOptional();
    // ofNullable1.ifPresent(author1 -> System.out.println(author1.getName()));
    // // 如果为空则返回一个默认值
    // Author author3 = ofNullable1.orElseGet(() -> new Author());
    // // 如果为空则根据传入参数获取异常
    // try {
    // Author author4 = ofNullable1.orElseThrow(() -> new RuntimeException("空"));
    // System.out.println(author4.getName());
    // } catch (Throwable e) {
    // // TODO: handle exception
    // }

    // Optional<Author> ofNullable = getAuthorOptional();
    // ofNullable.filter(author -> author.getAge() > 18).ifPresent(author ->
    // System.out.println(author.getName()));
    // if (ofNullable.isPresent()) {
    // System.out.println(ofNullable.get().getName());
    // }

    Optional<Author> ofNullable = getAuthorOptional();
    ofNullable.map((author) -> author.getBooks()).ifPresent(books -> {
      books.forEach(book -> System.out.println(book.getName()));
    });
  }

  private static Optional<Author> getAuthorOptional() {
    Author author2 = new Author(2L, "tom2", 17, "asdasd", null);
    ArrayList<Book> books1 = new ArrayList<>();

    books1.add(new Book(1L, "book1", "哲学,小说", 12));
    books1.add(new Book(2L, "book2", "爱情", 12));
    books1.add(new Book(3L, "book3", "风俗,小说", 12));
    books1.add(new Book(4L, "book4", "名著,小说", 12));
    author2.setBooks(books1);
    return Optional.ofNullable(author2);
  }

  private static Optional<Author> getAuthorOptional01() {
    Author author2 = new Author(2L, "tom2", 19, "asdasd", null);
    return author2 == null ? Optional.empty() : Optional.of(author2);
  }

  private static Author getAuthor() {
    Author author2 = new Author(2L, "tom2", 19, "asdasd", null);
    return author2;
  }
}
