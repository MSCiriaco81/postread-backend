package com.postread.reading.controller;

import com.postread.reading.dto.ReadingEntryRequest;
import com.postread.reading.model.ReadingEntry;
import com.postread.reading.service.ReadingService;
import com.postread.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Reading Entries", description = "Log and manage reading sessions")
public class ReadingController {

    private final ReadingService readingService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Log a reading session")
    public ResponseEntity<ReadingEntry> logReading(
            @AuthenticationPrincipal UserDetails principal,
            @Valid @RequestBody ReadingEntryRequest request) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(readingService.logReading(userId, request));
    }

    @GetMapping
    @Operation(summary = "Get my reading history")
    public ResponseEntity<Page<ReadingEntry>> getMyReadings(
            @AuthenticationPrincipal UserDetails principal,
            @PageableDefault(size = 20) Pageable pageable) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(readingService.getUserReadings(userId, pageable));
    }

    @GetMapping("/range")
    @Operation(summary = "Get readings in a date range")
    public ResponseEntity<List<ReadingEntry>> getReadingsInRange(
            @AuthenticationPrincipal UserDetails principal,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(readingService.getUserReadingsInRange(userId, from, to));
    }

    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a reading entry")
    public ResponseEntity<Void> deleteEntry(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable String entryId) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        readingService.deleteEntry(userId, entryId);
        return ResponseEntity.noContent().build();
    }
}
