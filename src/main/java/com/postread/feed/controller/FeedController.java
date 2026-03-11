package com.postread.feed.controller;

import com.postread.feed.model.FeedEvent;
import com.postread.feed.service.FeedService;
import com.postread.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Feed", description = "Social activity feed")
public class FeedController {

    private final FeedService feedService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get my social feed")
    public ResponseEntity<Page<FeedEvent>> getFeed(
            @AuthenticationPrincipal UserDetails principal,
            @PageableDefault(size = 20) Pageable pageable) {
        String userId = userService.getByEmail(principal.getUsername()).getId();
        return ResponseEntity.ok(feedService.getFeed(userId, pageable));
    }
}
