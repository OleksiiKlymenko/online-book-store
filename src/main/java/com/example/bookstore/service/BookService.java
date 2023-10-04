package com.example.bookstore.service;

import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto dto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto getById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);
}
