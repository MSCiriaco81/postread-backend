package com.postread.user.dto;

import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
        @Size(max = 200) String bio,
        String profilePicture
) {}
