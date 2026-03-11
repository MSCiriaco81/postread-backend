package com.postread.social.service;

import com.postread.shared.exception.BusinessException;
import com.postread.shared.exception.ConflictException;
import com.postread.social.model.Friendship;
import com.postread.social.model.Friendship.FriendshipStatus;
import com.postread.social.repository.FriendshipRepository;
import com.postread.user.dto.UserResponse;
import com.postread.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    public Friendship sendFriendRequest(String requesterId, String receiverId) {
        if (requesterId.equals(receiverId)) {
            throw new BusinessException("Cannot add yourself as a friend");
        }
        // Validate receiver exists
        userService.getById(receiverId);

        friendshipRepository.findBetweenUsers(requesterId, receiverId).ifPresent(f -> {
            throw new ConflictException("Friendship already exists with status: " + f.getStatus());
        });

        return friendshipRepository.save(Friendship.builder()
                .requesterId(requesterId)
                .receiverId(receiverId)
                .build());
    }

    public Friendship acceptFriendRequest(String userId, String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new BusinessException("Friend request not found"));

        if (!friendship.getReceiverId().equals(userId)) {
            throw new BusinessException("Only the receiver can accept this request");
        }
        if (friendship.getStatus() != FriendshipStatus.PENDING) {
            throw new BusinessException("Request is not pending");
        }

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendship.setUpdatedAt(Instant.now());
        return friendshipRepository.save(friendship);
    }

    public void rejectFriendRequest(String userId, String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new BusinessException("Friend request not found"));

        if (!friendship.getReceiverId().equals(userId)) {
            throw new BusinessException("Only the receiver can reject this request");
        }

        friendship.setStatus(FriendshipStatus.REJECTED);
        friendship.setUpdatedAt(Instant.now());
        friendshipRepository.save(friendship);
    }

    public List<UserResponse> getFriends(String userId) {
        return friendshipRepository.findByUserAndStatus(userId, FriendshipStatus.ACCEPTED)
                .stream()
                .map(f -> {
                    String friendId = f.getRequesterId().equals(userId) ? f.getReceiverId() : f.getRequesterId();
                    return UserResponse.from(userService.getById(friendId));
                })
                .collect(Collectors.toList());
    }

    public List<String> getFriendIds(String userId) {
        return friendshipRepository.findByUserAndStatus(userId, FriendshipStatus.ACCEPTED)
                .stream()
                .map(f -> f.getRequesterId().equals(userId) ? f.getReceiverId() : f.getRequesterId())
                .collect(Collectors.toList());
    }

    public List<Friendship> getPendingRequests(String userId) {
        return friendshipRepository.findByReceiverIdAndStatus(userId, FriendshipStatus.PENDING);
    }
}
