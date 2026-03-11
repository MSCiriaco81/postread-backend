package com.postread.user.dto;

import com.postread.user.model.User;

import java.time.Instant;

public record UserResponse(
        String id,
        String username,
        String email,
        String bio,
        String profilePicture,
        Instant createdAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getProfilePicture(),
                user.getCreatedAt()
        );
    }
}
