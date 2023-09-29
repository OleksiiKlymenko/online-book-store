package com.example.bookstore.service;

import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto dto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto getById(Long id);

    void deleteById(Long id);
}
