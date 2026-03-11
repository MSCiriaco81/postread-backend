package com.postread.auth.service;

import com.postread.auth.dto.AuthResponse;
import com.postread.auth.dto.LoginRequest;
import com.postread.auth.dto.RegisterRequest;
import com.postread.shared.exception.ConflictException;
import com.postread.shared.security.JwtService;
import com.postread.user.model.User;
import com.postread.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService")
class AuthServiceTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtService jwtService;
    @Mock AuthenticationManager authenticationManager;

    @InjectMocks AuthService authService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id("user-123")
                .username("leitor")
                .email("leitor@postread.com")
                .passwordHash("hashed_password")
                .roles(Set.of("ROLE_USER"))
                .active(true)
                .build();
    }

    // ── register ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("register: deve criar usuário e retornar token quando dados são válidos")
    void register_success() {
        var request = new RegisterRequest("leitor", "leitor@postread.com", "senha1234");

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByUsername(request.username())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("jwt-token-abc");

        AuthResponse response = authService.register(request);

        assertThat(response.token()).isEqualTo("jwt-token-abc");
        assertThat(response.username()).isEqualTo("leitor");
        assertThat(response.email()).isEqualTo("leitor@postread.com");
        assertThat(response.userId()).isEqualTo("user-123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("register: deve lançar ConflictException quando email já existe")
    void register_emailAlreadyExists_throwsConflict() {
        var request = new RegisterRequest("outro", "leitor@postread.com", "senha1234");
        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Email already in use");

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("register: deve lançar ConflictException quando username já existe")
    void register_usernameAlreadyExists_throwsConflict() {
        var request = new RegisterRequest("leitor", "novo@postread.com", "senha1234");
        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByUsername(request.username())).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Username already taken");

        verify(userRepository, never()).save(any());
    }

    // ── login ─────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("login: deve autenticar e retornar token quando credenciais são válidas")
    void login_success() {
        var request = new LoginRequest("leitor@postread.com", "senha1234");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("jwt-token-xyz");

        AuthResponse response = authService.login(request);

        assertThat(response.token()).isEqualTo("jwt-token-xyz");
        assertThat(response.email()).isEqualTo("leitor@postread.com");
    }

    @Test
    @DisplayName("login: deve lançar BadCredentialsException quando senha é inválida")
    void login_badCredentials_throws() {
        var request = new LoginRequest("leitor@postread.com", "senha-errada");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BadCredentialsException.class);
    }
}
