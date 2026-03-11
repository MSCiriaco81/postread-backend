package com.postread.shared.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JwtService")
class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSecret",
                "postread-super-secret-key-change-in-production-minimum-256-bits");
        ReflectionTestUtils.setField(jwtService, "jwtExpirationMs", 86400000L);

        userDetails = User.builder()
                .username("leitor@postread.com")
                .password("irrelevant")
                .authorities(Collections.emptyList())
                .build();
    }

    @Test
    @DisplayName("generateToken: deve gerar token não nulo e não vazio")
    void generateToken_returnsNonBlankToken() {
        String token = jwtService.generateToken(userDetails);
        assertThat(token).isNotBlank();
    }

    @Test
    @DisplayName("extractUsername: deve extrair o email do subject do token")
    void extractUsername_returnsCorrectEmail() {
        String token = jwtService.generateToken(userDetails);
        String extracted = jwtService.extractUsername(token);
        assertThat(extracted).isEqualTo("leitor@postread.com");
    }

    @Test
    @DisplayName("isTokenValid: deve retornar true para token recém-gerado")
    void isTokenValid_freshToken_returnsTrue() {
        String token = jwtService.generateToken(userDetails);
        assertThat(jwtService.isTokenValid(token, userDetails)).isTrue();
    }

    @Test
    @DisplayName("isTokenValid: deve retornar false quando username não bate")
    void isTokenValid_wrongUser_returnsFalse() {
        String token = jwtService.generateToken(userDetails);
        UserDetails otherUser = User.builder()
                .username("outro@postread.com")
                .password("irrelevant")
                .authorities(Collections.emptyList())
                .build();
        assertThat(jwtService.isTokenValid(token, otherUser)).isFalse();
    }

    @Test
    @DisplayName("isTokenValid: deve retornar false para token expirado")
    void isTokenValid_expiredToken_returnsFalse() {
        // Gera token com expiração no passado
        JwtService expiredJwtService = new JwtService();
        ReflectionTestUtils.setField(expiredJwtService, "jwtSecret",
                "postread-super-secret-key-change-in-production-minimum-256-bits");
        ReflectionTestUtils.setField(expiredJwtService, "jwtExpirationMs", -1000L);

        String token = expiredJwtService.generateToken(userDetails);
        assertThat(jwtService.isTokenValid(token, userDetails)).isFalse();
    }
}
