package com.postread.book.service;

import com.postread.book.dto.BookRequest;
import com.postread.book.model.Book;
import com.postread.book.repository.BookRepository;
import com.postread.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(BookRequest request) {
        return bookRepository.save(request.toBook());
    }

    public Book getBook(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", bookId));
    }

    public Page<Book> searchBooks(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return bookRepository.findAll(pageable);
        }
        return bookRepository.searchByText(query, pageable);
    }
}
