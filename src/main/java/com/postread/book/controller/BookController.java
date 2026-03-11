package com.postread.book.controller;

import com.postread.book.dto.BookRequest;
import com.postread.book.model.Book;
import com.postread.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Book catalog management")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add a new book to the catalog")
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(request));
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "Get book details by ID")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @GetMapping
    @Operation(summary = "Search books by title or author")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBooks(q, pageable));
    }
}
