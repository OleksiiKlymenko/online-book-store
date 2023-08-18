package com.example.bookstore.service;

import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.CreateBookRequestDto;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto save(CreateBookRequestDto dto) {
        Book model = bookMapper.mapToModel(dto);
        return bookMapper.mapToDto(bookRepository.save(model));
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getById(Long id) {
        return bookMapper.mapToDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find book by id: " + id)));
    }
}
