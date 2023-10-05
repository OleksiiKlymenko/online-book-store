package com.example.bookstore.service.impl;

import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.book.CreateBookRequestDto;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto save(CreateBookRequestDto dto) {
        Set<Category> categories = dto.getCategoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Can't find category by id " + id)))
                .collect(Collectors.toSet());
        Book book = bookMapper.mapToModel(dto);
        book.setCategories(categories);
        return bookMapper.mapToDto(bookRepository.save(book));
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getById(Long id) {
        return bookMapper.mapToDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find book by id: " + id)));
    }

    @Override
    public BookResponseDto update(Long id, CreateBookRequestDto requestDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book by id " + id + "doesn't exist");
        }
        Book book = bookMapper.mapToModel(requestDto);
        book.setId(id);
        return bookMapper.mapToDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
