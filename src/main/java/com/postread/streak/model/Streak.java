package com.postread.streak.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "streaks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Streak {

    @Id
    private String id;

    private String title;

    private List<String> participantIds;

    private String creatorId;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private int currentStreak = 0;

    @Builder.Default
    private int bestStreak = 0;

    private GoalType goalType;

    private int goalValue;   // e.g. 7 (days), 1000 (minutes)

    @Builder.Default
    private StreakStatus status = StreakStatus.ACTIVE;

    @CreatedDate
    private Instant createdAt;

    public enum GoalType {
        CONSECUTIVE_DAYS,     // X dias seguidos lendo
        TOTAL_MINUTES,        // X minutos totais em grupo
        TOTAL_PAGES           // X páginas totais
    }

    public enum StreakStatus {
        ACTIVE, COMPLETED, BROKEN, EXPIRED
    }
}
