package com.postread.book.service;

import com.postread.book.dto.BookRequest;
import com.postread.book.model.Book;
import com.postread.book.repository.BookRepository;
import com.postread.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookService")
class BookServiceTest {

    @Mock BookRepository bookRepository;
    @InjectMocks BookService bookService;

    private Book mockBook;

    @BeforeEach
    void setUp() {
        mockBook = Book.builder()
                .id("book-1")
                .title("Dom Quixote")
                .author("Cervantes")
                .pageCount(1000)
                .build();
    }

    @Test
    @DisplayName("addBook: deve salvar e retornar o livro criado")
    void addBook_savesAndReturnsBook() {
        var request = new BookRequest("Dom Quixote", "Cervantes", null, null, null, 1000, null, null);
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        Book result = bookService.addBook(request);

        assertThat(result.getTitle()).isEqualTo("Dom Quixote");
        assertThat(result.getAuthor()).isEqualTo("Cervantes");
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    @DisplayName("getBook: deve retornar livro quando ID existe")
    void getBook_exists_returnsBook() {
        when(bookRepository.findById("book-1")).thenReturn(Optional.of(mockBook));

        Book result = bookService.getBook("book-1");

        assertThat(result.getId()).isEqualTo("book-1");
        assertThat(result.getTitle()).isEqualTo("Dom Quixote");
    }

    @Test
    @DisplayName("getBook: deve lançar ResourceNotFoundException quando ID não existe")
    void getBook_notFound_throwsException() {
        when(bookRepository.findById("nao-existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBook("nao-existe"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("searchBooks: deve buscar por texto quando query não está vazia")
    void searchBooks_withQuery_callsTextSearch() {
        var pageable = PageRequest.of(0, 20);
        Page<Book> page = new PageImpl<>(List.of(mockBook));
        when(bookRepository.searchByText("quixote", pageable)).thenReturn(page);

        Page<Book> result = bookService.searchBooks("quixote", pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(bookRepository).searchByText("quixote", pageable);
        verify(bookRepository, never()).findAll(pageable);
    }

    @Test
    @DisplayName("searchBooks: deve listar todos quando query está vazia")
    void searchBooks_emptyQuery_callsFindAll() {
        var pageable = PageRequest.of(0, 20);
        Page<Book> page = new PageImpl<>(List.of(mockBook));
        when(bookRepository.findAll(pageable)).thenReturn(page);

        Page<Book> result = bookService.searchBooks("", pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(bookRepository).findAll(pageable);
        verify(bookRepository, never()).searchByText(any(), any());
    }
}
