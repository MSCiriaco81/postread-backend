package com.postread.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postread.auth.dto.AuthResponse;
import com.postread.auth.dto.LoginRequest;
import com.postread.auth.dto.RegisterRequest;
import com.postread.auth.service.AuthService;
import com.postread.shared.exception.ConflictException;
import com.postread.shared.security.JwtAuthenticationFilter;
import com.postread.shared.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = AuthController.class,
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JwtAuthenticationFilter.class
    )
)
// Desabilita os filtros de servlet (incluindo Spring Security) no MockMvc;
// estes testes validam comportamento do controller, não autenticação/autorização.
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("AuthController")
class AuthControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean AuthService authService;
    @MockBean JwtService jwtService;
    @MockBean UserDetailsService userDetailsService;

    // ── POST /api/v1/auth/register ────────────────────────────────────────────

    @Test
    @DisplayName("POST /register: deve retornar 201 com token quando dados são válidos")
    void register_validData_returns201() throws Exception {
        var request = new RegisterRequest("leitor", "leitor@postread.com", "senha1234");
        var response = new AuthResponse("jwt-token", "user-1", "leitor", "leitor@postread.com");

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("jwt-token"))
                .andExpect(jsonPath("$.username").value("leitor"))
                .andExpect(jsonPath("$.email").value("leitor@postread.com"));
    }

    @Test
    @DisplayName("POST /register: deve retornar 400 quando username é muito curto")
    void register_shortUsername_returns400() throws Exception {
        var request = new RegisterRequest("ab", "leitor@postread.com", "senha1234");

        mockMvc.perform(post("/api/v1/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.username").exists());
    }

    @Test
    @DisplayName("POST /register: deve retornar 400 quando email é inválido")
    void register_invalidEmail_returns400() throws Exception {
        var request = new RegisterRequest("leitor", "nao-eh-email", "senha1234");

        mockMvc.perform(post("/api/v1/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.email").exists());
    }

    @Test
    @DisplayName("POST /register: deve retornar 409 quando email já está em uso")
    void register_duplicateEmail_returns409() throws Exception {
        var request = new RegisterRequest("leitor", "leitor@postread.com", "senha1234");
        when(authService.register(any())).thenThrow(new ConflictException("Email already in use"));

        mockMvc.perform(post("/api/v1/auth/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    // ── POST /api/v1/auth/login ───────────────────────────────────────────────

    @Test
    @DisplayName("POST /login: deve retornar 200 com token quando credenciais são válidas")
    void login_validCredentials_returns200() throws Exception {
        var request = new LoginRequest("leitor@postread.com", "senha1234");
        var response = new AuthResponse("jwt-token", "user-1", "leitor", "leitor@postread.com");

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    @DisplayName("POST /login: deve retornar 400 quando campos estão vazios")
    void login_emptyFields_returns400() throws Exception {
        var request = new LoginRequest("", "");

        mockMvc.perform(post("/api/v1/auth/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
