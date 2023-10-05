package com.example.bookstore.service;

import com.example.bookstore.dto.CategoryResponseDto;
import com.example.bookstore.dto.CreateCategoryRequestDto;
import com.example.bookstore.dto.book.BookDtoWithoutCategoryIds;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto);

    void delete(Long id);

    public List<BookDtoWithoutCategoryIds> getBooksByCategoriesId(Long id);

}
