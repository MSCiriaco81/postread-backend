package com.postread.streak.controller;

import com.postread.streak.dto.CreateStreakRequest;
import com.postread.streak.model.Streak;
import com.postread.streak.model.StreakActivity;
import com.postread.streak.service.StreakService;
import com.postread.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/streaks")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Streaks", description = "Reading streaks and group challenges")
public class StreakController {

    private final StreakService streakService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new streak or group challenge")
    public ResponseEntity<Streak> createStreak(
            @AuthenticationPrincipal UserDetails principal,
            @Valid @RequestBody CreateStreakRequest request) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(streakService.createStreak(userId, request));
    }

    @GetMapping
    @Operation(summary = "Get my active streaks")
    public ResponseEntity<List<Streak>> getMyStreaks(@AuthenticationPrincipal UserDetails principal) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(streakService.getMyActiveStreaks(userId));
    }

    @GetMapping("/{streakId}")
    @Operation(summary = "Get streak details")
    public ResponseEntity<Streak> getStreak(@PathVariable String streakId) {
        return ResponseEntity.ok(streakService.getStreak(streakId));
    }

    @PostMapping("/{streakId}/checkin")
    @Operation(summary = "Check in to a streak for today")
    public ResponseEntity<StreakActivity> checkIn(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String streakId,
            @RequestParam(defaultValue = "0") int minutesRead) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(streakService.checkIn(userId, streakId, minutesRead));
    }
}
