package com.postread.book.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private String id;

    @TextIndexed(weight = 3)
    private String title;

    @TextIndexed(weight = 2)
    private String author;

    private String description;

    private String cover;

    private String isbn;

    private Integer pageCount;

    private String publishedYear;

    private String genre;

    @CreatedDate
    private Instant createdAt;
}
