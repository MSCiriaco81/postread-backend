package com.postread.auth.dto;

public record AuthResponse(
        String token,
        String userId,
        String username,
        String email
) {}
