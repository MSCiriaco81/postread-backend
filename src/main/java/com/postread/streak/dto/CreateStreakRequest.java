package com.postread.streak.dto;

import com.postread.streak.model.Streak.GoalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateStreakRequest(
        @NotBlank String title,
        List<String> participantIds,
        @NotNull GoalType goalType,
        @Min(1) int goalValue
) {}
