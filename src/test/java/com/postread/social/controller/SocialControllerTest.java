package com.postread.social.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postread.shared.exception.ConflictException;
import com.postread.shared.security.JwtAuthenticationFilter;
import com.postread.shared.security.JwtService;
import com.postread.social.model.Friendship;
import com.postread.social.model.Friendship.FriendshipStatus;
import com.postread.social.service.SocialService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = SocialController.class,
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JwtAuthenticationFilter.class
    )
)
@DisplayName("SocialController")
class SocialControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean SocialService socialService;
    @MockBean UserService userService;
    @MockBean JwtService jwtService;
    @MockBean UserDetailsService userDetailsService;

    private User mockUser;
    private Friendship mockFriendship;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id("user-1").username("leitor").email("leitor@postread.com")
                .roles(Set.of("ROLE_USER")).active(true).build();

        mockFriendship = Friendship.builder()
                .id("friendship-1").requesterId("user-1").receiverId("user-2")
                .status(FriendshipStatus.PENDING).createdAt(Instant.now()).build();
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("POST /social/friends/request/{id}: deve retornar 201 ao enviar pedido")
    void sendRequest_returns201() throws Exception {
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(socialService.sendFriendRequest("user-1", "user-2")).thenReturn(mockFriendship);

        mockMvc.perform(post("/api/v1/social/friends/request/user-2").with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.requesterId").value("user-1"));
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("POST /social/friends/request/{id}: deve retornar 409 quando amizade já existe")
    void sendRequest_alreadyExists_returns409() throws Exception {
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(socialService.sendFriendRequest(any(), any()))
                .thenThrow(new ConflictException("Friendship already exists"));

        mockMvc.perform(post("/api/v1/social/friends/request/user-2").with(csrf()))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("POST /social/friends/accept/{id}: deve retornar friendship ACCEPTED")
    void acceptRequest_returnsAccepted() throws Exception {
        mockFriendship.setStatus(FriendshipStatus.ACCEPTED);
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(socialService.acceptFriendRequest("user-1", "friendship-1")).thenReturn(mockFriendship);

        mockMvc.perform(post("/api/v1/social/friends/accept/friendship-1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTED"));
    }

    @Test
    @WithMockUser(username = "leitor@postread.com")
    @DisplayName("GET /social/friends: deve retornar lista de amigos")
    void getFriends_returnsList() throws Exception {
        when(userService.getByEmail("leitor@postread.com")).thenReturn(mockUser);
        when(socialService.getFriends("user-1")).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/social/friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /social/friends: deve retornar 401 sem autenticação")
    void getFriends_noAuth_returns401() throws Exception {
        mockMvc.perform(get("/api/v1/social/friends"))
                .andExpect(status().isUnauthorized());
    }
}
