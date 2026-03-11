package com.postread.book.dto;

import com.postread.book.model.Book;
import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String author,
        String description,
        String cover,
        String isbn,
        Integer pageCount,
        String publishedYear,
        String genre
) {
    public Book toBook() {
        return Book.builder()
                .title(title).author(author).description(description)
                .cover(cover).isbn(isbn).pageCount(pageCount)
                .publishedYear(publishedYear).genre(genre)
                .build();
    }
}
