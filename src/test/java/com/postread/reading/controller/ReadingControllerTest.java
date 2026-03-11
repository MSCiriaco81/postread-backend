package com.postread.reading.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postread.reading.dto.ReadingEntryRequest;
import com.postread.reading.model.ReadingEntry;
import com.postread.reading.service.ReadingService;
import com.postread.shared.security.JwtAuthenticationFilter;
import com.postread.shared.security.JwtService;
import com.postread.user.model.User;
import com.postread.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = ReadingController.class,
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JwtAuthenticationFilter.class
    )
)
@DisplayName("ReadingController")
class ReadingControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean ReadingService readingService;
    @MockBean UserService userService;
    @MockBean JwtService jwtService;
    @MockBean UserDetailsService userDetailsService;

    private User mockUser;
    private ReadingEntry mockEntry;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id("user-1").username("leitor").email("leitor@postread.com")
                .roles(Set.of("ROLE_USER")).active(true).build();

        mockEntry = ReadingEntry.builder()
                .id("entry-1").userId("user-1").bookId("book-1")
                .minutesRead(45).pagesRead(30).date(LocalDate.now())
                .build();
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("POST /readings: deve retornar 201 com entrada criada")
    void logReading_valid_returns201() throws Exception {
        var request = new ReadingEntryRequest("book-1", 45, 30, null, null, null);
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(readingService.logReading(eq("user-1"), any(ReadingEntryRequest.class))).thenReturn(mockEntry);

        mockMvc.perform(post("/api/v1/readings")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("entry-1"))
                .andExpect(jsonPath("$.minutesRead").value(45));
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("POST /readings: deve retornar 400 com bookId vazio")
    void logReading_emptyBookId_returns400() throws Exception {
        var request = new ReadingEntryRequest("", 45, 30, null, null, null);

        mockMvc.perform(post("/api/v1/readings")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("GET /readings: deve retornar histórico de leituras")
    void getMyReadings_returnsPage() throws Exception {
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(readingService.getUserReadings(eq("user-1"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(mockEntry)));

        mockMvc.perform(get("/api/v1/readings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].bookId").value("book-1"));
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("DELETE /readings/{id}: deve retornar 204 ao deletar")
    void deleteEntry_returns204() throws Exception {
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);

        mockMvc.perform(delete("/api/v1/readings/entry-1").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("POST /readings: deve retornar 401 sem autenticação")
    void logReading_noAuth_returns401() throws Exception {
        var request = new ReadingEntryRequest("book-1", 45, 30, null, null, null);

        mockMvc.perform(post("/api/v1/readings")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
