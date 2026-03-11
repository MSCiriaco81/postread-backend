package com.postread.social.service;

import com.postread.shared.exception.BusinessException;
import com.postread.shared.exception.ConflictException;
import com.postread.social.model.Friendship;
import com.postread.social.model.Friendship.FriendshipStatus;
import com.postread.social.repository.FriendshipRepository;
import com.postread.user.dto.UserResponse;
import com.postread.user.model.User;
import com.postread.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SocialService")
class SocialServiceTest {

    @Mock FriendshipRepository friendshipRepository;
    @Mock UserService userService;

    @InjectMocks SocialService socialService;

    private User userA;
    private User userB;
    private Friendship pendingFriendship;

    @BeforeEach
    void setUp() {
        userA = User.builder().id("user-a").username("ana").email("ana@postread.com")
                .roles(Set.of("ROLE_USER")).active(true).build();
        userB = User.builder().id("user-b").username("bruno").email("bruno@postread.com")
                .roles(Set.of("ROLE_USER")).active(true).build();

        pendingFriendship = Friendship.builder()
                .id("friendship-1")
                .requesterId("user-a")
                .receiverId("user-b")
                .status(FriendshipStatus.PENDING)
                .createdAt(Instant.now())
                .build();
    }

    // ── sendFriendRequest ─────────────────────────────────────────────────────

    @Test
    @DisplayName("sendFriendRequest: deve criar friendship PENDING quando válida")
    void sendFriendRequest_valid_createsPending() {
        when(userService.getById("user-b")).thenReturn(userB);
        when(friendshipRepository.findBetweenUsers("user-a", "user-b")).thenReturn(Optional.empty());
        when(friendshipRepository.save(any(Friendship.class))).thenReturn(pendingFriendship);

        Friendship result = socialService.sendFriendRequest("user-a", "user-b");

        assertThat(result.getStatus()).isEqualTo(FriendshipStatus.PENDING);
        verify(friendshipRepository).save(any(Friendship.class));
    }

    @Test
    @DisplayName("sendFriendRequest: deve lançar BusinessException ao tentar adicionar a si mesmo")
    void sendFriendRequest_selfRequest_throwsBusiness() {
        assertThatThrownBy(() -> socialService.sendFriendRequest("user-a", "user-a"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("yourself");
    }

    @Test
    @DisplayName("sendFriendRequest: deve lançar ConflictException quando amizade já existe")
    void sendFriendRequest_alreadyExists_throwsConflict() {
        when(userService.getById("user-b")).thenReturn(userB);
        when(friendshipRepository.findBetweenUsers("user-a", "user-b"))
                .thenReturn(Optional.of(pendingFriendship));

        assertThatThrownBy(() -> socialService.sendFriendRequest("user-a", "user-b"))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Friendship already exists");
    }

    // ── acceptFriendRequest ───────────────────────────────────────────────────

    @Test
    @DisplayName("acceptFriendRequest: deve mudar status para ACCEPTED")
    void acceptFriendRequest_pending_acceptsIt() {
        when(friendshipRepository.findById("friendship-1")).thenReturn(Optional.of(pendingFriendship));
        when(friendshipRepository.save(any(Friendship.class))).thenAnswer(inv -> inv.getArgument(0));

        Friendship result = socialService.acceptFriendRequest("user-b", "friendship-1");

        assertThat(result.getStatus()).isEqualTo(FriendshipStatus.ACCEPTED);
    }

    @Test
    @DisplayName("acceptFriendRequest: deve lançar BusinessException quando não é o receiver")
    void acceptFriendRequest_notReceiver_throwsBusiness() {
        when(friendshipRepository.findById("friendship-1")).thenReturn(Optional.of(pendingFriendship));

        assertThatThrownBy(() -> socialService.acceptFriendRequest("user-a", "friendship-1"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("receiver");
    }

    @Test
    @DisplayName("acceptFriendRequest: deve lançar BusinessException quando já aceita")
    void acceptFriendRequest_alreadyAccepted_throwsBusiness() {
        pendingFriendship.setStatus(FriendshipStatus.ACCEPTED);
        when(friendshipRepository.findById("friendship-1")).thenReturn(Optional.of(pendingFriendship));

        assertThatThrownBy(() -> socialService.acceptFriendRequest("user-b", "friendship-1"))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("not pending");
    }

    // ── getFriends ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getFriends: deve retornar lista de amigos aceitos")
    void getFriends_returnsAcceptedFriends() {
        var accepted = Friendship.builder()
                .requesterId("user-a").receiverId("user-b")
                .status(FriendshipStatus.ACCEPTED).build();

        when(friendshipRepository.findByUserAndStatus("user-a", FriendshipStatus.ACCEPTED))
                .thenReturn(List.of(accepted));
        when(userService.getById("user-b")).thenReturn(userB);

        List<UserResponse> friends = socialService.getFriends("user-a");

        assertThat(friends).hasSize(1);
        assertThat(friends.get(0).username()).isEqualTo("bruno");
    }

    @Test
    @DisplayName("getFriends: deve retornar lista vazia quando não há amigos")
    void getFriends_noFriends_returnsEmpty() {
        when(friendshipRepository.findByUserAndStatus("user-a", FriendshipStatus.ACCEPTED))
                .thenReturn(List.of());

        assertThat(socialService.getFriends("user-a")).isEmpty();
    }

    // ── getFriendIds ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("getFriendIds: deve resolver o ID correto independente de quem enviou o pedido")
    void getFriendIds_resolvesBothDirections() {
        // user-b foi quem enviou o pedido para user-a
        var friendship = Friendship.builder()
                .requesterId("user-b").receiverId("user-a")
                .status(FriendshipStatus.ACCEPTED).build();

        when(friendshipRepository.findByUserAndStatus("user-a", FriendshipStatus.ACCEPTED))
                .thenReturn(List.of(friendship));

        List<String> ids = socialService.getFriendIds("user-a");

        assertThat(ids).containsExactly("user-b");
    }
}
