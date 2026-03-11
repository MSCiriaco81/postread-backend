package com.postread.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postread.book.dto.BookRequest;
import com.postread.book.model.Book;
import com.postread.book.service.BookService;
import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.shared.security.JwtAuthenticationFilter;
import com.postread.shared.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = BookController.class,
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JwtAuthenticationFilter.class
    )
)
// Desabilita os filtros de servlet (incluindo Spring Security) no MockMvc;
// estes testes validam comportamento do controller, não autenticação/autorização.
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("BookController")
class BookControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean BookService bookService;
    @MockBean JwtService jwtService;
    @MockBean UserDetailsService userDetailsService;

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
    @WithMockUser
    @DisplayName("POST /books: deve criar livro e retornar 201")
    void addBook_returns201() throws Exception {
        var request = new BookRequest("Dom Quixote", "Cervantes", null, null, null, 1000, null, null);
        when(bookService.addBook(any(BookRequest.class))).thenReturn(mockBook);

        mockMvc.perform(post("/api/v1/books")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("book-1"))
                .andExpect(jsonPath("$.title").value("Dom Quixote"));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /books: deve retornar 400 quando título está vazio")
    void addBook_emptyTitle_returns400() throws Exception {
        var request = new BookRequest("", "Cervantes", null, null, null, null, null, null);

        mockMvc.perform(post("/api/v1/books")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /books/{id}: deve retornar livro sem autenticação")
    void getBook_noAuth_returns200() throws Exception {
        when(bookService.getBook("book-1")).thenReturn(mockBook);

        mockMvc.perform(get("/api/v1/books/book-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dom Quixote"))
                .andExpect(jsonPath("$.author").value("Cervantes"));
    }

    @Test
    @DisplayName("GET /books/{id}: deve retornar 404 quando livro não existe")
    void getBook_notFound_returns404() throws Exception {
        when(bookService.getBook("nao-existe"))
                .thenThrow(new ResourceNotFoundException("Book", "nao-existe"));

        mockMvc.perform(get("/api/v1/books/nao-existe"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /books: deve buscar livros por query")
    void searchBooks_withQuery_returnsPage() throws Exception {
        when(bookService.searchBooks(eq("quixote"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(mockBook)));

        mockMvc.perform(get("/api/v1/books").param("q", "quixote"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Dom Quixote"));
    }
}
