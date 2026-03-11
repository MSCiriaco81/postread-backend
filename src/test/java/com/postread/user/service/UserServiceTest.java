package com.postread.user.service;

import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.user.dto.UpdateProfileRequest;
import com.postread.user.dto.UserResponse;
import com.postread.user.model.User;
import com.postread.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService")
class UserServiceTest {

    @Mock UserRepository userRepository;
    @InjectMocks UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id("user-123")
                .username("leitor")
                .email("leitor@postread.com")
                .bio("Leitor ávido")
                .roles(Set.of("ROLE_USER"))
                .active(true)
                .build();
    }

    @Test
    @DisplayName("getProfile: deve retornar UserResponse quando usuário existe")
    void getProfile_userExists_returnsResponse() {
        when(userRepository.findById("user-123")).thenReturn(Optional.of(mockUser));

        UserResponse response = userService.getProfile("user-123");

        assertThat(response.id()).isEqualTo("user-123");
        assertThat(response.username()).isEqualTo("leitor");
        assertThat(response.email()).isEqualTo("leitor@postread.com");
    }

    @Test
    @DisplayName("getProfile: deve lançar ResourceNotFoundException quando usuário não existe")
    void getProfile_userNotFound_throwsException() {
        when(userRepository.findById("nao-existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getProfile("nao-existe"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("nao-existe");
    }

    @Test
    @DisplayName("getProfileByUsername: deve retornar usuário correto")
    void getProfileByUsername_found_returnsResponse() {
        when(userRepository.findByUsername("leitor")).thenReturn(Optional.of(mockUser));

        UserResponse response = userService.getProfileByUsername("leitor");

        assertThat(response.username()).isEqualTo("leitor");
    }

    @Test
    @DisplayName("updateProfile: deve atualizar bio e foto quando fornecidos")
    void updateProfile_updatesFields() {
        var request = new UpdateProfileRequest("Nova bio incrível", "https://cdn.postread.com/foto.jpg");
        when(userRepository.findById("user-123")).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UserResponse response = userService.updateProfile("user-123", request);

        assertThat(response.bio()).isEqualTo("Nova bio incrível");
        assertThat(response.profilePicture()).isEqualTo("https://cdn.postread.com/foto.jpg");
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("updateProfile: não deve sobrescrever campos null")
    void updateProfile_nullFields_doesNotOverwrite() {
        var request = new UpdateProfileRequest(null, null);
        when(userRepository.findById("user-123")).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UserResponse response = userService.updateProfile("user-123", request);

        assertThat(response.bio()).isEqualTo("Leitor ávido"); // mantém o original
    }
}
