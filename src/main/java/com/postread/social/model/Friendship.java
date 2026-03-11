package com.postread.social.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "friendships")
@CompoundIndex(def = "{'requesterId': 1, 'receiverId': 1}", unique = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friendship {

    @Id
    private String id;

    private String requesterId;

    private String receiverId;

    @Builder.Default
    private FriendshipStatus status = FriendshipStatus.PENDING;

    @CreatedDate
    private Instant createdAt;

    private Instant updatedAt;

    public enum FriendshipStatus {
        PENDING, ACCEPTED, REJECTED, BLOCKED
    }
}
