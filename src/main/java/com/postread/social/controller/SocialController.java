package com.postread.social.controller;

import com.postread.social.model.Friendship;
import com.postread.social.service.SocialService;
import com.postread.user.dto.UserResponse;
import com.postread.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/social")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Social", description = "Friends and social connections")
public class SocialController {

    private final SocialService socialService;
    private final UserService userService;

    @PostMapping("/friends/request/{receiverId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Send a friend request")
    public ResponseEntity<Friendship> sendRequest(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String receiverId) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(socialService.sendFriendRequest(userId, receiverId));
    }

    @PostMapping("/friends/accept/{friendshipId}")
    @Operation(summary = "Accept a friend request")
    public ResponseEntity<Friendship> acceptRequest(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String friendshipId) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(socialService.acceptFriendRequest(userId, friendshipId));
    }

    @PostMapping("/friends/reject/{friendshipId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Reject a friend request")
    public ResponseEntity<Void> rejectRequest(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String friendshipId) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        socialService.rejectFriendRequest(userId, friendshipId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/friends")
    @Operation(summary = "Get my friends list")
    public ResponseEntity<List<UserResponse>> getFriends(@AuthenticationPrincipal UserDetails principal) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(socialService.getFriends(userId));
    }

    @GetMapping("/friends/requests")
    @Operation(summary = "Get pending friend requests")
    public ResponseEntity<List<Friendship>> getPendingRequests(@AuthenticationPrincipal UserDetails principal) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(socialService.getPendingRequests(userId));
    }
}
