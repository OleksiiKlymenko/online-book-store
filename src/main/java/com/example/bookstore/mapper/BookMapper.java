package com.example.bookstore.mapper;

import com.example.bookstore.config.MapperConfig;
import com.example.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.book.CreateBookRequestDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto mapToDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", source = "categoryIds")
    Book mapToModel(CreateBookRequestDto dto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto responseDto, Book book) {
        responseDto.setCategoryIds(book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
    }

    default Set<Long> mapCategoriesToIds(Set<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
    }

    default Set<Category> mapIdsToCategories(Set<Long> ids) {
        return ids.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }
}
