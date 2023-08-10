package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    private final BookService bookService;

    public BookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book kobzar = new Book();
                kobzar.setTitle("Kobzar");
                kobzar.setPrice(BigDecimal.valueOf(200));
                kobzar.setAuthor("Shevchenko");
                kobzar.setIsbn("something");

                bookService.save(kobzar);

                System.out.println(bookService.findAll());
            }
        };
    }

}
