package com.example.bookstore.service;

import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto dto);

    List<BookResponseDto> findAll();

    BookResponseDto getById(Long id);

    void deleteById(Long id);
}
