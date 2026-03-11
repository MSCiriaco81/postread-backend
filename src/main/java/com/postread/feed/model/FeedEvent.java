package com.postread.feed.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "feed_events")
@CompoundIndex(def = "{'targetUserId': 1, 'createdAt': -1}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedEvent {

    @Id
    private String id;

    private String actorUserId;      // who did the action

    private String targetUserId;     // whose feed this appears in

    private EventType eventType;

    private Map<String, String> payload;  // flexible event data

    @CreatedDate
    private Instant createdAt;

    public enum EventType {
        READING_LOGGED,
        STREAK_MAINTAINED,
        STREAK_BROKEN,
        STREAK_STARTED,
        FRIENDSHIP_STARTED,
        BOOK_ADDED
    }
}
